import javax.swing.*;

public class Breakout {
    public static void main(String[] args) {
        // Das Fenster mit dem GamePanel erstellen
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ball und Paddle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setResizable(false);
            GamePanel panel = new Level1Panel();  // Oder das gewünschte Level
            frame.add(panel);
            frame.setLocationRelativeTo(null); // Fenster zentrieren
            frame.setVisible(true);
            panel.requestFocusInWindow(); // Fokus explizit setzen
        });
    }
}
