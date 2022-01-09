package org.maria.enrollme.ui.clients_view;

import android.widget.TableRow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClientsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<String> clientsList;
    private MutableLiveData<List<String>> liveClientsList;

    public ClientsViewModel() {
        clientsList = new ArrayList<String>();
        clientsList.add("test1");
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        liveClientsList = new MutableLiveData<>();
        liveClientsList.setValue(clientsList);
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<String>> getClients() {
        return liveClientsList;
    }

    public void setClients(ArrayList<String> clients) {
        clientsList.add("test2");
    }
}