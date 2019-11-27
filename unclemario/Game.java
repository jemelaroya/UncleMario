package unclemario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
    public class Game extends JPanel{
        static int HEIGHT = 800; //Height of the window.
        static int WIDTH = 600; //Width of the window.
        static void setColor(Color WHITE) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        UncleMarioCharacter character = new UncleMarioCharacter(); //Make a new character.
        Pipe pipe = new Pipe(WIDTH); //Makes the First Pipe you see.
        Pipe pipe2 = new Pipe(WIDTH + (WIDTH / 2)); //Makes the Second Pipe you see.
        static int score = 0; //Score.
        int scrollX = 0; //Scrolls the background.
        static boolean dead = false; //Used to reset the walls
        static String deathMessage = "" ; // "you died, try again";

//Fetching pipe.png from Imgur where it's hosted.
        BufferedImage img = null;{
            try {
                img = ImageIO.read(new URL("https://imgur.com/EfctVyI.png"));
            } catch (IOException e) {
                System.out.println("WRONG BACKGROUND"); //prints "WRONG BACKGROUND" if there is an issue obtaining the background
            }
        }

        public Game(){

//This mouseAdapter just listens for clicks, where upon it then tells the character to jump.
        this.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent arg0) {
        character.jump();
            }
        }
    );
}

@SuppressWarnings("static-access")
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img, scrollX, 0, null); //There are two backgrounds so you get that seamless transition, this is the first
        g.drawImage(img, scrollX + 1800, 0, null); //Second background.
        pipe.paint(g); //Paints the First Pipe.
        pipe2.paint(g); //Second pipe.
        character.paint(g); //Paints the Uncle Mario.
        g.setColor(Color.GREEN);
        g.setFont(new Font("comicsans", Font.BOLD, 40));
        g.drawString("" + score, WIDTH / 2 - 20, 700);
        g.drawString(deathMessage, 200, 200); //paints "" if the player has not just died, paints "you died, try again" if the user just died
    }

@SuppressWarnings("static-access")
    public void move(){
        pipe.move(); //Moves the First pipe.
        pipe2.move(); //Moves the Second pipe.
        character.move(); //Moves the character.
        scrollX += pipe.speed; //Scrolls the background.
    if (scrollX == -1800) //This loops the background around after it's done
        scrollX = 0;
    if (dead){ //this block essentially pushes the walls back 600 pixels on character's death.
        pipe.x = 600;
        pipe2.x = 600 + (WIDTH / 2);
        dead = false;
    }
    if ( (pipe.x == UncleMarioCharacter.X) || (pipe2.x == UncleMarioCharacter.X) ) //Increments the score when the player passes a wall.
        score();
    }

    public static void score(){
        score += 1;
    }
}