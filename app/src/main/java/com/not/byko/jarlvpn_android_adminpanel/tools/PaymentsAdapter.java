package com.not.byko.jarlvpn_android_adminpanel.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.not.byko.jarlvpn_android_adminpanel.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentsAdapter extends ArrayAdapter<String> {
    private List<String> usernameList = null;
    private List<String> purchaseDate = null;
    private List<String> daysList = null;
    private List<String> idPaymentList = null;
    private LayoutInflater mInflater;

    public PaymentsAdapter(Context context, List<String> usernameList, List<String> purchaseDate,
                            List<String> daysList, List<String> idPaymentList) {
        super(context, R.layout.affiliate_payments_list_view, usernameList);
        this.usernameList = usernameList;
        this.purchaseDate = purchaseDate;
        this.daysList = daysList;
        this.idPaymentList = idPaymentList;
        mInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.affiliate_payments_list_view, null, true);
        TextView usernameTextView = rowView.findViewById(R.id.textView71);
        TextView purchaseTextView = rowView.findViewById(R.id.textView72);
        TextView daysTextview = rowView.findViewById(R.id.textView73);
        TextView idPaymentTextView = rowView.findViewById(R.id.textView74);

        usernameTextView.setText("User: " + usernameList.get(position));
        purchaseTextView.setText("Purchase date: "+ purchaseDate.get(position));
        daysTextview.setText("Ends in: " + daysList.get(position));
        idPaymentTextView.setText("Payment id: " + idPaymentList.get(position));

        return rowView;
    }
}
