import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public abstract class GamePanel extends JPanel implements ActionListener, KeyListener {

    protected Timer timer;
    protected int ballX, ballY;
    protected int ballSize = 20;
    protected int dx = 3, dy = -3;
    protected int lives = 3;
    protected boolean gameOver = false;

    protected int paddleX;
    protected final int paddleWidth = 100, paddleHeight = 15;
    protected boolean leftPressed = false, rightPressed = false;

    protected ArrayList<Rectangle> blocks;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        resetGame();
        initBlocks();

        timer = new Timer(5, this);
        timer.start();

        SwingUtilities.invokeLater(() -> requestFocusInWindow());
    }

    protected void resetGame() {
        dx = 3;
        dy = -3;
        paddleX = (600 - paddleWidth) / 2;

        // Ball zentriert über Paddle
        ballX = paddleX + (paddleWidth - ballSize) / 2;
        ballY = getHeight() > 0 ? getHeight() - 40 - ballSize - 100 : 500;
    }

    protected abstract void initBlocks();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Hintergrund
        g.setColor(new Color(152, 50, 33));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Leben
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Leben: " + lives, 10, 20);

        // Ball
        g.setColor(new Color(153, 255, 0));
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Paddle
        g.setColor(Color.CYAN);
        g.fillRect(paddleX, getHeight() - 40, paddleWidth, paddleHeight);

        // Blöcke
        g.setColor(new Color(255, 98, 0));
        for (Rectangle block : blocks) {
            g.fillRect(block.x, block.y, block.width, block.height);
        }

        // Game Over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Paddle Bewegung
            if (leftPressed && paddleX > 0) paddleX -= 5;
            if (rightPressed && paddleX + paddleWidth < getWidth()) paddleX += 5;

            // Ball Bewegung
            ballX += dx;
            ballY += dy;

            // Wandkollision
            if (ballX <= 0 || ballX + ballSize >= getWidth()) dx = -dx;
            if (ballY <= 0) dy = -dy;

            // Paddle-Kollision
            Rectangle paddleRect = new Rectangle(paddleX, getHeight() - 40, paddleWidth, paddleHeight);
            Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);
            if (ballRect.intersects(paddleRect)) {
                dy = -dy;
                ballY = getHeight() - 40 - ballSize;
            }

            // Block-Kollision
            for (int i = 0; i < blocks.size(); i++) {
                if (ballRect.intersects(blocks.get(i))) {
                    blocks.remove(i);
                    dy = -dy;
                    break;
                }
            }

            // Alle Blöcke zerstört
            if (blocks.isEmpty()) {
                dx = 0;
                dy = 0;
                ballX = paddleX + (paddleWidth - ballSize) / 2;
                ballY = getHeight() - 40 - ballSize;
            }

            // Ball fällt raus
            if (ballY > getHeight()) {
                lives--;

                if (lives <= 0) {
                    gameOver = true;
                    timer.stop();
                    repaint();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    int response = JOptionPane.showConfirmDialog(
                            this,
                            "Game Over! Willst du nochmal spielen?",
                            "Spiel beenden",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        lives = 3;
                        gameOver = false;
                        resetGame();
                        initBlocks();
                        timer.start();
                    } else {
                        System.exit(0);
                    }
                } else {
                    resetGame();
                }
            }

            repaint();
        }
    }

    // Tastatur
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
