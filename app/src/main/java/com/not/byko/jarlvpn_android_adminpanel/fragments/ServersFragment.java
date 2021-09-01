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

import com.not.byko.jarlvpn_android_adminpanel.tools.ImageAdapter;
import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.ServerActivity;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.ServersListResponse;

import java.util.ArrayList;
import java.util.List;

public class ServersFragment extends Fragment {


    private ImageAdapter imageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.servers_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebController webController = new WebController();

        List<String> serverIP = new ArrayList<>();
        List<Integer> serverCountryImageCode = new ArrayList<>();
        List<ServersListResponse> servers = webController.getServerList();

        for(ServersListResponse serversListResponse : servers){
            serverIP.add(serversListResponse.getIpAddress() +
                    (serversListResponse.getOwnerEmail().isEmpty() ? "" : " / " + serversListResponse.getOwnerEmail()));
            serverCountryImageCode.add(R.drawable.nl_flag);
        }

        imageAdapter = new ImageAdapter(getActivity(), serverIP, serverCountryImageCode);

        ListView listView = getView().findViewById(R.id.server_listView);
        listView.setAdapter(imageAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), ServerActivity.class);
                intent.putExtra("ipAddress", servers.get(position).getIpAddress());
                intent.putExtra("serverCity", servers.get(position).getServerCity());
                intent.putExtra("serverCountry", servers.get(position).getServerCountry());
                intent.putExtra("status", servers.get(position).getStatus());
                intent.putExtra("ownerEmail", servers.get(position).getOwnerEmail());
                intent.putExtra("createDate", servers.get(position).getCreateDate());
                intent.putExtra("expDate", servers.get(position).getExpDate());
                intent.putExtra("userExpDate", servers.get(position).getUserExpDate());
                intent.putExtra("hosting", servers.get(position).getHosting());
                intent.putExtra("passphrase", servers.get(position).getPassphrase());
                intent.putExtra("id", servers.get(position).getId());
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
                imageAdapter.getFilter().filter(s);

                return true;
            }
        });

    }
}
