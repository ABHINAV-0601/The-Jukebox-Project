package com.data;

import java.util.List;

public class Playlist {
    private int playlistId;
    private String name;

    private List<Song> listOfSongs;

    public Playlist() {
    }

    public Playlist(int playlistId,String name, List<Song> listOfSongs) {
        this.playlistId = playlistId;
        this.name = name;
        this.listOfSongs = listOfSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    public void setListOfSongs(List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", name='" + name + '\'' +
                ", listOfSongs=" + listOfSongs +
                '}';
    }
}
