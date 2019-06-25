package jpotify.view.centerpanel;

import jpotify.view.ImagePanel;
import jpotify.view.Listeners.NewSongIsPlayed;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Jsong extends JPanel implements MouseListener {

    public static final int WIDTH = CenterPanelView.WIDTH - 100 , HEIGHT = 190 ,BORDER= 1;
    public static final Dimension DIMENSION = new Dimension(ImagePanel.JSONG ,ImagePanel.JSONG);
    private ImagePanel artwork;
    private JPanel infoPanel = new JPanel();
    private JLabel artist = new JLabel();
    private JLabel title = new JLabel();
    private JLabel album = new JLabel();
    private NewSongIsPlayed songIsPlayed;
    private JButton delete = new JButton();

    public Jsong(String ttl , String rtst, String lbm, String rtwrk) {
        this(ttl, rtst, lbm, new ImageIcon(rtwrk).getImage());
    }

    public Jsong(String ttl, String rtst, String lbm, Image img){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(14,14,14));
        artwork = new ImagePanel(img, DIMENSION);
        artwork.setBorder(BorderFactory.createMatteBorder(BORDER,BORDER,BORDER,BORDER,Color.white));
        this.add(artwork, BorderLayout.WEST);
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(delete, BorderLayout.EAST);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./assets/Remove.png")));
            delete.setIcon(new ImageIcon(icon.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        delete.addMouseListener(this);
        delete.setBorder(null);
        delete.setBackground(new Color(14,14,14));

        mySetBorder(new Color(14,14,14));

        infoPanel.setBackground(new Color(14,14,14));
        infoPanel.setLayout(new GridLayout(3,1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        //TODO add icons and add songs
        infoPanel.add(title);
        infoPanel.add(artist);
        infoPanel.add(album);
        title.setText(ttl);
        title.setForeground(Color.lightGray);
        artist.setText(rtst);
        artist.setForeground(Color.lightGray);
        album.setText(lbm);
        album.setForeground(Color.lightGray);
        this.add(infoPanel, BorderLayout.CENTER);

        this.addMouseListener(this);

        this.setVisible(true);
    }

    public void setSongIsPlayed(NewSongIsPlayed sg){
        songIsPlayed = sg;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this)){
            System.out.println("play");
            //TODO play new song
        }
        if (e.getSource().equals(delete)){
            System.out.println("delete");
            //TODO delete Song
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(delete))
            delete.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red));
        else {
            this.setBackground(Color.BLACK);
            mySetBorder(Color.black);
            delete.setBackground(Color.BLACK);
            infoPanel.setBackground(Color.BLACK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(delete))
            delete.setBorder(null);
        else {
            this.setBackground(new Color(14, 14, 14));
            mySetBorder(new Color(14,14,14));
            delete.setBackground(new Color(14,14,14));
            infoPanel.setBackground(new Color(14,14,14));
        }
    }

    private void mySetBorder(Color c){
        Border outerB = BorderFactory.createMatteBorder(0,15,5,0, c);
        Border whiteLineB = BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
        Border inerB = BorderFactory.createMatteBorder(0,0,5,0, c);
        Border complexB = BorderFactory.createCompoundBorder(whiteLineB, inerB);
        this.setBorder(BorderFactory.createCompoundBorder(outerB, complexB));
    }
}
