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


import com.not.byko.jarlvpn_android_adminpanel.CreateNewsActivity;
import com.not.byko.jarlvpn_android_adminpanel.DeleteNewsActivity;
import com.not.byko.jarlvpn_android_adminpanel.R;
import com.not.byko.jarlvpn_android_adminpanel.tools.SearchableAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;
import com.not.byko.jarlvpn_android_adminpanel.models.NewsListResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private SearchableAdapter searchableAdapter;
    private boolean darkMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        setHasOptionsMenu(true);

        Bundle bundle =  getArguments();
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

        List<String> newsContent = new ArrayList<>();
        List<String> newsDate = new ArrayList<>();

        List<NewsListResponse> newsListResponseList = webController.getNewsList(getView());


        for(NewsListResponse newsListResponse: newsListResponseList){
            newsDate.add(newsListResponse.getNewsDate());
            newsContent.add(newsListResponse.getNewsContent());
        }

        searchableAdapter = new SearchableAdapter(getActivity(), newsDate, newsContent);

        ListView listView = getView().findViewById(R.id.news_listView);
        listView.setAdapter(searchableAdapter);

        Button createNewNews = getView().findViewById(R.id.button9);
        createNewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNewsActivity.class);
                intent.putExtra("darkMode", darkMode);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), DeleteNewsActivity.class);

                int originalPosition = searchableAdapter.originalPosition(position);

                intent.putExtra("id", newsListResponseList.get(originalPosition).getNewsId());
                intent.putExtra("newsContent", newsListResponseList.get(originalPosition).getNewsContent());
                intent.putExtra("newsDate", newsListResponseList.get(originalPosition).getNewsDate());
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
                searchableAdapter.getFilter().filter(s);
                return true;
            }
        });

    }
}
