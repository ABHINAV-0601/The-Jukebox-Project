package com.dao;

import com.data.Song;
import com.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    public String getSongNameBySongId(int songId) throws SQLException, ClassNotFoundException {
        String songName = "";
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select name from song where song_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,songId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            songName = resultSet.getString(1);
        }

        return songName;
    }
    public List<Song> getSongDetailsBySongId(int songId) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from song where song_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,songId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int song_Id = resultSet.getInt(1);
            String songName = resultSet.getString(2);
            double duration = resultSet.getDouble(3);
            String releaseDate = resultSet.getString(4);
            int genre_Id = resultSet.getInt(5);
            int artistId = resultSet.getInt(6);

            String genreName = new GenreDAO().getGenreNameByGenreId(genre_Id);
            String artistName = new ArtistDAO().getArtistNameByArtistId(artistId);

            songList.add(new Song(song_Id,songName,duration,releaseDate,genreName,artistName));
        }
        return songList;
    }
    public Song getSongById(double songId) throws SQLException, ClassNotFoundException {
        Song song = new Song();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from song where song_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1,songId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int song_Id = resultSet.getInt(1);
            String songName = resultSet.getString(2);
            double duration = resultSet.getDouble(3);
            String releaseDate = resultSet.getString(4);
            int genre_Id = resultSet.getInt(5);
            int artistId = resultSet.getInt(6);

            String genreName = new GenreDAO().getGenreNameByGenreId(genre_Id);
            String artistName = new ArtistDAO().getArtistNameByArtistId(artistId);

            song.setSongId(song_Id);
            song.setName(songName);
            song.setDuration(duration);
            song.setReleaseDate(releaseDate);
            song.setGenre(genreName);
            song.setArtist(artistName);
        }
        return song;
    }
    /*public String getSongPath(){

    }*/
}
