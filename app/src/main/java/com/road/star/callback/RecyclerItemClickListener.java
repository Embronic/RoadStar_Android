package com.road.star.callback;

import android.view.View;

public interface RecyclerItemClickListener<T> {

    void onItemClick(int position, T item, View v);
}
