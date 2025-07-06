package PlinkoBalls;

import java.awt.*;

public class Peg {
    private int x;
    private int y;
    private double radius;
    private int animationCount;

    public Peg(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.animationCount = 0;
    }

    public void paintPeg(Graphics2D g2d) {
        if (animationCount > 0) {
            int scale = (int) (-animationCount * 3.75 + 30);
            g2d.setColor(new Color((int) (100 - scale*1.75), (int) (108 - scale*1.75), (int) (120 - scale*1.75)));
            g2d.fillOval((x - (int)Math.round(radius)) - scale/2, (y - (int)Math.round(radius)) - scale/2, ((int)Math.round(radius) * 2) + scale, ((int)Math.round(radius) * 2) + scale);
            animationCount--;
        }

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x - (int)Math.round(radius), y - (int)Math.round(radius), (int)Math.round(radius) * 2, (int)Math.round(radius) * 2);
    }

    public void animateHit() {
        animationCount = 8;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }
}
