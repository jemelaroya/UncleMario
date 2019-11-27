package unclemario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

    public class Main {
    static JFrame frame = new JFrame("UncleMario");
        public static void main (String [] args) throws InterruptedException{
            frame.setSize(Game.WIDTH, Game.HEIGHT); //Declares the JFrame (window).
            frame.setVisible(true); //For the JFrame to be Visible.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When you close the Program it will automatically stops.
            frame.setLocationRelativeTo(null); //Sets the Window at the middle of the Screen.
            run(); //Calles the run method.
        }

    public static void run() throws InterruptedException{
        final Menu menu = new Menu(); //The Menu.
        final Game game = new Game(); //The Game.
        Timer animationTimer = new Timer(20, new ActionListener(){ //Animation timer for the game, repaints the bird, walls, and background every 20 milliseconds
    public void actionPerformed(ActionEvent event){
        game.repaint();
        game.move();
        };
    }
);
        frame.add(menu); //Adds the Menu.
        menu.setVisible(true);
        frame.revalidate(); //Displays the Menu.
        frame.repaint();
    while (menu.startGame == false){ //Waits until the Games starts.
        Thread.sleep(10);
    }
        frame.remove(menu); //Removes Menu when mouse is clicked.
        frame.add(game); //Adds the game after the mouse is clicked
        game.setVisible(true); //Makes sure the Game is displayed.
        frame.revalidate();
        animationTimer.start(); //Begins animation timer, and the game begins!
    }
}