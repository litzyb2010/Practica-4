import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GamePanel extends JPanel implements Runnable {

    private Game game;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);



        if (!game.started) {        
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Dimitri Swank", Font.PLAIN, 60));
            g2D.drawString("Cheems Fly", 90, 100);
            g2D.setFont(new Font("JD Eugenia", Font.PLAIN, 45));
            g2D.drawString("Press SPACE to start", 45, 300);
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Heroes Legend", Font.PLAIN, 12));
            g2D.drawString("High Score: 35 ", 340, 18);
        } else {
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Heroes Legend", Font.PLAIN, 15));
            g2D.drawString("Score:  "+ Integer.toString(game.score), 0, 18);
        }

        if (game.gameover) {
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Dimitri Swank", Font.PLAIN, 60));
            g2D.drawString("GAME OVER", 90, 210);
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("JD Eugenia", Font.PLAIN, 45));
            g2D.drawString("Press R to restart", 60, 270);
        }

        if (game.paused){
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Dimitri Swank", Font.PLAIN, 35));
            g2D.drawString("Game PAUSED", 150, 100);
            g2D.drawString("II", 250, 150);
        }
    }

    public void run() {
        try {
            ReproducirSonido("lib/kirby2.wav");
            while (true) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void ReproducirSonido(String nombreSonido){
       try
            {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            } 
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex)
            {
             System.out.println("Error al reproducir el sonido.");
            }
     }


}
