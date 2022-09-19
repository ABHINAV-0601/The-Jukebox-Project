package com.main;

import com.dao.PlaylistDAO;
import com.dao.SongDAO;
import com.data.JukeBoxOperations;
import com.data.Song;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JukeBoxImplementation {
    public static void main(String[] args) {
        try {
            AudioPlayer audioPlayer = new AudioPlayer();
            Scanner scanner = new Scanner(System.in);
            JukeBoxOperations jukeBoxOperations = new JukeBoxOperations();
            System.out.println("\n==========================Welcome to JukeBox=========================");
            boolean flag = true;
            while (flag) {
                jukeBoxOperations.printMenu();
                int c = scanner.nextInt();

                switch (c) {
                    case 1:
                        //to play song
                        List<Song> songs = jukeBoxOperations.displayAllSongs();
                        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                        for (Song song : songs) {
                            System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                        }
                        System.out.println("Enter the song Id you want to play");
                        int num = scanner.nextInt();
                        String songName = new SongDAO().getSongNameBySongId(num);
                        String songPath = "resources/" + songName + ".wav";
                        audioPlayer.playSong(songPath);
                        break;

                    case 2:
                        // to search a song
                        boolean value = true;
                        while(value){
                            jukeBoxOperations.printOptions();
                            int a = scanner.nextInt();
                            switch(a){
                                case 1 :
                                    List<Song> songList = jukeBoxOperations.displayAllSongs();
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                                    for (Song song : songList) {
                                        System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                                    }
                                    boolean flag4 = true;
                                    while(flag4) {
                                        System.out.println("Press\n"+ "1 - to play these songs\n" + "2 - return back to previous menu");
                                        int b = scanner.nextInt();
                                        switch(b){
                                            case 1:
                                               /* System.out.println("Enter the song id you want to play");
                                                int num1 = scanner.nextInt();
                                                String songName2 = new SongDAO().getSongNameBySongId(num1);*/
                                                audioPlayer.playSong(songList);
                                                break;
                                            case 2:
                                                flag4 = false;
                                                break;
                                        }

                                    }
                                    break;
                                case 2 :
                                    // to search song by name
                                    System.out.println("Enter the song name you want to search");
                                    scanner.nextLine();
                                    String songName1 = scanner.nextLine();

                                    List<Song> strings = jukeBoxOperations.searchSong(songName1);
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                                    for (Song song : strings) {
                                        System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                                    }
                                    boolean flag1 = true;
                                    while(flag1) {
                                        System.out.println("Press\n"+ "1 - to play these songs\n" + "2 - to add song into playlist\n" + "3 - return back to previous menu");
                                        int b = scanner.nextInt();
                                        switch(b){
                                            case 1:
                                                /*System.out.println("Enter the song id you want to play");
                                                int num1 = scanner.nextInt();
                                                String songName2 = new SongDAO().getSongNameBySongId(num1);*/
                                                audioPlayer.playSong(strings);
                                                break;
                                            case 2 :
                                                System.out.println("Press \n" + "1 - to add song into existing playlist\n" + "2 - to create new playlist and then add\n" + "3 - to go back to previous menu");
                                                boolean flag5 = true;
                                                while(flag5){
                                                    int d = scanner.nextInt();
                                                    switch (d){
                                                        case 1 :
                                                            new PlaylistDAO().getDetailsOfExistingPlaylist();
                                                            System.out.println("Enter the playlist id in which you want to add song");
                                                            int playlistId = scanner.nextInt();
                                                            System.out.println("Enter the song id you want to add in playlist");
                                                            int songId = scanner.nextInt();
                                                            int row = new PlaylistDAO().addSongToPlaylist(playlistId,songId);
                                                            if(row == 1){
                                                                System.out.println("Song added \n" + "Please go to previous menu to play through playlist");
                                                            }else{
                                                                System.out.println("Song not added \n" + "Some error occured");
                                                            }
                                                            break;
                                                        case 2 :
                                                            System.out.println("Enter the name you want to set of playlist");
                                                            scanner.nextLine();
                                                            String playlistName = scanner.nextLine();
                                                            int rows = new PlaylistDAO().createNewPlaylist(playlistName);
                                                            if(rows == 1){
                                                                System.out.println("Playlist created\n" + "Please go to previous menu to add song in this playlist");
                                                            }else{
                                                                System.out.println("Playlist not created");
                                                            }

                                                            break;
                                                        case 3 :
                                                            flag5 = false;
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 3:
                                                flag1 = false;
                                                break;
                                        }

                                    }
                                    break;
                                case 3 :
                                    // to search song by artist id
                                    System.out.println("Artist Id - Artist Name \n" + "1 - Arijit Singh \n" + "2 - Ved Sharma \n" + "3 - Badshah");
                                    System.out.println("Enter the artist id you want to search");
                                    int artistId = scanner.nextInt();

                                    List<Song> songList1 = jukeBoxOperations.getAllTheSongsOfTheGivenArtistId(artistId);
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                                    for (Song song : songList1) {
                                        System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                                    }
                                    boolean flag2 = true;
                                    while(flag2) {
                                        System.out.println("Press\n"+ "1 - to play these songs\n" + "2 - to add song into playlist\n" + "3 - return back to previous menu");

                                        int b = scanner.nextInt();
                                        switch (b) {
                                            case 1:
                                                /*System.out.println("Enter the song id you want to play");
                                                int num1 = scanner.nextInt();
                                                String songName2 = new SongDAO().getSongNameBySongId(num1);*/
                                                audioPlayer.playSong(songList1);
                                                break;
                                            case 2 :
                                                System.out.println("Press \n" + "1 - to add song into existing playlist\n" + "2 - to create new playlist and then add\n" + "3 - to go back to previous menu");
                                                boolean flag5 = true;
                                                while(flag5){
                                                    int d = scanner.nextInt();
                                                    switch (d){
                                                        case 1 :
                                                            new PlaylistDAO().getDetailsOfExistingPlaylist();
                                                            System.out.println("Enter the playlist id in which you want to add song");
                                                            int playlistId = scanner.nextInt();
                                                            System.out.println("Enter the song id you want to add in playlist");
                                                            int songId = scanner.nextInt();
                                                            int row = new PlaylistDAO().addSongToPlaylist(playlistId,songId);
                                                            if(row == 1){
                                                                System.out.println("Song added \n" + "Please go to previous menu to play through playlist");
                                                            }else{
                                                                System.out.println("Song not added \n" + "Some error occured");
                                                            }
                                                            break;
                                                        case 2 :
                                                            System.out.println("Enter the name you want to set of playlist");
                                                            scanner.nextLine();
                                                            String playlistName = scanner.nextLine();
                                                            int rows = new PlaylistDAO().createNewPlaylist(playlistName);
                                                            if(rows == 1){
                                                                System.out.println("Playlist created\n" + "Please go to previous menu to add song in this playlist");
                                                            }else{
                                                                System.out.println("Playlist not created");
                                                            }

                                                            break;
                                                        case 3 :
                                                            flag5 = false;
                                                            break;
                                                    }
                                                }
                                            case 3 :
                                                flag2 = false;
                                                break;
                                        }
                                    }
                                    break;
                                case 4 :
                                    // to search song by genre id
                                    System.out.println("Genre Id - Genre Name \n" + "1 - Romantic \n" + "2 - Fantasy \n" + "3 - PartyMix");
                                    System.out.println("Enter the genre id you want to search");
                                    int genreId = scanner.nextInt();

                                    List<Song> songList2 = jukeBoxOperations.getAllTheSongsOfTheGivenGenreId(genreId);
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                                    for (Song song : songList2) {
                                        System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                                    }
                                    boolean flag3 = true;
                                    while(flag3) {
                                        System.out.println("Press\n"+ "1 - to play these songs\n" + "2 - to add song into playlist\n" + "3 - return back to previous menu");

                                        int b = scanner.nextInt();
                                        switch (b) {
                                            case 1:
                                                /*System.out.println("Enter the song id you want to play");
                                                int num1 = scanner.nextInt();
                                                String songName2 = new SongDAO().getSongNameBySongId(num1);*/
                                                audioPlayer.playSong(songList2);
                                                break;
                                            case 2 :
                                                System.out.println("Press \n" + "1 - to add song into existing playlist\n" + "2 - to create new playlist and then add\n" + "3 - to go back to previous menu");
                                                boolean flag5 = true;
                                                while(flag5){
                                                    int d = scanner.nextInt();
                                                    switch (d){
                                                        case 1 :
                                                            new PlaylistDAO().getDetailsOfExistingPlaylist();
                                                            System.out.println("Enter the playlist id in which you want to add song");
                                                            int playlistId = scanner.nextInt();
                                                            System.out.println("Enter the song id you want to add in playlist");
                                                            int songId = scanner.nextInt();
                                                            int row = new PlaylistDAO().addSongToPlaylist(playlistId,songId);
                                                            if(row == 1){
                                                                System.out.println("Song added \n" + "Please go to previous menu to play through playlist");
                                                            }else{
                                                                System.out.println("Song not added \n" + "Some error occured");
                                                            }
                                                            break;
                                                        case 2 :
                                                            System.out.println("Enter the name you want to set of playlist");
                                                            scanner.nextLine();
                                                            String playlistName = scanner.nextLine();
                                                            int rows = new PlaylistDAO().createNewPlaylist(playlistName);
                                                            if(rows == 1){
                                                                System.out.println("Playlist created\n" + "Please go to previous menu to add song in this playlist");
                                                            }else{
                                                                System.out.println("Playlist not created");
                                                            }

                                                            break;
                                                        case 3 :
                                                            flag5 = false;
                                                            break;
                                                    }
                                                }
                                            case 3:
                                                flag3 = false;
                                                break;
                                        }
                                    }
                                    break;
                                case 5 :
                                    value = false;
                            }
                        }
                        break;
                    case 3 :
                        //playlist menu
                        boolean flag6 = true;
                        while(flag6){
                            jukeBoxOperations.playlistOptions();
                            int a = scanner.nextInt();
                            switch (a){
                                case 1 :
                                    // to enter into existing playlist

                                    new PlaylistDAO().getDetailsOfExistingPlaylist();
                                    System.out.println("Press the playlist id you want to choose");
                                    int playlistId = scanner.nextInt();
                                    List<Song> songList = new PlaylistDAO().enterIntoExistingPlaylist(playlistId);
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                                    for (Song song : songList) {
                                        System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                                    }
                                    boolean flag7 = true;
                                    while(flag7){
                                        System.out.println("Press\n"+ "1 - to play these songs\n" + "2 - return back to previous menu");
                                        int d = scanner.nextInt();
                                        switch(d){
                                            case 1:
                                                /*System.out.println("Enter the song id you want to play");
                                                int num1 = scanner.nextInt();*/
                                                //String songName2 = new SongDAO().getSongNameBySongId(num1);
                                                audioPlayer.playSong(songList);
                                                break;
                                            case 2:
                                                flag7 = false;
                                                break;
                                        }
                                    }
                                    break;
                                case 2 :
                                    // to create new playlist
                                    System.out.println("Enter the name you want to set of playlist");
                                    scanner.nextLine();
                                    String playlistName = scanner.nextLine();
                                    int row = new PlaylistDAO().createNewPlaylist(playlistName);
                                    if(row == 1){
                                        System.out.println("Playlist created\n" + "Please go to previous menu to add song in this playlist");
                                    }else{
                                        System.out.println("Playlist not created");
                                    }

                                    break;
                                case 3 :
                                    // to add song in existing playlist
                                    new PlaylistDAO().getDetailsOfExistingPlaylist();
                                    System.out.println("Enter the playlist id in which you want to add song");
                                    int playlistId1 = scanner.nextInt();
                                    List<Song> songList1 = jukeBoxOperations.displayAllSongs();
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s\n", "Song id", "Song Name", "Duration", "Release Date", "Genre Name", "Artist Name");
                                    for (Song song : songList1) {
                                        System.out.format("%-10d %-30s %-30s %-30s %-30s %-30s\n", song.getSongId(), song.getName(), song.getDuration(), song.getReleaseDate(), song.getGenre(), song.getArtist());
                                    }
                                    System.out.println("Enter the song id you want to add");
                                    int songId = scanner.nextInt();
                                    int rows = new PlaylistDAO().addSongToPlaylist(playlistId1,songId);
                                    if(rows == 1){
                                        System.out.println("Song added \n" + "Please go to previous menu to play through playlist");
                                    }else{
                                        System.out.println("Song not added \n" + "Some error occured");
                                    }
                                    break;
                                case 4 :
                                    flag6 = false;
                                    break;
                            }
                        }
                        break;
                    case 4:
                        // to stop jukebox
                        flag = false;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
