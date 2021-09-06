package com.not.byko.jarlvpn_android_adminpanel.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.models.AllCryptoInvoices;
import com.not.byko.jarlvpn_android_adminpanel.tools.InvoiceAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.util.ArrayList;
import java.util.List;

public class InvoicesCryptoFragment extends Fragment {

    private InvoiceAdapter searchableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.invoices_crypto_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebController webController = new WebController();

        List<String> ownersMail = new ArrayList<>();
        List<String> transactionId = new ArrayList<>();
        List<Boolean> paidInvoice = new ArrayList<>();

        for (AllCryptoInvoices cryptoInvoices : webController.getAllCryptoInvoices(view)){
            ownersMail.add(cryptoInvoices.getOwnerMail());
            transactionId.add(cryptoInvoices.getIdTransaction());
            paidInvoice.add(cryptoInvoices.isPaid());
        }

        ListView listView = getView().findViewById(R.id.crypto_listView);

        searchableAdapter = new InvoiceAdapter(getContext(), ownersMail, transactionId, paidInvoice, view);

        listView.setAdapter(searchableAdapter);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchableAdapter.getFilter().filter(s);
                return true;
            }
        });

    }
}
