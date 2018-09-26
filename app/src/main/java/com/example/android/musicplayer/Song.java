package com.example.android.musicplayer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * {@link Song} represents a a song that the user wants to play.
 * It contains a song title,the artist name, image resource id  for that song and audio resource id for the song.
 */
public class Song implements Parcelable{
    /**
     * The song title
     */
    private String songTitle;

    /**
     * Song artist name
     */
    private String artistName;

    /**
     * Audio Resource id for the song
     */
    private int audioResourceId;
    /**
     * image resource id  for the song
     */
    private int mImageResourceId=NO_IMAGE_PROVIDED;
    /**


    /** Constant value that represents no image was provided for this song */
    private static final int NO_IMAGE_PROVIDED = -1;

    // We can also include child Parcelable objects. Assume MySubParcel is such a Parcelable:
    private Song mInfo;

    /**
     * Create a new Song object.
     *
     * @param song is the title of the song
     * @param artist   is the name of the artist
     */
    public Song(String song, String artist, int AudioResourceId ) {
        songTitle = song;
        artistName = artist;
        audioResourceId = AudioResourceId;

    }
    /**
     * Create a new Song object.
     *
     * @param song is the title of the song
     * @param artist   is the name of the artist
     * @param imageResourceId is the drawable resource ID for the image associated with the song
     *
     */
    public Song(String song, String artist, int imageResourceId, int AudioResourceId){
        songTitle = song;
        artistName = artist;
        mImageResourceId = imageResourceId;
        audioResourceId = AudioResourceId;
    }

    protected Song(Parcel in) {
        songTitle = in.readString();
        artistName = in.readString();
        audioResourceId = in.readInt();
        mImageResourceId = in.readInt();
        mInfo = in.readParcelable(Song.class.getClassLoader());
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    /**
     * Get the song title.
     */
    public String getsong() {
        return songTitle;
    }

    /**
     * Get the artist name.
     */
    public String getArtist() {
        return artistName;
    }

    /**
     * Get the image resource id of the song.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this song.
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get the audio resource id of the song.
     */
    public int getAudioResourceId() {
        return audioResourceId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songTitle);
        dest.writeString(artistName);
        dest.writeInt(audioResourceId);
        dest.writeInt(mImageResourceId);
        dest.writeParcelable(mInfo, flags);
    }
    public Song() {
        // Normal actions performed by class, since this is still a normal object!
    }
}
