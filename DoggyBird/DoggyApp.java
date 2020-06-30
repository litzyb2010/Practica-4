import javax.swing.*;

public class DoggyApp{

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("CHEEMS FLY");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);

    }
}
