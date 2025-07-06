package PlinkoBalls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlinkoTest extends JPanel {

    private int gameWidth;
    private int gameHeight;
    private Objects objects;
    public PlinkoTest(GameDimensions dimensions) {
        this.gameWidth = dimensions.getGameWidth();
        this.gameHeight = dimensions.getGameHeight();
        objects = new Objects(dimensions);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"), "pressSpace");
        getActionMap().put("pressSpace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.addBall(dimensions.getBallXVar()+ (Math.random()-0.5) * 90 * dimensions.getMultiplier(), dimensions.getBallHeight(), dimensions.getBallRadius(), 0, 0.7 * dimensions.getMultiplier(), 0.5, gameHeight);
            }   //+ (Math.random()-0.5) * 33   // + (Math.random() * 70)
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "pressE");
        getActionMap().put("pressE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.raiseBallCost();
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "pressW");
        getActionMap().put("pressW", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.lowerBallCost();
            }
        });


        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();
    }

    public void update() {
        objects.moveBalls();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(14,30,43));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        //g2d.setColor(Color.RED);
        //g2d.fillOval(300 - 2, 682 - 2, 4, 4);
        //g2d.setColor(Color.RED);
        //g2d.fillOval(350 - 2, 200 - 2, 4, 4);

        objects.paintGame(g2d);
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

}
