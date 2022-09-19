package com.dao;

import com.data.Genre;
import com.util.DatabaseConnection;

import java.sql.*;

public class GenreDAO {
    public String getGenreNameByGenreId(int genreId) throws SQLException, ClassNotFoundException {
        String stringName = "";
        Connection connection = DatabaseConnection.getConnection();
        java.lang.String sql = "select name from genre where genre_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,genreId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            stringName = resultSet.getString(1);
        }

        return stringName;
    }
}
