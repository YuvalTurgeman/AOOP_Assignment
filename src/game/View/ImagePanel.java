/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type ImagePanel, extends JPanel.
 * @methods: ctor, setBackgroundImage, paintComponents.
 **/

package game.View;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;


public class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel() {
        // Default constructor, no image set initially
    }

    public void setBackgroundImage(String fileName) {
        try {
            this.backgroundImage = new ImageIcon(fileName).getImage();
            repaint(); // Repaint the panel to show the new background image
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
