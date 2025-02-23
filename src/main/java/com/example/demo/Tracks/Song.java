package com.example.demo.Tracks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Song subclass
public class Song extends Track {
    public String song_name;
    public String artist_name;
    public String album_name;

    @JsonCreator
    public Song(
            @JsonProperty("ts") String time_stamp,
            @JsonProperty("ms_played") int ms_played,
            @JsonProperty("conn_country") String country_played,
            @JsonProperty("reason_start") String reason_started,
            @JsonProperty("reason_end") String reason_ended,
            @JsonProperty("shuffle") boolean shuffle,
            @JsonProperty("skipped") boolean skipped,
            @JsonProperty("offline") boolean offline,
            @JsonProperty("offline_timestamp") long offline_time_stamp,
            @JsonProperty("master_metadata_track_name") String song_name,
            @JsonProperty("master_metadata_album_artist_name") String artist_name,
            @JsonProperty("master_metadata_album_album_name") String album_name) {
        super(time_stamp,
                ms_played,
                country_played,
                reason_started,
                reason_ended,
                shuffle,
                skipped,
                offline,
                offline_time_stamp);
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.album_name = album_name;
    }

    @Override
    public String toString() {
        return "Song{ts=" + time_stamp + ", ms_played=" + ms_played + ", artist_name='" + artist_name + "', song_name='" + song_name + "'}";
    }

    @Override
    public String getName() {
        return song_name;
    }
}
