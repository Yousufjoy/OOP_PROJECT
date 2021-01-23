import javax.swing.JFrame;


public class FlappyFrame extends JFrame {

    public FlappyFrame(){

        // Adding the panel

        add(new FlappyPanel());

        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Everything we do that involves swing is the exact copy of this

    public static void main(String[] args) {
        new FlappyFrame();
    }

}
