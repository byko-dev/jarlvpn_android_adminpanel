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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.UserActivity;
import com.not.byko.jarlvpn_android_adminpanel.tools.BetterArrayAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    private BetterArrayAdapter arrayAdapter;
    private boolean darkMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users_fragment, container, false);
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

        List<String> list = webController.getUsersList(getView());

        arrayAdapter = new BetterArrayAdapter(getActivity(), list);

        ListView listView = getView().findViewById(R.id.details_listView);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                intent.putExtra("username", list.get(arrayAdapter.originalPosition(position)));
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
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);

                return true;
            }
        });
    }
}
