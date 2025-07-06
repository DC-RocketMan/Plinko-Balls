package PlinkoBalls;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

public enum MultiplierTypes {
    HIGHEST(List.of(0, 16), 1000, new Color(225, 1, 55)),
    HIGH(List.of(1, 15), 130, new Color(230, 20, 50)),
    GOOD(List.of(2, 14), 26, new Color(230, 45, 42)),
    STILL_GOOD(List.of(3, 13),  9, new Color(230, 65, 30)),
    ALMOST_GOOD(List.of(4, 12), 4, new Color(230, 90, 30)),
    NICE(List.of(5, 11), 2, new Color(230, 115, 20)),
    BAD(List.of(6, 10), 0.2, new Color(235, 130, 10)),
    WORSE(List.of(7, 9), 0.2, new Color(230, 155, 7)),
    WORST(List.of(8), 0.2, new Color(230, 180, 0));

    private final List<Integer> pos;
    private final double multiplier;
    private final Color color;

    MultiplierTypes(List<Integer> pos, double multiplier, Color color) {
        this.pos = pos;
        this.multiplier = multiplier;
        this.color = color;
    }

    public static MultiplierTypes fromPos(int pos) {
        for (MultiplierTypes type : MultiplierTypes.values()) {
            if (type.getPos().contains(pos)) {
                return type;
            }
        }
        throw new NoSuchElementException("No such pos man");
    }

    public List<Integer> getPos() {
        return pos;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public Color getColor() {
        return color;
    }
}
