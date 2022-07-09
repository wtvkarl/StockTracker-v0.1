package stocktracker;

import javax.swing.JFrame;

public class App {

    public static InputHandler inputHandler;

    public static final int width = 1000 , height = 600;

    public static void main(String[] args) {
        JFrame window = new JFrame("Stock Tracker v 0.1");
        Screen screen = new Screen(width,height);
        inputHandler = new InputHandler();

        window.setFocusable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(screen);
        window.pack();
        window.addKeyListener(inputHandler);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        screen.startThread();
    }
}
