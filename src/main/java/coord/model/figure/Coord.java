package coord.model.figure;

import java.util.stream.IntStream;

public final class Coord {
    private static final int MIN = 0;
    private static final int MAX = 24;

    private static final Coord[] CACHE = IntStream.rangeClosed(MIN, MAX)
                                                    .mapToObj(Coord::new)
                                                    .toArray(Coord[]::new);

    private final int i;

    public static Coord of(int i) {
        if (i < MIN || i > MAX) {
            throw new IllegalArgumentException("좌표의 범위는 "+ MIN + " ~ " + MAX + "입니다.");
        }
        return CACHE[i];
    }

    private Coord(int i) {
        this.i = i;
    }

    public int val() {
        return i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}