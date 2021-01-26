import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FlappyPanel extends JPanel implements KeyListener, ActionListener{

    final int WIDTH = 525, HEIGHT = 550;
    final int WALLXVELOCITY = 5;
    final int WALLWIDHT = 50;
    int flappyHeight = HEIGHT / 4;
    int flappyV = 0, flappyA = 8,flappyI=1; // velocity, Accelartion
//    int [] wallx = new int [2]; // for the wall
//    int [][] gapx = new int [2][2]; // for the gap between walls

    int wallx = WIDTH + 10;
    int gap= (int)(Math.random()*HEIGHT);
    boolean gameOver = false;


    public FlappyPanel(){
        setSize(WIDTH,HEIGHT);
        setFocusable(true); //  to gain the power of getting focused
        addKeyListener(this);

        setBackground(Color.CYAN);

        //Adding frame
        new Timer(40,this).start();
    }

    public void paintComponent(Graphics g){  // If we don't we gonna notice the square appearing everywhere
        super.paintComponent(g);


        if(!gameOver) {
            drawWall(g);
            drawFlappy(g);
        } else {

        }


    }

    public void actionPerformed(ActionEvent e){   // ActionEvent = When the user clicks a button

        flappyA += flappyI;
        flappyV += flappyA;
        wallx -= WALLXVELOCITY;

        repaint();
    }

    private void drawFlappy(Graphics g){
        g.setColor(Color.BLUE);

        if(!((flappyHeight + flappyV)>= gap && (flappyHeight+flappyV+25)<=gap+100
        && (wallx <= 75)&& (wallx+WALLWIDHT >=75))) {
              gameOver = true;
        }

        g.fillRect(75,flappyHeight+flappyV,25,25);
    }

    private void drawWall(Graphics g){
        g.setColor(Color.GRAY);

        g.fillRect(wallx,0,WALLWIDHT,HEIGHT);

        g.setColor(Color.CYAN);
        g.fillRect(wallx,gap,WALLWIDHT,100);


    }

    public void keyTyped(KeyEvent e){  // KeyEvent = when a key is pressed, released, or typed.

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Setting acceleration
        if(code == e.VK_SPACE){
            flappyA = -8;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
