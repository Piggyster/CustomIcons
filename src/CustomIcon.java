import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CustomIcon {

    private String text;
    private Color color;
    private Color darkColor;
    private Cursor cursor;

    public CustomIcon(String text, Color color) {
        this.text = text;
        this.color = color;
        cursor = new Cursor();
        generate();
    }

    public void generate() {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        for(int x = 0; x <= getWidth(); x++) {
            for(int y = 0; y <= getHeight(); y++) {
                Color background = getBackground(x, y);
                if(background == null) {
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(background);
                }
                g2d.fillRect(x, y, 1, 1);
            }
        }


        g2d.setColor(Color.BLACK);
        cursor.set(4, 2);

        int xOffset = 0;

        for(char c : text.toCharArray()) {
            Letter letter = Letter.get(c);
            for(int i = 0; i < letter.getPattern().length; i++) {
                for(int j = 0; j < letter.getPattern()[0].length; j++) {
                    boolean b = letter.getPattern()[i][j];
                    if(b) {
                        g2d.fillRect(cursor.getX(), cursor.getY(), 1, 1);
                    }
                    cursor.moveRight();
                }
                cursor.moveDown();
                cursor.set(4 + xOffset, cursor.getY());
            }
            xOffset += letter.getWidth() + 1;
            cursor.set(4 + xOffset, 2);
        }

        g2d.dispose();

        bufferedImage = ImageUtils.imageToBufferedImage(ImageUtils.makeColorTransparent(bufferedImage, Color.BLACK));

        File file = new File(text + ".png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    private Color getBackground(int x, int y) {
        if(
                (x == 0 && y == 0)
                || (x == 0 && y == getHeight() - 1)
                || (x == getWidth() - 1 && y == 0)
                || (x == getWidth() - 1 && y == getHeight() - 1)
        ) {
            return null;
        } else if(
                y == 0
                || y == getHeight() - 1
                || x == 0
                || x == getWidth() - 1
        ) {
            return getDarkColor();
        } else if (
                (x == 1 && y == 1)
                || (x == 1 && y == getHeight() - 2)
                || (x == getWidth() - 2 && y == 1)
                || (x == getWidth() - 2 && y == getHeight() - 2)
        ) {
            return getDarkColor();
        } else {
            return color;
        }
    }

    public int getHeight() {
        return 9;
    }

    public int getWidth() {
        int total = 7 + text.length();
        for(char c : text.toCharArray()) {
            total += Letter.get(c).getWidth();
        }
        return total;
    }

    public Color getColor() {
        return color;
    }

    public Color getDarkColor() {
        if(darkColor != null) return darkColor;
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        darkColor = new Color(Color.HSBtoRGB(hsb[0], Math.min(hsb[1] + 0.1f, 1f), Math.max(hsb[2] - 0.2f, 0f)));
        return darkColor;
    }
}
