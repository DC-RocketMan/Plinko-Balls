package PlinkoBalls;

public enum GameDimensions {
    FULL_HD(1920, 1080, 1, (1920)/2 - 13, 100, 50, 25, 5.25, 10),
    HD(1280, 720, (double) 2 /3, 1280/2 - 30, 66, 32, 16, 3.5, (double) 20 /3);

    private final int gameWidth;
    private final int gameHeight;
    private final double multiplier;
    private final int ballXVar;
    private final int ballHeight;
    private final int pegDistance;
    private final int pegDiagonalDistance;
    private final double pegRadius;
    private final double ballRadius;

    GameDimensions(int gameWidth, int gameHeight, double multiplier, int ballXVar, int ballHeight, int pegDistance, int pegDiagonalDistance, double pegRadius, double ballRadius) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.multiplier = multiplier;
        this.ballXVar = ballXVar;
        this.ballHeight = ballHeight;
        this.pegDistance = pegDistance;
        this.pegDiagonalDistance = pegDiagonalDistance;
        this.pegRadius = pegRadius;
        this.ballRadius = ballRadius;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int getBallXVar() {
        return ballXVar;
    }

    public int getBallHeight() {
        return ballHeight;
    }

    public int getPegDistance() {
        return pegDistance;
    }

    public int getPegDiagonalDistance() {
        return pegDiagonalDistance;
    }

    public double getPegRadius() {
        return pegRadius;
    }

    public double getBallRadius() {
        return ballRadius;
    }
}
