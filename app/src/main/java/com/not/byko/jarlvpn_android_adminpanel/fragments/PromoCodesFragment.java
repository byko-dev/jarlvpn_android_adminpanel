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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.SearchableAdapter;
import com.not.byko.jarlvpn_android_adminpanel.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.LoginRequest;

import java.util.ArrayList;
import java.util.List;

public class PromoCodesFragment extends Fragment {

    private SearchableAdapter searchableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.promo_code_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebController webController = new WebController();

        List<String> titles = new ArrayList<>();
        List<String> description = new ArrayList<>();

        //for (DiscountCode discountCode : webController.getDiscountCodeList()){
        //    titles.add(discountCode.getCode());
        //    description.add(discountCode.getPercentage().toString());
        //}

        titles.add("RADARA");
        description.add("13");
        titles.add("JARLVPN");
        description.add("4");

        searchableAdapter = new SearchableAdapter(getActivity(), titles, description);

        ListView listView = getView().findViewById(R.id.code_listView);

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
