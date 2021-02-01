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
    int flappyHeight = HEIGHT / 4;
    int flappyV = 0;
    int flappyA = 8;
    int flappyI = 1;
    public static int score = 0;
    final int WALLXVELOCITY = 5;
    final int WALLWIDHT = 50;
    int [] wallx = {WIDTH, WIDTH + WIDTH/2}; // for the wall
    int [] gap ={(int)(Math.random()*HEIGHT),(int)(Math.random()*HEIGHT)}; // for the gap between walls


    boolean gameOver = false;

    Timer time = new Timer(40,this);


    public FlappyPanel(){
        setSize(WIDTH,HEIGHT);
        setFocusable(true); //  to gain the power of getting focused
        addKeyListener(this);

        setBackground(Color.CYAN);

    }

    private void drawFlappy(Graphics g){ // this will creat the bird
        g.setColor(Color.BLUE);


        g.fillRect(75,flappyHeight+flappyV,25,25);
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Setting acceleration-going up
        if(code == e.VK_SPACE){
            flappyA = -8;

        }

        // For not instantly starting the game
        if(code == e.VK_S){
            time.start();

        }

        // for Restart

        if(code == e.VK_R){
            time.stop();
            flappyHeight = HEIGHT / 4;
            flappyV = 0;
            flappyA = 8; // velocity, Accelartion
            score = 0;
            wallx[0] = WIDTH;
            wallx[1] = WIDTH + WIDTH/2;
            gap  [0]  = (int)(Math.random()*HEIGHT-150);
            gap  [1]  = (int)(Math.random()*HEIGHT-150);


            gameOver = false;

          repaint();

        }
    }

    public void actionPerformed(ActionEvent e){   // ActionEvent = When the user clicks a button


        flappyV = flappyV + flappyA; // for every  frame there is we say because of this when we press space the bird is going up
        flappyA = flappyA + flappyI;  //
        wallx[0] = wallx[0]-WALLXVELOCITY;
        wallx[1] = wallx[1]-WALLXVELOCITY;

        repaint();
    }

    private void drawWall(Graphics g){

        for(int i=0; i<2; i++) {
            g.setColor(Color.GRAY);
            g.fillRect(wallx[i], 0, WALLWIDHT, HEIGHT);

            g.setColor(Color.CYAN);
            g.fillRect(wallx[i], gap[i], WALLWIDHT, 100);
        }


    }



    public void paintComponent(Graphics g){ // this will creat the bird
        super.paintComponent(g); // If we don't we gonna notice the square appearing everywhere


        if(!gameOver) {
            g.setColor(Color.black);
            g.drawString("SCORE "+ score,WIDTH/2,10);
            drawWall(g);
            logic();
            drawFlappy(g);
        } else {
            g.setColor(Color.black);
            g.drawString("SCORE "+ score,WIDTH/2,10);

        }


    }
    

    private void logic(){

        for(int i=0; i<2; i++) {



            // this dies if the flappy goes through
            if(wallx[i] <=100 && wallx [i]+ WALLWIDHT >= 100){
                if((flappyHeight+flappyV)>=0 && (flappyHeight+ flappyV)<= gap[i] || (flappyHeight+flappyV+25)>=gap[i]+100 && (flappyHeight+flappyV+25)
                <=HEIGHT){
                    gameOver = true;
                }
            }


            if(flappyHeight + flappyV <=0 || flappyHeight+flappyV +25 >=HEIGHT){
                gameOver = true;
            }

            if(75 > wallx[i] + WALLWIDHT){
                score++;
            }

            if(wallx[i] + WALLWIDHT <=0){

                wallx[i] = WIDTH;
                gap[i] = (int)(Math.random()*(HEIGHT-150));

            }
        }

    }



    public void keyTyped(KeyEvent e){  // KeyEvent = when a key is pressed, released, or typed.

    }


    @Override
    public void keyReleased(KeyEvent e) {


    }
}
