package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalScoreFrameTest {
    @Test
    @DisplayName("다음 NormalScoreFrame으로 진행하는 케이스 테스트")
    void bowlTest1() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextScoreFrame = normalScoreFrame.addScore(10);
        ScoreFrame nextNextScoreFrame = nextScoreFrame.addScore(10);

        assertThat(nextScoreFrame).isInstanceOf(NormalScoreFrame.class);

        assertThat(nextNextScoreFrame).isInstanceOf(NormalScoreFrame.class);
    }

    @Test
    @DisplayName("다음 FinalScoreFrame으로 진행하는 케이스 테스트")
    void bowlTest2() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(9));
        ScoreFrame nextScoreFrame = normalScoreFrame.addScore(10);

        NormalScoreFrame normalScoreFrame2 = new NormalScoreFrame(new Turn(9));
        ScoreFrame nextScoreFrame2 = normalScoreFrame2.addScore(9).addScore(1);

        NormalScoreFrame normalScoreFrame3 = new NormalScoreFrame(new Turn(9));
        ScoreFrame nextScoreFrame3 = normalScoreFrame3.addScore(9).addScore(0);

        assertThat(nextScoreFrame).isInstanceOf(FinalScoreFrame.class);

        assertThat(nextScoreFrame2).isInstanceOf(FinalScoreFrame.class);

        assertThat(nextScoreFrame3).isInstanceOf(FinalScoreFrame.class);
    }

    @Test
    @DisplayName("다음 ScoreFrame으로 진행하지 않는 케이스 테스트")
    void bowlTest3() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));
        ScoreFrame nextScoreFrame = normalScoreFrame.addScore(9);

        assertThat(nextScoreFrame).isSameAs(normalScoreFrame);
    }

    @Test
    @DisplayName("최대 점수 초과 시 예외 발생 케이스 테스트")
    void bowlTest4() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        assertThatThrownBy(() -> normalScoreFrame.addScore(9).addScore(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("점수 계산 가능한지 확인 - 3 스트라이크")
    void isCalculable() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextNormalScoreFrame = normalScoreFrame.addScore(10);
        boolean firstCalculable = normalScoreFrame.isCalculable();

        ScoreFrame nextNextNormalScoreFrame = nextNormalScoreFrame.addScore(10);
        boolean secondCalculable = normalScoreFrame.isCalculable();

        nextNextNormalScoreFrame.addScore(10);
        boolean thirdCalculable = normalScoreFrame.isCalculable();

        assertThat(firstCalculable).isFalse();

        assertThat(secondCalculable).isFalse();

        assertThat(thirdCalculable).isTrue();
    }

    @Test
    @DisplayName("점수 계산 가능한지 확인 - 1 스패어 1 투구")
    void isCalculable2() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextNormalScoreFrame = normalScoreFrame.addScore(2);
        boolean firstCalculable = normalScoreFrame.isCalculable();

        ScoreFrame nextNextNormalScoreFrame = nextNormalScoreFrame.addScore(8);
        boolean secondCalculable = normalScoreFrame.isCalculable();

        nextNextNormalScoreFrame.addScore(5);
        boolean thirdCalculable = normalScoreFrame.isCalculable();

        assertThat(firstCalculable).isFalse();

        assertThat(secondCalculable).isFalse();

        assertThat(thirdCalculable).isTrue();
    }

    @Test
    @DisplayName("점수 계산 가능한지 확인 - 미스")
    void isCalculable3() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextNormalScoreFrame = normalScoreFrame.addScore(2);
        boolean firstCalculable = normalScoreFrame.isCalculable();

        nextNormalScoreFrame.addScore(6);
        boolean secondCalculable = normalScoreFrame.isCalculable();

        assertThat(firstCalculable).isFalse();

        assertThat(secondCalculable).isTrue();
    }

    @Test
    @DisplayName("점수 계산 테스트 - 3 스트라이크")
    void getScoreTest() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextNormalScoreFrame = normalScoreFrame.addScore(10);
        ScoreFrame nextNextNormalScoreFrame = nextNormalScoreFrame.addScore(10);
        nextNextNormalScoreFrame.addScore(10);

        assertThat(normalScoreFrame.getScore(Score.ofZero())).isEqualTo(new Score(30));
    }

    @Test
    @DisplayName("점수 계산 테스트 - 1 스페어 1 투구")
    void getScoreTest2() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextNormalScoreFrame = normalScoreFrame.addScore(8);
        ScoreFrame nextNextNormalScoreFrame = nextNormalScoreFrame.addScore(2);
        nextNextNormalScoreFrame.addScore(1);

        assertThat(normalScoreFrame.getScore(Score.ofZero())).isEqualTo(new Score(11));
    }

    @Test
    @DisplayName("점수 계산 테스트 - 미스")
    void getScoreTest3() {
        NormalScoreFrame normalScoreFrame = new NormalScoreFrame(new Turn(1));

        ScoreFrame nextNormalScoreFrame = normalScoreFrame.addScore(8);
        nextNormalScoreFrame.addScore(1);

        assertThat(normalScoreFrame.getScore(Score.ofZero())).isEqualTo(new Score(9));
    }
}