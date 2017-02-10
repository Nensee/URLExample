package com.fragment.login.urlexample;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nensee on 2/10/17.
 */
public class CustomAdapter extends BaseAdapter{

    Context context;
    ArrayList<Post> posts;
    LayoutInflater layoutInflater;


    CustomAdapter(Context context,ArrayList<Post> posts)
    {
        this.context=context;
        this.posts=posts;
    }

    @Override
    public int getCount()
    {
        return posts.size();
    }

    @Override
    public Object getItem(int position)
    {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    static class ViewHolder
    {
        TextView uid;
        TextView iid;
        TextView tile;
        TextView desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
            holder.uid = (TextView) convertView.findViewById(R.id.txtviw1);
            holder.iid = (TextView) convertView.findViewById(R.id.txtviw2);
            holder.tile = (TextView) convertView.findViewById(R.id.txtviw3);
            holder.desc = (TextView) convertView.findViewById(R.id.txtviw4);

            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.uid.setText("UserID: "+posts.get(position).getUserid());
        holder.iid.setText("ID: "+posts.get(position).getId());
        holder.tile.setText("Title: "+posts.get(position).getTitle());
        holder.desc.setText("Description: "+posts.get(position).getDescription());
        return convertView;
    }
}


