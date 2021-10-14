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
    private boolean darkMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.servers_fragment, container, false);
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

        List<String> serverIP = new ArrayList<>();
        List<Integer> serverCountryImageCode = new ArrayList<>();
        List<ServersListResponse> servers = webController.getServerList(getView());

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

                int originalPosition = imageAdapter.originalPosition(position);

                intent.putExtra("ipAddress", servers.get(originalPosition).getIpAddress());
                intent.putExtra("serverCity", servers.get(originalPosition).getServerCity());
                intent.putExtra("serverCountry", servers.get(originalPosition).getServerCountry());
                intent.putExtra("status", servers.get(originalPosition).getStatus());
                intent.putExtra("ownerEmail", servers.get(originalPosition).getOwnerEmail());
                intent.putExtra("createDate", servers.get(originalPosition).getCreateDate());
                intent.putExtra("expDate", servers.get(originalPosition).getExpDate());
                intent.putExtra("userExpDate", servers.get(originalPosition).getUserExpDate());
                intent.putExtra("hosting", servers.get(originalPosition).getHosting());
                intent.putExtra("passphrase", servers.get(originalPosition).getPassphrase());
                intent.putExtra("id", servers.get(originalPosition).getId());
                intent.putExtra("darkMode", darkMode);
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
