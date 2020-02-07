package com.example.m335_poe_ladders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LadderAdapter extends ArrayAdapter<Ladder> {

    public LadderAdapter(Context context, ArrayList<Ladder> ladders) {
        super(context, 0, ladders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Ladder ladder = getItem(position);

        // Check if any existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ladder_item, parent, false);
        }

        // Lookup view for data population
        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView level = (TextView) convertView.findViewById(R.id.level);

        // Populate the data into the template view using the data object
        rank.setText(String.format("%d", ladder.getRank()));
        image.setImageResource(ladder.getImageId());
        name.setText(ladder.getName());
        level.setText(String.format("Level %d", ladder.getLevel()));

        // Return the completed view to render on screen
        return convertView;
    }
}
