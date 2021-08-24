package com.not.byko.jarlvpn_android_adminpanel.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

import com.not.byko.jarlvpn_android_adminpanel.ConfigActivity;
import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.SearchableAdapter;
import com.not.byko.jarlvpn_android_adminpanel.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.Configs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigsFragment extends Fragment {

    private SearchableAdapter searchableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.configs_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebController webController = new WebController();
        List<Configs> configsList = webController.getWireGuardConfigurations();
        List<String> confNameAndOwner = new ArrayList<>();
        List<String> serverIp = new ArrayList<>();

        for(Configs configs : configsList){
            confNameAndOwner.add(configs.getConfName() + " / " + configs.getOwnerMail());
            serverIp.add(configs.getServerIp());
        }

        ListView listView = getView().findViewById(R.id.configs_listView);

        searchableAdapter = new SearchableAdapter(getActivity(), confNameAndOwner, serverIp);
        listView.setAdapter(searchableAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ConfigActivity.class);

                intent.putExtra("confName", configsList.get(position).getConfName());
                intent.putExtra("ownerId", configsList.get(position).getOwnerId());
                intent.putExtra("ownerMail", configsList.get(position).getOwnerMail());
                intent.putExtra("orgConfName", configsList.get(position).getOrgConfName());
                intent.putExtra("serverIp", configsList.get(position).getServerIp());

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
