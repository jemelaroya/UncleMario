package unclemario;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

    public class Menu extends JPanel{
        private static final long serialVersionUID = 1L;    
        int highscore;
        
////Fetching background.png from Imgur where it's hosted.
        static BufferedImage img = null;{
            try {
                img = ImageIO.read(new URL("https://imgur.com/2EFRmfr.png"));
            } catch (IOException e) {
                System.out.println("WRONG MENU");
            }
        }
        
    boolean startGame = false; //The boolean toggle that starts the game over in ExecuteMe


    public Menu(){
        setFocusable(true); //waits for a mouseclick, then toggles startGame
        addMouseListener(new MouseAdapter(){
            
    @Override
    public void mouseClicked(MouseEvent e) {
        startGame = true;
            }
        }
    );
}

    public void paint (Graphics g){
        super.paint(g);
        g.drawImage(img, 0, 0, null); //Paints the background
    }
}