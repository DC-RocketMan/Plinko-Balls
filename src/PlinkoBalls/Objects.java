package PlinkoBalls;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Objects {
    private List<Ball> balls;
    private List<Peg> pegs;
    private List<Multiplier> multipliers;
    private Wallet wallet;
    private double ballCost;
    private GameDimensions dimensions;

    public Objects(GameDimensions dimensions) {
        this.dimensions = dimensions;
        this.balls = new ArrayList<>();
        this.pegs = pegCreation(dimensions.getGameWidth(), dimensions.getGameHeight());
        this.multipliers = multiplierCreation(dimensions.getGameWidth(), dimensions.getGameHeight());
        this.wallet = new Wallet(dimensions);
        this.ballCost = 20.0;
    }

    public void addBall(double x, double y, double radius, double velocityY, double accelerationY, double dampening, int ground) {
        if ((wallet.getAmount() - ballCost) < 0) return;
        balls.add(new Ball(x, y, radius, velocityY, accelerationY, dampening, pegs, multipliers, ground, ballCost));
        wallet.removeAmount(ballCost);
    }

    public void moveBalls() {
        List<Ball> ballsToRemove = new ArrayList<>();
        for (Ball ball : balls) {
            int multiplierPos = ball.moveBall();
            if (multiplierPos >= 0) {
                wallet.addAmount(ball.getAmount() * MultiplierTypes.fromPos(multiplierPos).getMultiplier());
                ballsToRemove.add(ball);
            }
            if (multiplierPos == -2) {
                ballsToRemove.add(ball);
            }
        }
        if (!ballsToRemove.isEmpty()) {
            balls.removeAll(ballsToRemove);
        }
    }

    public void paintGame(Graphics2D g2d) {
        pegs.forEach(peg -> peg.paintPeg(g2d));
        balls.forEach(ball -> ball.paintBall(g2d));
        multipliers.forEach(multiplier -> multiplier.paintMultiplier(g2d));
        wallet.paintWallet(g2d);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, (int)(30 * dimensions.getMultiplier())));
        g2d.drawString("Ball cost: " + ballCost, (int)(1600 * dimensions.getMultiplier()), (int)(75 * dimensions.getMultiplier()));
    }

    public List<Peg> pegCreation(int gameWidth, int gameHeight) {
        gameWidth -= 25;
        int startWidth = (gameWidth/2) - 50;
        int startHeight = gameHeight/7;
        System.out.println(startHeight);
        List<Peg> pegs = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < i + 3; j++) {
                System.out.println(startHeight + i * dimensions.getPegDistance());
                pegs.add(new Peg(startWidth + j*dimensions.getPegDistance() - i* dimensions.getPegDiagonalDistance(), startHeight + i * dimensions.getPegDistance(), dimensions.getPegRadius()));
            }
        }

        return pegs;
    }

    public List<Multiplier> multiplierCreation(int gameWidth, int gameHeight) {
        gameWidth -= 25;
        int multiplierX = ((gameWidth/2) - 50) - 15*dimensions.getPegDiagonalDistance();
        int multiplierY = ((gameHeight/7) + 15*dimensions.getPegDistance()) + 5;
        List<Multiplier> multipliers = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            multipliers.add(new Multiplier(multiplierX + i*dimensions.getPegDistance(), multiplierY, dimensions.getPegDistance(), i, dimensions));
        }
        return multipliers;
    }

    public void raiseBallCost() {
        if (wallet.getAmount() - (ballCost + 20) < 0) return;
        ballCost += 20;
    }

    public void lowerBallCost() {
        if (ballCost - 20 <= 0) return;
        ballCost -= 20;
    }
}
