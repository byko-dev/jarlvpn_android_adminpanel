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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.not.byko.jarlvpn_android_adminpanel.DetailsActivity;
import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.WebConfig;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment {

    private Float oneMonthPriceValue;
    private Integer sixMonthsDiscountValue;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebController webController = new WebController();

        TextView totalUsers = getView().findViewById(R.id.textView4);
        TextView totalServers = getView().findViewById(R.id.textView5);
        TextView paypalInvoices = getView().findViewById(R.id.textView6);
        TextView cryptoInvoices = getView().findViewById(R.id.textView7);
        TextView oneMonthPrice = getView().findViewById(R.id.textView8);
        TextView sixMonthsDiscount = getView().findViewById(R.id.textView9);
        TextView unusedServers = getView().findViewById(R.id.textView10);
        TextView paidPaypal = getView().findViewById(R.id.textView11);
        TextView paidCrypto = getView().findViewById(R.id.textView12);
        TextView todayUsers = getView().findViewById(R.id.textView13);
        TextView todayPaypal = getView().findViewById(R.id.textView14);
        TextView todayCrypto = getView().findViewById(R.id.textView15);

        WebConfig webConfig = webController.getDetails();

        oneMonthPriceValue = webConfig.getOneMonthPrice();
        sixMonthsDiscountValue = webConfig.getSixMonthDiscount();

        totalUsers.setText("Total registered users: " + webConfig.getTotalUsers());
        totalServers.setText("Total JarlVPN servers: " + webConfig.getTotalServers());
        unusedServers.setText("Unused JarlVPN servers: " + webConfig.getUnusedServers());
        paypalInvoices.setText("Total created paypal invoices: " + webConfig.getTotalPaypalInvoices());
        paidPaypal.setText("Total paid paypal invoices: " + webConfig.getSuccessInvoicesPaypal());
        cryptoInvoices.setText("Total created crypto invoices: " + webConfig.getTotalCryptoInvoices());
        paidCrypto.setText("Total paid crypto invoices: " + webConfig.getSuccessInvoicesCrypto());
        oneMonthPrice.setText("One month price: " + oneMonthPriceValue);
        sixMonthsDiscount.setText("Six months discount: " + sixMonthsDiscountValue);
        todayUsers.setText("Today users registered: " + webConfig.getTodayUsersRegistered());
        todayPaypal.setText("Today paypal invoices created: " + webConfig.getTodayPaypalInvoices());
        todayCrypto.setText("Today crypto invoices created: " + webConfig.getTodayCryptoInvoices());



        Button button = getView().findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("oneMonthPrice", oneMonthPriceValue.toString());
                intent.putExtra("sixMonthsValue", sixMonthsDiscountValue.toString());

                startActivity(intent);
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        menuItem.setVisible(false);
    }
}
