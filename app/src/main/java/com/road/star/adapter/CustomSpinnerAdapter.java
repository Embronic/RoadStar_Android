package com.road.star.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.road.star.R;
import com.road.star.models.CustomSpinnerModel;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<CustomSpinnerModel> {

    private LayoutInflater flater;

    public CustomSpinnerAdapter(Activity context, int resouceId, List<CustomSpinnerModel> list) {
        super(context, resouceId, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    private View rowview(View convertView, int position) {

        CustomSpinnerModel rowItem = getItem(position);

        CustomSpinnerAdapter.viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {

            holder = new CustomSpinnerAdapter.viewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.spinner_dropdown_item, null, false);

            holder.txtTitle = (TextView) rowview.findViewById(R.id.spinnertitle);
            rowview.setTag(holder);
        } else {
            holder = (CustomSpinnerAdapter.viewHolder) rowview.getTag();
        }
        holder.txtTitle.setText(rowItem.getTitle());
        return rowview;
    }

    private class viewHolder {
        TextView txtTitle;
    }
}