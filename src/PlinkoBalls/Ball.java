package PlinkoBalls;

import java.awt.*;
import java.util.List;

public class Ball {
    private double x;
    private double y;
    private double radius;
    private double velocityX;
    private double velocityY;
    private double accelerationY;
    private double dampening;
    private Collision collision;
    private double amount;

    public Ball(double x, double y, double radius, double velocityY, double accelerationY, double dampening, List<Peg> pegs, List<Multiplier> multipliers, int ground, double amount) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = 0;
        this.velocityY = velocityY;
        this.accelerationY = accelerationY;
        this.dampening = dampening;
        this.collision = new Collision(this, pegs, multipliers, ground);
        this.amount = amount;
    }

    public int moveBall() {
        velocityY += accelerationY;

        velocityX *= 0.885;

        //0.886

        if (collision.checkPegCollision()) {
            collision.checkGroundCollision();
            return collision.checkMultiplierCollision();
        }

        x += velocityX;
        y += velocityY;

        collision.checkGroundCollision();
        return collision.checkMultiplierCollision();
    }

    public void bounceBall() {
        velocityY = -velocityY * dampening;
    }

    public void paintBall(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int)Math.round(x - radius), (int)Math.round(y - radius), (int)Math.round(radius) * 2, (int)Math.round(radius) * 2);

        //g2d.setColor(Color.RED);
        //g2d.fillOval((int)Math.round(x-2), (int)Math.round(y-2), 4, 4);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
