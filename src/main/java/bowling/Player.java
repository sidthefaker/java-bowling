package bowling;

import java.util.List;
import java.util.Objects;

public class Player {
    public static final int NAME_LENGTH = 3;
    public static final String ONLY_ENG_ALPHABETS = "^[a-zA-Z]*$";

    private final String name;
    private final ScoreFrames scoreFrames = new ScoreFrames();

    public Player(String name) {
        validate(name);

        this.name = name;
    }

    public List<String> getScoreStrings() {
        return scoreFrames.getScoreStrings();
    }

    public List<String> getCalculatedScoreStrings() {
        return scoreFrames.getCalculatedScores();
    }

    public boolean isContinued() {
        return scoreFrames.isContinued();
    }

    public boolean playBowl(int score) {
        return scoreFrames.bowl(score);
    }

    public String getNameString() {
        return name;
    }

    private void validate(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Player name null exception.");
        }

        if (!isLengthThree(name)) {
            throw new IllegalArgumentException("플레이어의 이름은 3자리이여야 합니다.");
        }

        if (!isOnlyAlphabetics(name)) {
            throw new IllegalArgumentException("플레이어의 이름은 오직 영문자 조합이여야 합니다.");
        }
    }

    private boolean isOnlyAlphabetics(String nonNullName) {
        return nonNullName.matches(ONLY_ENG_ALPHABETS);
    }

    private boolean isLengthThree(String nonNullName) {
        return nonNullName.length() == NAME_LENGTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}