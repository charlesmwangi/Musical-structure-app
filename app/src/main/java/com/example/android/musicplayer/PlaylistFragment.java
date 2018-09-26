package com.example.android.musicplayer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {


    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.song_list, container, false);
        //Create an ArrayList of songs
        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("phrase1", "Miwork", R.raw.phrase1));
        songs.add(new Song("Phrase2", "Miwork", R.raw.phrase2));
        songs.add(new Song("Phrase3", "Miwork", R.raw.phrase3));


        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(getActivity(), songs);

        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // song_list.xml file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each song in the list of songs.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);


        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);
                String songName = song.getsong();
                String artist = song.getArtist();
                int audio = song.getAudioResourceId();

                //send nan intent to play the song
                Intent intent = new Intent(getActivity().getBaseContext(), NowPlayingActivity.class);
                intent.putExtra("SENDER_KEY", "MyFragment");
                intent.putExtra("song", songName);
                intent.putExtra("artist", artist);
                intent.putExtra("audio", audio);

                getActivity().startActivity(intent);

            }
        });


        return rootView;
    }
}
