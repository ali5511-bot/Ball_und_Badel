import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1Panel extends GamePanel {
public Level1Panel(){ requestFocusInWindow();
}
    @Override
    protected void initBlocks() {
        blocks = new ArrayList<>();
        int blockWidth = 60, blockHeight = 30;

        for (int reihe = 0; reihe < 3; reihe++) {
            for (int spalte = 0; spalte < 6; spalte++) {
                blocks.add(new Rectangle(30 + spalte * 80, 40 + reihe * 40, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        g.setColor(Color.BLACK);
        g.setFont(new Font("Enterprise",Font.PLAIN, 18));
        g.drawString("Level 1", 10, 20);
    }


}
