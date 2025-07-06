package PlinkoBalls;

import java.util.List;

public class Collision {
    private Ball ball;
    private List<Peg> pegs;
    private List<Multiplier> multipliers;
    private int ground;

    public Collision(Ball ball, List<Peg> pegs, List<Multiplier> multipliers, int ground) {
        this.ball = ball;
        this.pegs = pegs;
        this.multipliers = multipliers;
        this.ground = ground - 40;
    }

    public int checkMultiplierCollision() {
        Multiplier checkMultiplier = multipliers.getFirst();

        if ((ball.getY()) < checkMultiplier.getY()) return -1;

        for (Multiplier multiplier : multipliers) {
            if (ball.getX() >= multiplier.getX() && ball.getX() <= (multiplier.getX() + multiplier.getLength())) {
                multiplier.animateHit();
                return multiplier.getPos();
            }
        }
        return -2;
    }
    public void checkGroundCollision() {
        if ((ball.getY() + ball.getRadius()) >= ground) {
            ball.setY(ground - ball.getRadius());
            ball.bounceBall();
        }
    }

    public double timeOfImpact(double dx, double dy, double vx, double vy, double sumR) {
        double a = vx * vx + vy * vy;
        double b = 2 * (vx * dx + vy * dy);
        double c = dx * dx + dy * dy - sumR * sumR;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0 || a == 0) return -1;

        double sqrt = Math.sqrt(discriminant);
        double t1 = (-b - sqrt)/(2 * a);
        double t2 = (-b + sqrt)/(2 * a);
        if (0 <= t1 && t1 <= 1) return t1;
        if (0 <= t2 && t2 <= 1) return t2;
        return -1;
    }

    public boolean checkPegCollision() {

        for (Peg peg : pegs) {
            double dx = ball.getX() - peg.getX();
            double dy = ball.getY() - peg.getY();
            double vx = ball.getVelocityX();
            double vy = ball.getVelocityY();

            double sumR = ball.getRadius() + peg.getRadius();
            double toi = timeOfImpact(dx, dy, vx, vy, sumR);

            if (toi < 0) continue;
            double collisionX = ball.getX() + vx * toi;
            double collisionY = ball.getY() + vy * toi;

            double nx = (collisionX - peg.getX()) / sumR;
            double ny = (collisionY - peg.getY()) / sumR;

            double vDot = vx * nx + vy * ny;
            vx = vx - 2 * vDot * nx;
            vy = vy - 2 * vDot * ny;



            double remaining = 1 - toi;
            ball.setX(collisionX + vx * remaining);
            ball.setY(collisionY + vy * remaining);

            if((int)(Math.random() * 25) == 10) {
                vx *= 1.28; //1.35 //1.2
                vy *= 0.85;
            } else {
                vx *= 0.38;
                vy *= 0.6;
            }

            ball.setVelocityX(vx);
            ball.setVelocityY(vy);
            peg.animateHit();
            return true;
        }
        return false;
    }
}

/*double distance = Math.hypot(dx, dy);
            double overlap = (ball.getRadius() + peg.getRadius()) - distance;
            if (distance > 0 && overlap+eps > 0) {
                double nx = dx / distance;
                double ny = dy / distance;

                double velocityX = ball.getVelocityX();
                double velocityY = ball.getVelocityY();

                double vDot = velocityX * nx  + velocityY * ny;
                if (vDot >= 0) {
                    continue;
                }

                double vDotX = velocityX - 2 * (velocityX * nx  + velocityY * ny) * nx;
                double vDotY = velocityY - 2 * (velocityX * nx  + velocityY * ny) * ny;

                ball.setVelocityX(vDotX * 0.9);
                ball.setVelocityY(vDotY * 0.9);

                ball.setX(ball.getX() + (nx * overlap));
                ball.setY(ball.getY() + (ny * overlap));

                break;

            }*/