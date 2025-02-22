package com.example.demo.service;

import com.example.demo.Tracks.Track;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SharedService {
    private String public_string_1 = "";
    private final List<Track> tracks = new ArrayList<>();

    public String getPublicString1() {
        return public_string_1;
    }

    public void setPublicString1(String value) {
        this.public_string_1 = value;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }
}
