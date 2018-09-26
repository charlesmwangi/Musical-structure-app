package com.example.android.musicplayer;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceSongsFragment extends Fragment {
    //instance variables
    private ArrayList<DeviceSongs> songList=new ArrayList<DeviceSongs>();
    private ListView songView;
    private View rootView;
    final private int REQUEST_PERMISSION = 123;
    public DeviceSongsFragment() {
        // Required empty public constructor
    }
  //check if read external storage is permitted
    private void getSong() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
            return;
        }
        getSongList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.song_list, container, false);
        //retrieve the ListView
        songView = (ListView) rootView.findViewById(R.id.list);
        //set the list by first checking for permissions to read external storage
        getSong();
        //display the list of songs in the user interface and sort the data so that the songs are presented alphabetically
        Collections.sort(songList, new Comparator<DeviceSongs>() {
            public int compare(DeviceSongs a, DeviceSongs b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });
        //create a new instance of the Adapter class and set it on the ListView
        DeviceSongAdapter songAdt = new DeviceSongAdapter(getActivity(), songList);
        songView.setAdapter(songAdt);

        // Set a click listener to play the audio when the list item is clicked on
        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                DeviceSongs song = songList.get(position);
                String songName = song.getTitle();
                String artist = song.getArtist();


                //send nan intent to play the song
                Intent intent = new Intent(getActivity().getBaseContext(), NowPlayingActivity.class);
                intent.putExtra("SENDER_KEY", "MyFragment");
                intent.putExtra("song", songName);
                intent.putExtra("artist", artist);

                getActivity().startActivity(intent);

            }
        });

        return rootView;

    }
//request permission to access files
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    switch (requestCode) {
        case REQUEST_PERMISSION:
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                getSongList();
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), "READ_EXTERNAL_STORAGE Denied", Toast.LENGTH_SHORT)
                        .show();
            }
            break;
        default:
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

    //helper method to retrieve the audio file information:
    public void getSongList() {
        //retrieve song info
        //create a ContentResolver instance
        ContentResolver musicResolver = getActivity().getContentResolver();
        //retrieve the URI for external music files
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        //create a Cursor instance using the ContentResolver instance to query the music files
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        //iterate over the results, first checking that we have valid data
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new DeviceSongs(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
            musicCursor.close();
        }
    }

}
