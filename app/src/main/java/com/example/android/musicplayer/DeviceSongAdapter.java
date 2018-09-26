package com.example.android.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeviceSongAdapter extends BaseAdapter {
    //instance variables
    private ArrayList<DeviceSongs> songs;
    private LayoutInflater songInf;

    //a constructor method to instantiate the variables
    public DeviceSongAdapter(Context context, ArrayList<DeviceSongs> theSongs) {
        songs = theSongs;
        songInf = LayoutInflater.from(context);
        //super(context, 0, theSongs);
    }

    @Override
    public int getCount() {
        //return the size of the list
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View songLay = convertView;
        if (songLay == null) {
            songLay = songInf.inflate(
                    R.layout.playlist, parent, false);
        }

        //get title and artist views
        TextView songView = (TextView) songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView) songLay.findViewById(R.id.song_artist);
        //get song using position
        DeviceSongs currSong = songs.get(position);
        //get title and artist strings
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        //set position as tag
        songLay.setTag(position);
        return songLay;
    }
}
