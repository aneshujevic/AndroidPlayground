package com.example.pictures_json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<CatModel> catModelArrayList;

    CatAdapter(Context context, ArrayList<CatModel> catModelArrayList) {
        this.context = context;
        this.catModelArrayList = catModelArrayList;
    }

    @Override
    public int getCount() {
        return catModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return catModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.lv_cats, null, true);
            holder.iv = convertView.findViewById(R.id.iv);
            holder.name = convertView.findViewById(R.id.name);
            holder.race = convertView.findViewById(R.id.race);
            holder.sound = convertView.findViewById(R.id.sound);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(catModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.name.setText("Name: " + catModelArrayList.get(position).getName());
        holder.race.setText("Race: " + catModelArrayList.get(position).getRace());
        holder.sound.setText("Sound: " + catModelArrayList.get(position).getSound());

        return convertView;
    }

    private static class ViewHolder {
        TextView name, race, sound;
        ImageView iv;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
