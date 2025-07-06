package PlinkoBalls;

import java.awt.*;

public class Multiplier {
    private int x;
    private int y;
    private int length;
    private int pos;
    private double multiplication;
    private Color color;
    private int animationCount;
    private GameDimensions dimensions;

    public Multiplier(int x, int y, int length, int pos, GameDimensions dimensions) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.pos = pos;
        this.multiplication = MultiplierTypes.fromPos(pos).getMultiplier();
        this.color = MultiplierTypes.fromPos(pos).getColor();
        this.animationCount = 0;
        this.dimensions = dimensions;
    }

    public void paintMultiplier(Graphics2D g2d) {
        int move = 0;
        if (animationCount > 0) {
            move = (int) (- (1.0 / 1.8) * Math.pow(animationCount - 6, 2) + 20);
            animationCount--;
        }
        g2d.setColor(color);
        g2d.fillRect(x + (int)(5* dimensions.getMultiplier()), y + (int)((25 + move)* dimensions.getMultiplier()), length-(int)(10 * dimensions.getMultiplier()), length-(int)(10 * dimensions.getMultiplier()));

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, (int)(15 * dimensions.getMultiplier())));
        g2d.drawString("" + multiplication, x + (int)(10 * dimensions.getMultiplier()), y + length/2 + (int)((25 + move)* dimensions.getMultiplier()));
    }

    public void animateHit() {
        animationCount = 12;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public int getPos() {
        return pos;
    }
}
