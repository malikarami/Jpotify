package jpotify.view.leftpanel;

import jpotify.view.MyBorder;

import javax.swing.*;
import java.awt.*;

public class LibraryBar extends JPanel {

    private static final int ELEMENTS_SIZE = 10;

//    private JButton addSong = new JButton();
//    private JButton songs = new JButton();
//    private JButton albums = new JButton();

    private MyBorder border;

    public LibraryBar() {

        //overall sets
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));

//        this.setBackground(Color.white);
//        this.setBorder(BorderFactory.createTitledBorder("Library"));
////        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.setLayout(new GridLayout(3, 1));
//
//        try {
//            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Add.png")));
//            addSong.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Music Note.png")));
//            songs.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Album.png")));
//            albums.setIcon(new ImageIcon(icon.getImage().getScaledInstance(ELEMENTS_SIZE, ELEMENTS_SIZE, Image.SCALE_AREA_AVERAGING)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        addSong.setText("Add song");
//        songs.setText("Songs");
//        albums.setText("Albums");
//        for (JButton b :
//                new JButton[]{addSong, songs, albums}) {
//            b.setHorizontalAlignment(SwingConstants.LEFT);
//            b.setPreferredSize(new Dimension(LeftPanelView.WIDTH - 10, 30));
//            b.setBackground(Color.white);
//            this.add(b);
//        }

        this.setPreferredSize(new Dimension(LeftPanelView.WIDTH, LeftPanelView.ELEMENTS_HEIGHT));
        this.setVisible(true);
    }
}
