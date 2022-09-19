package com.data;

import com.dao.ArtistDAO;
import com.dao.GenreDAO;
import com.dao.PlaylistDAO;
import com.dao.SongDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JukeBoxOperationsTest {
    JukeBoxOperations jukeBoxOperations;
    SongDAO songDAO;
    ArtistDAO artistDAO;
    GenreDAO genreDAO;
    PlaylistDAO playlistDAO;
    @BeforeEach
    void setUp() {
        jukeBoxOperations = new JukeBoxOperations();
        songDAO = new SongDAO();
        artistDAO = new ArtistDAO();
        genreDAO = new GenreDAO();
        playlistDAO = new PlaylistDAO();
    }

    @AfterEach
    void tearDown() {
        jukeBoxOperations = null;
        songDAO = null;
        artistDAO = null;
        genreDAO = null;
        playlistDAO = null;
    }

    @Test
    void searchSong() throws SQLException, ClassNotFoundException {
        String name = "Bekha";
       List<Song> actual = jukeBoxOperations.searchSong(name);
       assertEquals(1,actual.size());
    }

    @Test
    void getAllTheSongsOfTheGivenArtistId() throws SQLException, ClassNotFoundException {
        List<Song> actual = jukeBoxOperations.getAllTheSongsOfTheGivenArtistId(1);
        assertEquals(2,actual.size());
    }

    @Test
    void displayAllSongs() throws SQLException, ClassNotFoundException {
        List<Song> actual = jukeBoxOperations.displayAllSongs();
        assertEquals(6,actual.size());
    }

    @Test
    void getAllTheSongsOfTheGivenGenreId() throws SQLException, ClassNotFoundException {
        List<Song> actual = jukeBoxOperations.getAllTheSongsOfTheGivenGenreId(2);
        assertEquals(2,actual.size());
    }
    @Test
    void getSongNameBySongId() throws SQLException, ClassNotFoundException {
        String actual = songDAO.getSongNameBySongId(1);
        assertEquals("Bekhayali",actual);
    }
    @Test
    void getSongById() throws SQLException, ClassNotFoundException {
        Song actual = songDAO.getSongById(1);
        assertEquals("Bekhayali",actual.getName());
    }
    @Test
    void getSongDetailsBySongId() throws SQLException, ClassNotFoundException {
        List<Song> actual = songDAO.getSongDetailsBySongId(1);
        assertEquals(1,actual.size());
    }
    @Test
    void createNewPlaylist() throws SQLException, ClassNotFoundException {
        String name = "favourite";
        int actual = playlistDAO.createNewPlaylist(name);
        assertEquals(1,actual);
    }
    @Test
    void enterIntoExistingPlaylist() throws SQLException, ClassNotFoundException {
        List<Song> actual = playlistDAO.enterIntoExistingPlaylist(1);
        assertEquals(4,actual.size());
    }
    @Test
    void addSongToPlaylist() throws SQLException, ClassNotFoundException {
        int actual = playlistDAO.addSongToPlaylist(1,5);
        assertEquals(1,actual);
    }
    @Test
    void getArtistNameByArtistId() throws SQLException, ClassNotFoundException {
        String actual = artistDAO.getArtistNameByArtistId(1);
        assertEquals("Arijit Singh",actual);
    }
    @Test
    void getGenreNameByGenreId() throws SQLException, ClassNotFoundException {
        String actual = genreDAO.getGenreNameByGenreId(1);
        assertEquals("Romantic",actual);
    }
}