package com.example.android.musicplayer;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SongAdapter extends ArrayAdapter<Song> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param songs A List of Song objects to display in a list
     */
    public SongAdapter(Context context, ArrayList<Song> songs)  {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songs);


    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.playlist, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the ImageView in the playlist.xml layout with the ID imageResourceId
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this song or not
        if (currentSong.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            image.setImageResource(currentSong.getImageResourceId());
            // Make sure the view is visible
            image.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            image.setVisibility(View.GONE);
        }



        // Find the TextView in the playlist.xml layout with the song title
        TextView songTextView = (TextView) listItemView.findViewById(R.id.song_title);
        // Get  the current Song object and
        // set this text on the song TextView
        songTextView.setText(currentSong.getsong());

        // Find the TextView in the playlist.xml layout with the artist name
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.song_artist);
        // Get the artist name from the current Song object and
        // set this text on the artist TextView
        artistTextView.setText(currentSong.getArtist());

        return listItemView;
    }

}
