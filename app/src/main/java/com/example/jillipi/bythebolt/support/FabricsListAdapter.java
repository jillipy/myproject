package com.example.jillipi.bythebolt.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jillipi.bythebolt.R;
import com.example.jillipi.bythebolt.data.Fabric;

import java.util.ArrayList;

/**
 * Created by jillianpyle on 1/19/15.
 */
public class FabricsListAdapter extends ArrayAdapter<Fabric> {
    Context context;
    int layoutResourceId;

    public FabricsListAdapter(Context context, int layoutResoruceId){
        super(context, layoutResoruceId);
        this.context = context;
        this.layoutResourceId = layoutResoruceId;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = getWorkingView(convertView);
        ViewHolder viewHolder = getViewHolder(view);
        Fabric fabric = getItem(position);

        String formattedSubtext = String.format("ID: %s Type: %s Yards: %s ", fabric.getId(), fabric.getType(), fabric.getYardage());

        viewHolder.fabric_title.setText(fabric.getName());
        viewHolder.fabric_subtitle.setText(formattedSubtext);
        viewHolder.fabric_icon.setImageResource(R.drawable.ic_action_photo);

        return view;
    }

    private View getWorkingView(View convertView){
        View workingView;

        if (null == convertView){
            Context context = getContext();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            workingView = inflater.inflate(layoutResourceId,null);

        }
        else {
            workingView = convertView;
        }
        return workingView;
    }

    private ViewHolder getViewHolder(View workingView) {
        final Object tag = workingView.getTag();
        ViewHolder holder;

        if (null == tag || !(tag instanceof ViewHolder)){
            holder = new ViewHolder();
            holder.fabric_icon = (ImageView) workingView.findViewById(R.id.fabric_icon);
            holder.fabric_title = (TextView) workingView.findViewById(R.id.fabric_title);
            holder.fabric_subtitle = (TextView) workingView.findViewById(R.id.fabric_subtitles);
            workingView.setTag(holder);

        }
        else {
            holder = (ViewHolder) tag;
        }

        return holder;
    }

    private static class ViewHolder {
        public TextView fabric_title;
        public TextView fabric_subtitle;
        public ImageView fabric_icon;
    }
}
