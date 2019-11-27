package unclemario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

    public class Pipe {
        Random rnd = new Random(); //Generates a random height for gap.
        int x ; //X position of the Pipe, always changing (right to left).
        int y = rnd.nextInt(Game.HEIGHT - 400) + 200; //Generates the Height of Y value that is the top of the bottom Pipe.
        static int speed = -5; //Scrolling speed.
        int WIDTH = 50; //Constant width of the pipe.
        int height = Game.HEIGHT - y; //Height of the pipe.
        int GAP = 150; //Constant gap size.

//Fetching pipe.png from Imgur where it's hosted.
        static BufferedImage img = null;{
            try {
                img = ImageIO.read(new URL("https://imgur.com/uSDRI7H.png"));
            } catch (IOException e) {
                System.out.println("WRONG WALL"); //Prints "NO PIPE" if there is an error retrieving the image.
            }
        }

    public Pipe(int i){
        this.x = i;
        }

//Paints the Pipes.
    public void paint(Graphics g){
        g.drawImage(img, x, y, null); //Top Pipe
        g.drawImage(img, x, ( -Game.HEIGHT ) + ( y - GAP), null); //Bottom Pipe
    }

    public void move(){
        x += speed; //Scrolls the Pipe
//Used to detect collisions.
        Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);
        Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Game.HEIGHT - (height + GAP));
//If Uncle Mario collides with a pipe, he dies and the game, character, and pipe will reset.
        if ( (wallBounds.intersects(UncleMarioCharacter.getBounds()) ) || (wallBoundsTop.intersects(UncleMarioCharacter.getBounds()))){
            UncleMarioCharacter.reset();
            died();
        }
//This is the Loop for the pipes.
        if (x <= 0 - WIDTH){
            x = Game.WIDTH;
            y = rnd.nextInt(Game.HEIGHT - 400) + 200;
            height = Game.HEIGHT - y;
        }
    }
    
//This is executed on death, just sets a random y value and tells Game that the bird died ??
    public void died(){
        y = rnd.nextInt(Game.HEIGHT - 400) + 200;
        height = Game.HEIGHT - y;
        Game.dead = true;
    }
}