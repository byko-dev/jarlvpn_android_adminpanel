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
import android.widget.Button;
import android.widget.ListView;

import com.not.byko.jarlvpn_android_adminpanel.CreateDiscountCodeActivity;
import com.not.byko.jarlvpn_android_adminpanel.PromoCodeActivity;
import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.tools.SearchableAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.DiscountCode;

import java.util.ArrayList;
import java.util.List;

public class PromoCodesFragment extends Fragment {

    private SearchableAdapter searchableAdapter;
    private ListView listView;
    private boolean darkMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.promo_code_fragment, container, false);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        darkMode = bundle.getBoolean("darkMode");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        WebController webController = new WebController();

        List<String> titles = new ArrayList<>();
        List<String> description = new ArrayList<>();
        List<DiscountCode> discountCodeList = webController.getDiscountCodeList(getView());

        for(DiscountCode discountCode : discountCodeList){
            titles.add(discountCode.getCode());
            description.add(discountCode.getPercentage().toString());
        }

        searchableAdapter = new SearchableAdapter(getActivity(), titles, description);

        listView = getView().findViewById(R.id.code_listView);

        listView.setAdapter(searchableAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PromoCodeActivity.class);

                intent.putExtra("code", discountCodeList.get(position).getCode());
                intent.putExtra("percentage", String.valueOf(discountCodeList.get(position).getPercentage()));
                intent.putExtra("id", discountCodeList.get(position).getId());
                intent.putExtra("ownerId", discountCodeList.get(position).getOwnerId());
                intent.putExtra("usedTimes", String.valueOf(discountCodeList.get(position).getUsedTimes()));
                intent.putExtra("plan", discountCodeList.get(position).getPlan());
                intent.putExtra("billing", translateBillingCode(discountCodeList.get(position).getBilling()));
                intent.putExtra("darkMode", darkMode);

                startActivity(intent);
            }
        });

        Button button = getView().findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateDiscountCodeActivity.class);
                intent.putExtra("darkMode", darkMode);
                startActivity(intent);
            }
        });


    }

    private String translateBillingCode(Integer billing){
        switch(billing){
            case -1:
                return "All";
            case 1:
                return billing.toString() + " Month";
            default:
                return billing.toString() + " Months";
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchableAdapter.getFilter().filter(newText);

                return true;
            }
        });
    }
}
