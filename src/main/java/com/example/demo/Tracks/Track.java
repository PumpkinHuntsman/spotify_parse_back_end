package com.example.demo.Tracks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown properties
public abstract class Track {
    public String time_stamp;
    public int ms_played;
    public String country_played;
    public String reason_started;
    public String reason_ended;
    public boolean shuffle;
    public boolean skipped;
    public boolean offline;
    public long offline_time_stamp;

    public Track(
            @JsonProperty("ts") String time_stamp,
            @JsonProperty("ms_played") int ms_played,
            @JsonProperty("conn_country") String country_played,
            @JsonProperty("reason_start") String reason_started,
            @JsonProperty("reason_end") String reason_ended,
            @JsonProperty("shuffle") boolean shuffle,
            @JsonProperty("skipped") boolean skipped,
            @JsonProperty("offline") boolean offline,
            @JsonProperty("offline_timestamp") long offline_time_stamp
    ) {
        this.time_stamp = time_stamp;
        this.ms_played = ms_played;
        this.country_played = country_played;
        this.reason_started = reason_started;
        this.reason_ended = reason_ended;
        this.shuffle = shuffle;
        this.skipped = skipped;
        this.offline = offline;
        this.offline_time_stamp = offline_time_stamp;
    }


}