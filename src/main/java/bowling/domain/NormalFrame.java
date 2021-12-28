package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import bowling.engine.FirstClassImmutableList;
import bowling.engine.Frame;
import bowling.engine.Score;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public class NormalFrame extends FirstClassImmutableList<Shot> implements Frame {
    private final Sequence sequence;

    protected NormalFrame(Sequence sequence, List<Shot> collection) {
        super(collection);
        this.sequence = sequence;
    }
    static Frame of(Sequence sequence, List<Shot> shots) {
        if (sequence == null || shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        if (sum(shots.stream()) > NUMBER_OF_PINS) {
            throw new IllegalArgumentException("sum of shot results cannot be larger than 10");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(shots);
        }

        return new NormalFrame(sequence, shots);
    }

    public static Frame ready(Sequence sequence) {
        return of(sequence, Collections.emptyList());
    }

    static int sum(Stream<Shot> shotStream) {
        return shotStream.map(Shot::toInt)
                .reduce(0, Integer::sum);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        if (isClear()) {
            throw new IllegalArgumentException("the frame is already clear");
        }

        if (size() >= NUMBER_OF_SHOT) {
            throw new IllegalStateException("the second shot is allowed after first shot.");
        }

        return NormalFrame.of(sequence, append(shot).collect());
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public boolean isClear() {
        return score().toInt() == NUMBER_OF_PINS;
    }

    @Override
    public Score score() {
        return FrameScore.of(sum(stream()));
    }

    @Override
    public boolean hasThirdChance() {
        return false;
    }

    @Override
    public boolean completed() {
        return isClear() || size() == NUMBER_OF_SHOT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sequence);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "sequence=" + sequence +
                ", shots=" + collect().toString() +
                '}';
    }
}
