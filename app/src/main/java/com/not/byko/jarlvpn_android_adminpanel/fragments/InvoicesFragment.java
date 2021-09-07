package com.not.byko.jarlvpn_android_adminpanel.fragments;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.not.byko.jarlvpn_android_adminpanel.InvoiceActivity;
import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.models.AllCryptoInvoices;
import com.not.byko.jarlvpn_android_adminpanel.models.AllPaypalInvoices;
import com.not.byko.jarlvpn_android_adminpanel.tools.InvoiceAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.SearchableAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.util.ArrayList;
import java.util.List;

public class InvoicesFragment extends Fragment {

    private InvoiceAdapter searchableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.invoices_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebController webController = new WebController();

        List<String> ownersMail = new ArrayList<>();
        List<String> transactionId = new ArrayList<>();
        List<Boolean> paidInvoice = new ArrayList<>();

        List<AllPaypalInvoices> allPaypalInvoicesList = webController.getAllPaypalInvoices(view);

        for (AllPaypalInvoices paypalInvoices: allPaypalInvoicesList){
            ownersMail.add(paypalInvoices.getOwnerMail());
            transactionId.add(paypalInvoices.getPaymentId());
            paidInvoice.add(paypalInvoices.isPaid());
        }

        ListView listView = getView().findViewById(R.id.paypal_listView);

        searchableAdapter = new InvoiceAdapter(getContext(), ownersMail, transactionId, paidInvoice, view);

        listView.setAdapter(searchableAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), InvoiceActivity.class);
                intent.putExtra("ownersMail", allPaypalInvoicesList.get(position).getOwnerMail());
                intent.putExtra("transactionId", allPaypalInvoicesList.get(position).getPaymentId());
                intent.putExtra("paidInvoice", allPaypalInvoicesList.get(position).isPaid());
                intent.putExtra("purchaseDate", allPaypalInvoicesList.get(position).getPurchaseDate());
                intent.putExtra("subType", allPaypalInvoicesList.get(position).getSubType());
                intent.putExtra("price", allPaypalInvoicesList.get(position).getPrice());
                intent.putExtra("cryptocurrency", "");
                startActivity(intent);
            }
        });
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
