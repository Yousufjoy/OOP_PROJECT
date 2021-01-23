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
    int flappyV = 0, flappyA = 4; // velocity, Accelartion


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
        drawFlappy(g);
    }

    public void actionPerformed(ActionEvent e){   // ActionEvent = When the user clicks a button

        flappyV += flappyA;

        repaint();
    }

    private void drawFlappy(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(75,flappyHeight+flappyV,50,50);
    }

    public void keyTyped(KeyEvent e){  // KeyEvent = when a key is pressed, released, or typed.

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Setting acceleration
        if(code == e.VK_SPACE){
            flappyV = -4;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
