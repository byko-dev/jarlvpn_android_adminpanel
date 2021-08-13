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
import com.not.byko.jarlvpn_android_adminpanel.SearchableAdapter;
import com.not.byko.jarlvpn_android_adminpanel.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.NewsListResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private SearchableAdapter searchableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.news_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebController webController = new WebController();

        List<String> newsContent = new ArrayList<>();
        List<String> newsDate = new ArrayList<>();


        for(NewsListResponse newsListResponse: webController.getNewsList()){
            newsDate.add(newsListResponse.getNewsDate());
            newsContent.add(newsListResponse.getNewsContent());
        }

        searchableAdapter = new SearchableAdapter(getActivity(), newsDate, newsContent);

        ListView listView = getView().findViewById(R.id.news_listView);
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
