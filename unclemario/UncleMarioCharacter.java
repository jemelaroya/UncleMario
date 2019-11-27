package unclemario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Timer;

    public class UncleMarioCharacter {
        static int DIAMETER = 20; //Diameter of the Uncle Mario.
        static int X = ( Game.WIDTH / 2 ) - ( DIAMETER / 2 ); //X position of the Uncle Mario. Does not change at any time.
        static int y = Game.HEIGHT / 2; //Y position of the Uncle Mario. Will change constantly.
        static int acceleration = 1; //Gravity Simulation.
        static int speed = 2; //Speed at which the Uncle Mario will fall.
        
//Fetching character.png from Imgur where it's hosted.
        static BufferedImage img = null;{
            try {
                img = ImageIO.read(new URL("https://i.imgur.com/S5s9WHI.png"));
            } catch (IOException e) {
                System.out.println("NO CHARACTER"); //Prints "NO CHARACTER" if there is an error retrieving the image.
            }
        }

    public UncleMarioCharacter(){
    }

//Called when the Uncle Mario jumps. It just temporarily sets the speed to -15 (arbitrary number).
    public void jump(){
        speed = - 15;
    }

    public static void move(){
//Called when Uncle Mario move between the top and bottom of the window.
    if ( ( y > 0 ) && ( y < Game.HEIGHT )) {
        speed += acceleration; //Code for the gravity. The speed is just increased by 1 all the time, even after a jump.
        y += speed; //The actual movement, Y equals (where it was) + (how far it should go)
    }
    else {
        reset(); //Resets Character's postion.
        Game.dead = true; //This is used in the Main method to reset the walls after a death.
        }
    }

    public static void reset(){ //Called after Uncle Mario dies.
        y = Game.HEIGHT / 2; //Resets position, speed, etc.
        speed = 2;
        Game.score = 0;
        Game.deathMessage = "Dead! Try Again!"; //Shows the Death Message.
//This timer makes the death message disappear after 3000 ms.
        Timer deathTimer = new Timer(3000, new ActionListener(){
        public void actionPerformed(ActionEvent event){
        Game.deathMessage = "";
            };
        }
    );
    deathTimer.start();
}

    public static void paint(Graphics g){
        g.drawImage(img, X, y, null); //Paints UncleMario's icon
    }

    public static Rectangle getBounds(){
        return new Rectangle(X, y, DIAMETER, DIAMETER); //Gives a rectangle used to detect collisions in the Wall class.
    }
}
