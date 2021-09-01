package com.not.byko.jarlvpn_android_adminpanel.tools;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.not.byko.jarlvpn_android_adminpanel.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter implements Filterable {
    private List<String> originalData = null;
    private List<String>filteredData = null;
    private List<Integer> image = null;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    public ImageAdapter(Context context, List<String> data, List<Integer> image) {
        this.filteredData = data ;
        this.originalData = data ;
        this.image = image;
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
        View rowView = mInflater.inflate(R.layout.listview_images, null, true);
        TextView titleView = rowView.findViewById(R.id.text1);
        ImageView descriptionView = rowView.findViewById(R.id.icon);

        titleView.setText(filteredData.get(position));
        descriptionView.setImageResource(image.get(position));

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
