package com.example.demo.Tracks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Episode subclass
public class Episode extends Track {
    public String episode_name;
    public String show_name;

    @JsonCreator
    public Episode(
            @JsonProperty("ts") String time_stamp,
            @JsonProperty("ms_played") int ms_played,
            @JsonProperty("conn_country") String country_played,
            @JsonProperty("reason_start") String reason_started,
            @JsonProperty("reason_end") String reason_ended,
            @JsonProperty("shuffle") boolean shuffle,
            @JsonProperty("skipped") boolean skipped,
            @JsonProperty("offline") boolean offline,
            @JsonProperty("offline_timestamp") long offline_time_stamp,
            @JsonProperty("episode_name") String episode_name,
            @JsonProperty("episode_show_name") String show_name) {


        super(time_stamp,
                ms_played,
                country_played,
                reason_started,
                reason_ended,
                shuffle,
                skipped,
                offline,
                offline_time_stamp);
        this.episode_name = episode_name;
        this.show_name = show_name;
    }

    @Override
    public String toString() {
        return "Episode{ts=" + time_stamp + ", ms_played=" + ms_played + ", show_name='" + show_name + "', episode_name='" + episode_name + "'}";
    }

    @Override
    public String getName(){
        return episode_name;
    }
}