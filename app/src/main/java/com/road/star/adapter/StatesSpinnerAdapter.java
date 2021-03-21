package com.road.star.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.road.star.R;
import com.road.star.models.state.StatesData;

import java.util.List;

public class StatesSpinnerAdapter extends ArrayAdapter<StatesData> {

    private LayoutInflater flater;
    List<StatesData> list;

    public StatesSpinnerAdapter(Activity context, int resouceId, List<StatesData> list) {
        super(context, resouceId, list);
        this.list = list;
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

        StatesData rowItem = getItem(position);

        StatesSpinnerAdapter.viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {

            holder = new StatesSpinnerAdapter.viewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.spinner_dropdown_item, null, false);

            holder.txtTitle = (TextView) rowview.findViewById(R.id.spinnertitle);
            rowview.setTag(holder);
        } else {
            holder = (StatesSpinnerAdapter.viewHolder) rowview.getTag();
        }
        holder.txtTitle.setText(rowItem.getName());
        return rowview;
    }

    private class viewHolder {
        TextView txtTitle;
    }

    public String getId(int position){
        return list.get(position).getState_id();
    }

    public String getName(int position){
        return list.get(position).getName();
    }

}
