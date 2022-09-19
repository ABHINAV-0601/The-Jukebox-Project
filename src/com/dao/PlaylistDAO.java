package com.dao;

import com.data.Playlist;
import com.data.Song;
import com.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlaylistDAO {

    public int createNewPlaylist(String playlistName) throws SQLException, ClassNotFoundException {
        int row = 0;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into playlist (name) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,playlistName);

        row = preparedStatement.executeUpdate();
        return row;
    }
    public List<Song> enterIntoExistingPlaylist(int playlistId) throws SQLException, ClassNotFoundException {
        List<Song> songList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select song_id from playlistcontents where playlist_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,playlistId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int songId = resultSet.getInt(1);
            //Song songDetails = new SongDAO().getSongDetailsBySongId(songId);

            songList.add(new SongDAO().getSongById(songId));

        }
        return songList;
    }
    public void getDetailsOfExistingPlaylist() throws SQLException, ClassNotFoundException {
        //List<String> stringList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from playlist";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        System.out.format("%-30s %-30s\n" , "PlayList Id" , "Playlist Name");
        while(resultSet.next()){
            String playlistId = resultSet.getString(1);
            String playlistName = resultSet.getString(2);

            System.out.format("%-30s %-30s\n" , playlistId , playlistName);
        }
        //return stringList;
    }
    public int addSongToPlaylist(int playlistId , int songId) throws SQLException, ClassNotFoundException {
        int row = 0;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into playlistcontents values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,playlistId);
        preparedStatement.setInt(2,songId);

        row = preparedStatement.executeUpdate();
        return row;
    }
}
