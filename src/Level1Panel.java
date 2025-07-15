import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1Panel extends GamePanel {
    public Level1Panel() {
        // Kein requestFocusInWindow nötig, wird in GamePanel erledigt
    }
    @Override
    protected void initBlocks() {
        blocks = new ArrayList<>();
        int blockWidth = 60, blockHeight = 30;
        int abstandX = 20, abstandY = 15;
        int startX = 30, startY = 50;
        for (int reihe = 0; reihe < 3; reihe++) {
            for (int spalte = 0; spalte < 6; spalte++) {
                int x = startX + spalte * (blockWidth + abstandX);
                int y = startY + reihe * (blockHeight + abstandY);
                blocks.add(new Rectangle(x, y, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Level-Anzeige oben rechts, damit sie nicht mit "Leben" kollidiert
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        String levelText = "Level 1";
        int textWidth = g.getFontMetrics().stringWidth(levelText);
        g.drawString(levelText, getWidth() - textWidth - 20, 30);
    }


}
