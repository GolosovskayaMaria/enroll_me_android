package org.maria.enrollme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.maria.enrollme.databinding.ActivityMainBinding;
import org.maria.enrollme.network.ClientManager;
import org.maria.enrollme.network.EnrollServer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EnrollServer server;
    private ClientManager clientManager;
    private TableLayout tableClients;

    public void setClientsUI(TableLayout a_tableClients) {
        tableClients = a_tableClients;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.d(getClass().getName(), "switch view " + Integer.toString(destination.getId()));
            }
        });

        server = new EnrollServer(this);
        clientManager = new ClientManager(server, this);
        clientManager.getAll();
//        org.maria.enrollme.ui.clients_view.ClientsViewModel model = new ViewModelProvider(this).get(org.maria.enrollme.ui.clients_view.ClientsViewModel.class);
    }

    public void updateFragments(List<ClientEntity> clients) {
        MainActivity th = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<Fragment> allFragments = getSupportFragmentManager().getFragments();
                if (allFragments == null || allFragments.isEmpty())
                    return;
                for (Fragment fragment : allFragments) {
                    if (fragment.isVisible()) {
                        Log.d(getClass().getName(), "update " + fragment.getClass().toString());
                        androidx.navigation.fragment.NavHostFragment frag = (androidx.navigation.fragment.NavHostFragment) fragment;
//                        final TableLayout tableClients = fragment.findViewById(R.id.tablelayout_clients);
//                        ((org.maria.enrollme.ui.clients_view.ClientsFragment) fragment).updateView();

                        for (ClientEntity entity : clients) {
                            TableRow row = new TableRow(th);
                            TextView tv = new TextView(th);
                            tv.setText(entity.getName() );
                            tableClients.addView(row);
                            row.addView(tv);

                        }


                    }
                }
            }
        });
    }

}