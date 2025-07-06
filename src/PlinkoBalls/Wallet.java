package PlinkoBalls;

import java.awt.*;

public class Wallet {
    private double amount;
    private GameDimensions dimensions;

    public Wallet(GameDimensions dimensions) {
        this.amount = 2000;
        this.dimensions = dimensions;
    }

    public void paintWallet(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, (int)(50 * dimensions.getMultiplier())));
        g2d.drawString("" + amount, (int)(75 * dimensions.getMultiplier()), (int)(75 * dimensions.getMultiplier()));
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }

    public void removeAmount(double amount) {
        this.amount -= amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
