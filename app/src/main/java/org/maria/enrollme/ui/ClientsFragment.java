package org.maria.enrollme.ui.clients_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maria.enrollme.MainActivity;
import org.maria.enrollme.R;
import org.maria.enrollme.databinding.FragmentClientsBinding;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ClientsFragment extends Fragment {

    private FragmentClientsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentClientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        final TableLayout tableClients = root.findViewById(R.id.tablelayout_clients);
        MainActivity main = (MainActivity) getActivity();
        main.setClientsUI(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}