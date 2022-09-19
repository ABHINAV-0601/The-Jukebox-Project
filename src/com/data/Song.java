package com.data;

public class Song {
    private int songId;
    private String name;
    private double duration;
    private String releaseDate;
    private String genre;
    private String artist;

    public Song(){

    }

    public Song(int songId ,String name, double duration, String releaseDate, String genre, String artist) {
        this.songId = songId;
        this.name = name;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.artist = artist;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Override
    public java.lang.String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre=" + genre +
                ", artist=" + artist +
                '}';
    }
}
