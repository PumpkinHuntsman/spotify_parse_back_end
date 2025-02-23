package com.example.demo.service;

import com.example.demo.Tracks.Episode;
import com.example.demo.Tracks.Song;
import com.example.demo.Tracks.Track;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SharedService {
    private String public_string_1 = "";
    private final List<Track> tracks = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    Map<String, Integer> songListenCount = new HashMap<>();

    public String getPublicString1() {
        return public_string_1;
    }

    public void setPublicString1(String value) {
        this.public_string_1 = value;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public Track getSingleTrack(int track_id) {return tracks.get(track_id);}

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public Map<String, Integer> getSongListenCount() {
        return songListenCount;
    }

    public void processFileUpload(MultipartFile file) throws Exception {
        try {
            JsonNode rootNode = mapper.readTree(file.getInputStream());
            if (rootNode.isEmpty()){
                throw new Exception("File is empty");
            }
            for (JsonNode node : rootNode) {
                // Filter 'nodes' into Songs or Episodes
                if (node.hasNonNull("master_metadata_track_name")) {
                    addTrack(mapper.treeToValue(node, Song.class));
                } else if (node.hasNonNull("episode_name")) {
                    addTrack(mapper.treeToValue(node, Episode.class));
                } else if (!node.has("ts")){
                    throw new Exception("File invalid");
                }
            }
            countSongs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public void countSongs() {
        for (Track track : tracks) {
            if (track.getClass() == Song.class) {
                if (songListenCount.containsKey(track.getName())) {
                    songListenCount.put(track.getName(), songListenCount.get(track.getName()) + 1);
                } else {
                    songListenCount.put(track.getName(), 1);
                }
            }
        }
    }
}
