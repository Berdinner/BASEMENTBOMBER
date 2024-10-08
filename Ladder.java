
/**
 * Code for ladders.
 *
 * @author BAXTER BERDINNER
 * @version 18/05/2023
 */

import java.awt.*;
import javax.swing.*;

public class Ladder {
    int CHUNK = Panel.CHUNK;

    int x, y, level;
    int offset = 24;

    Image ladderImg = new ImageIcon("ladder.png").getImage();

    Boolean ladderBroken = false;

    Rectangle col;
    Rectangle topCol;
    Rectangle bottomCol;
    Rectangle leftCol;
    Rectangle rightCol;

    public Ladder(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;

        col = new Rectangle(x - offset, y - offset * 2, ladderImg.getWidth(null) + offset * 2,
                ladderImg.getHeight(null) + offset * 2);

        topCol = new Rectangle(col.x, col.y - offset, col.width, offset * 4);
        bottomCol = new Rectangle(col.x, col.y + col.height - Panel.playerHeight, col.width, offset * 4);

        // ladderTop and ladderBottom offset above and below ladder.
        leftCol = new Rectangle(col.x - CHUNK + CHUNK / 4, col.y, CHUNK, col.height);
        rightCol = new Rectangle(col.x + col.width - CHUNK / 4, col.y, CHUNK, col.height);
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if (!ladderBroken) {
            if (Panel.lastRoom < level || Panel.lastRoom > level || Panel.gameOver)
                // Triggers when player is one room above or below ladder.
                ladderImg = new ImageIcon("ladderDark.png").getImage();
            if (!Panel.gameOver || level == 0) {
                if (Panel.lastRoom == level)
                    // Triggers when player is in the same room as ladder.
                    ladderImg = new ImageIcon("ladderDarkTop.png").getImage();
                if (Panel.lastRoom == level - 1 || level == 0 && Panel.gameOver)
                    // Triggers when player is one room above player. Overrides ladderDark.
                    ladderImg = new ImageIcon("ladderDarkBottom.png").getImage();
                if (Panel.lastRoom == level && level == 0)
                    // Triggers when player is in only room0.
                    ladderImg = new ImageIcon("ladder.png").getImage();
            }
        } else { // Broken ladder variants.
            if (Panel.lastRoom < level || Panel.lastRoom > level || Panel.gameOver)
                ladderImg = new ImageIcon("ladderBrokenDark.png").getImage();
            if (!Panel.gameOver || level ==0) {
                if (Panel.lastRoom == level)
                    ladderImg = new ImageIcon("ladderBrokenDarkTop.png").getImage();
                if (Panel.lastRoom == level - 1 || level == 0 && Panel.gameOver)
                    ladderImg = new ImageIcon("ladderBrokenDarkBottom.png").getImage();
                if (Panel.lastRoom == level && level == 0)
                    ladderImg = new ImageIcon("ladderBroken.png").getImage();
            }
        }

        g2D.drawImage(ladderImg, x + Panel.damageWobbleX, y + Panel.parallax + Panel.damageWobbleY, null);
    }
}