package com.data;

import com.dao.ArtistDAO;
import com.dao.GenreDAO;
import com.util.DatabaseConnection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JukeBoxOperations {
    //search for a song method
    public List<Song> searchSong(String name) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from song where name Like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name+ "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int songId = resultSet.getInt(1);
            java.lang.String songName = resultSet.getString(2);
            double duration = resultSet.getDouble(3);
            java.lang.String releaseDate = resultSet.getString(4);
            int genreId = resultSet.getInt(5);
            int artist_Id = resultSet.getInt(6);

            String genreName = new GenreDAO().getGenreNameByGenreId(genreId);
            String artistName = new ArtistDAO().getArtistNameByArtistId(artist_Id);

            songList.add(new Song(songId,songName,duration,releaseDate,genreName,artistName));
        }
        return songList;
    }

    // getAll the songs for the given artist_id
    public List<Song> getAllTheSongsOfTheGivenArtistId(int artistId) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from song where artist_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,artistId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int songId = resultSet.getInt(1);
            java.lang.String songName = resultSet.getString(2);
            double duration = resultSet.getDouble(3);
            java.lang.String releaseDate = resultSet.getString(4);
            int genreId = resultSet.getInt(5);
            int artist_Id = resultSet.getInt(6);

            String genreName = new GenreDAO().getGenreNameByGenreId(genreId);
            String artistName = new ArtistDAO().getArtistNameByArtistId(artist_Id);

            songList.add(new Song(songId,songName,duration,releaseDate,genreName,artistName));
        }
        return songList;
    }
    // to display all songs
    public List<Song> displayAllSongs() throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from song");
       // System.out.println("All songs list");
        //System.out.println();
        while(resultSet.next()){
            int songId = resultSet.getInt(1);
            java.lang.String songName = resultSet.getString(2);
            double duration = resultSet.getDouble(3);
            java.lang.String releaseDate = resultSet.getString(4);
            int genreId = resultSet.getInt(5);
            int artistId = resultSet.getInt(6);

            String genreName = new GenreDAO().getGenreNameByGenreId(genreId);
            String artistName = new ArtistDAO().getArtistNameByArtistId(artistId);

            songList.add(new Song(songId,songName,duration,releaseDate,genreName,artistName));
        }
        return songList;
    }
// to get the song with given genre id
    public List<Song> getAllTheSongsOfTheGivenGenreId(int genreId) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from song where genre_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,genreId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int songId = resultSet.getInt(1);
            String songName = resultSet.getString(2);
            double duration = resultSet.getDouble(3);
            String releaseDate = resultSet.getString(4);
            int genre_Id = resultSet.getInt(5);
            int artistId = resultSet.getInt(6);

            String genreName = new GenreDAO().getGenreNameByGenreId(genre_Id);
            String artistName = new ArtistDAO().getArtistNameByArtistId(artistId);

            songList.add(new Song(songId,songName,duration,releaseDate,genreName,artistName));
        }

        return songList;
    }
    public void printMenu(){
        System.out.println();
        System.out.println("Press \n"+"1 - to play a song \n" +
                "2 - to search song \n" +
                "3 - to enter into playlist menu \n" +
                "4 - to exit from jukebox"
                );
    }
    public void printOptions(){
        System.out.println();
        System.out.println("Press\n"+
                        "1 - to display all songs \n"+
                        "2 - to search song by name \n" +
                        "3 - to search song by artist id \n"+
                        "4 - to search song by genre id \n"+
                        "5 - to return back to previous menu");
    }

    public void playlistOptions(){
        System.out.println();
        System.out.println("Press\n"+
                "1 - to enter into existing playlist \n"+
                "2 - to create new playlist \n" +
                "3 - to add song in existing playlist\n"+
                "4 - to return back to previous menu");
    }
}
