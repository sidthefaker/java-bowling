package bowling.model;

public enum ShotResult {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    static final ShotResult MAX = TEN;

    private final int numOfPinDown;

    ShotResult(int numOfPinDown) {
        this.numOfPinDown = numOfPinDown;
    }

    public static ShotResult from(int numOfPinDown) {
        for (ShotResult shotResult : ShotResult.values()) {
            if (shotResult.numOfPinDown == numOfPinDown) {
                return shotResult;
            }
        }

        throw new IllegalArgumentException("올바르지 않은 넘어트린 핀 갯수!");
    }

    public int getNumOfPinDown() {
        return numOfPinDown;
    }
}