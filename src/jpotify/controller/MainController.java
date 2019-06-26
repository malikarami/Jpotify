package jpotify.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import helper.FileHelper;
import javazoom.jl.decoder.JavaLayerException;
import jpotify.model.CustomPlayer;
import jpotify.model.Song;
import jpotify.model.User;
import jpotify.model.Users;
import jpotify.view.MainView;
import jpotify.view.centerpanel.JSong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    public static final int PLAYLIST = 2, MYSONG = 0, ALBUMS = 1;
    public static final int PLAY_BUTTON=1;
    private MainView mainView;
    private Users users;
    private User user;
    private CustomPlayer player;
    private int volume = 0;

    public MainController(Users users, int userIndex) {
        mainView = new MainView(this);
        this.users = users;
        this.user = users.getUser(userIndex);
    }

    public void addSongToLibrary(File... files) {
        for (File f :
                files) {
            try {
                user.addSong(new Song(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<JSong> getJSongs() throws IOException {
        ArrayList<JSong> jSongs = new ArrayList<>();
        List<Song> songs = user.getLibrarySongs();
//        System.out.println(songs);
        for (int i = 0; i < songs.size(); i++) {
            jSongs.add(new JSong(this, songs.get(i).getTitle(), songs.get(i).getArtist(), songs.get(i).getAlbum(), songs.get(i).getArtwork(),i ));
        }
        return jSongs;
    }

    public void mySongIsOn(boolean b) {
        user.setCurrentSelectedListInGUI(user.getLibrary());
    }

    public void playSelectedSong(int index) {
        user.setCurrentList();
        Song song = user.playSong(index);
        try {
            if (player != null)
                player.stop();
            player = new CustomPlayer(song.getAddress(), this);
            player.play();
            player.setVolume(volume);

        } catch (JavaLayerException | IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        mainView.changeArtwork(song.getArtwork());
    }

    public void updateJSlider(int state){
        mainView.getBottomPanelView().getControlPanel().getControlBar().setValue(state);
        mainView.getBottomPanelView().getControlPanel().revalidate();
    }

    public void changeCenterPanel(int mode, ArrayList information){
           switch (mode){
               case ALBUMS:
                   mainView.getCenterPanelView().displayPanel(mode, information);
               case MYSONG:
                   mainView.getCenterPanelView().displayPanel(mode, information);
               case PLAYLIST:
                   mainView.getCenterPanelView().displayPanel(mode, information);
           }
    }

    public void controlButtonHandler(int mode){
        switch (mode){
            case PLAY_BUTTON:
                mainView.getBottomPanelView().getControlPanel().changeButton(mode);
        }
    }

    public void pause(boolean paused) {
        if (paused){
            player.pause();
        }
        else
            player.play();
    }

    public void pausePlayerForSeek() {
        if (player != null)
            player.pause();
    }

    public void resumePlayerForSeek() {
        if (player != null)
            player.resume();
    }

    public void sliderChanged(int value) {
        if (player != null) {
            try {
                player.changePositionHundred(value);
                player.setVolume(volume);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
        mainView.getBottomPanelView().getVolumeControlPanelView().changeVolume();
    }

    public void saveState() {
//        Users users = new Users();
//        users.addUser(user);
        try {
            FileHelper.saveUsers(this.users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeVolume(int value) {
        volume = value;
        if (player != null)
            player.setVolume(value);
    }
}
