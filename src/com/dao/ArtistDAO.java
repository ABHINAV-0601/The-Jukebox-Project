package com.dao;

import com.util.DatabaseConnection;

import java.sql.*;

public class ArtistDAO {
    public String getArtistNameByArtistId(int artistId) throws SQLException, ClassNotFoundException {
        String artistName = "";
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select name from artist where artist_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,artistId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            artistName = resultSet.getString(1);
        }

        return artistName;
    }
}
