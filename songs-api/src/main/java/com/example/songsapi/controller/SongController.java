package com.example.songsapi.controller;

import com.example.songsapi.models.Song;
import com.example.songsapi.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/song")
    public Iterable<Song> findAllSongs() {
        return songRepository.findAll();
    }
    @GetMapping("/song/{songId}")
    public Song findSongById(@PathVariable Long songId) {
        return songRepository.findOne(songId);
    }
    @DeleteMapping("/song/{songId}")
    public HttpStatus deletesongById(@PathVariable Long songId) {
        songRepository.delete(songId);
        return HttpStatus.OK;
    }

    @PostMapping("/song")
    public Song createNewSong(@RequestBody Song newSong) {
        return songRepository.save(newSong);
    }

    @PatchMapping("/song/{songId}")
    public Song updateSongById(@PathVariable Long songId, @RequestBody Song songRequest) {
        Song songFromDb = songRepository.findOne(songId);
        songFromDb.setTitle(songRequest.getTitle());
        songFromDb.setSonglength(songRequest.getSonglength());
        return songRepository.save(songFromDb);
    }
}
