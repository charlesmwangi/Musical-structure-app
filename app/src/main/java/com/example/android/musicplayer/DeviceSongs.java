package com.example.android.musicplayer;


public class DeviceSongs {
    /*instance variables for the data we want to store for each track:
     *
     */
    private long id;
    private String title;
    private String artist;

    // constructor method to instantiate the instance variables
    public DeviceSongs(long songID, String songTitle, String songArtist) {
        id = songID;
        title = songTitle;
        artist = songArtist;
    }

    //get methods for the instance variables
    public long getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
