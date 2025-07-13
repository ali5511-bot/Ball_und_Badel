import javax.swing.*;

public class Breakout {
    public static void main(String[] args) {
        // Das Fenster mit dem GamePanel erstellen
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ball und Paddle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            GamePanel panel = new Level1Panel();  // Oder das gewünschte Level
            frame.add(panel);
            frame.setVisible(true);

            // Fokus , nachdem das Fenster sichtbar
        });
    }
}
