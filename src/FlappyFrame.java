import javax.swing.JFrame;
import java.io.*;
import java.net.Socket;


public class FlappyFrame extends JFrame {

    public FlappyFrame(){

        // Adding the panel

        add(new FlappyPanel());

        setSize(500,500); 
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Everything we do that involves swing is the exact copy of this

    public static void main(String[] args) throws IOException {
        new FlappyFrame();
        BufferedReader reader;
        BufferedWriter writer;
        Socket socket = new Socket("127.0.0.1", 5000);

        OutputStreamWriter o = new OutputStreamWriter(socket.getOutputStream());
        writer = new BufferedWriter(o);

        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(isr);
        Thread t = new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(10000);
                    System.out.println(FlappyPanel.score);
                    writer.write(String.valueOf(FlappyPanel.score));
                    writer.newLine();
                    writer.flush();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }

}




