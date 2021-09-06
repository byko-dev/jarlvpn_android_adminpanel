package com.not.byko.jarlvpn_android_adminpanel.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.not.byko.jarlvpn_android_adminpanel.R;

import java.util.ArrayList;
import java.util.List;

public class InvoiceAdapter extends BaseAdapter implements Filterable {
    private List<String> originalData = null;
    private List<String>filteredData = null;
    private List<String> descriptionToData = null;
    private List<Boolean> paid = null;
    private View view;
    private LayoutInflater mInflater;
    private InvoiceAdapter.ItemFilter mFilter = new InvoiceAdapter.ItemFilter();

    public InvoiceAdapter(Context context, List<String> data, List<String> descriptionToData,
                          List<Boolean> paid, View view) {
        this.filteredData = data ;
        this.originalData = data ;
        this.descriptionToData = descriptionToData;
        this.paid = paid;
        this.view = view;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return filteredData.size();
    }

    public Object getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(android.R.layout.simple_list_item_2, null, true);
        TextView titleView = rowView.findViewById(android.R.id.text1);
        TextView descriptionView = rowView.findViewById(android.R.id.text2);

        titleView.setText(filteredData.get(position));
        descriptionView.setText(descriptionToData.get(position));

        if(paid.get(position)) titleView.setTextColor(view.getResources().getColor(R.color.green));
        else titleView.setTextColor(view.getResources().getColor(R.color.red));

        return rowView;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<String> list = originalData;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    }
}
