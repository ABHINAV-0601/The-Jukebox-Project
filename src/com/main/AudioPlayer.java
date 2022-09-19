package com.main;


import com.data.Song;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    List<Song> songsList;
    String songPath;
    int songIndex;
    public void playSong(List<Song> songsList) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        this.songsList = songsList;
        for (int i = 0; i < songsList.size(); i++) {
            songIndex = i;
            songPath = "resources/" + songsList.get(songIndex).getName() + ".wav";
            boolean flag = playSong(songPath);
            if(flag){
                break;
            }
        }
        songPath = null;
        songIndex = 0;
        songsList = null;
    }
    public boolean playSong(String songPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        try {
            String path = songPath ;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while (!response.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, R = Reset, Q = Quit, N = Next Song, Z = Previous Song");
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch (response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        //  System.out.println("Songs in queue: ");
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }

                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;

                    case ("Q"):
                        clip.close();
                        flag = true;
                        break;
                    case("N"):
                        songIndex += 1;
                        clip.close();
                        songPath = "resources/" + songsList.get(songIndex).getName() + ".wav";
                        playSong(songPath);
                        break;
                    case("Z"):
                        songIndex -= 1;
                        clip.close();
                        songPath = "resources/" + songsList.get(songIndex).getName() + ".wav";
                        playSong(songPath);
                        break;
                    /*case("M"):
                        clip.close();
                        String[] arg = new String[0];
                        JukeBoxImplementation.main(arg);
                        flag = true;
                        break;*/
                    default:
                        System.err.println("PLEASE SELECT THE CORRECT OPTION");
                        response = scanner.next();
                        response = response.toUpperCase();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }
}