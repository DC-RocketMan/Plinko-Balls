package PlinkoBalls;

import javax.swing.*;

public class Main extends JPanel {
    public static void main(String[] args) {
        GameDimensions dimensions = GameDimensions.HD;
        JFrame frame = new JFrame("Plinkotest");
        PlinkoTest panel = new PlinkoTest(dimensions);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(panel.getGameWidth(), panel.getGameHeight());
        frame.setVisible(true);
    }
}