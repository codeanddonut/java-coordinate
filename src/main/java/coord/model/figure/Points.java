package coord.model.figure;

import java.util.*;

public final class Points {
    private final List<Point> points;

    public Points(List<Point> points) {
        this.points = Collections.unmodifiableList(new ArrayList<>(new LinkedHashSet<>(points)));
    }

    public Points(Point ... points) {
        this(Arrays.asList(points));
    }

    public Points(Points points, Comparator<Point> criteria) {
        List<Point> aligned = new ArrayList<>(new HashSet<>(points.points));
        aligned.sort(criteria);
        this.points = Collections.unmodifiableList(aligned);
    }

    public int number() {
        return this.points.size();
    }

    public Point get(int index) {
        return this.points.get(index);
    }

    public List<Point> toList() {
        return this.points;
    }

    @Override
    public String toString() {
        final String tmp = this.points.toString();
        return tmp.substring(1, tmp.length() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Points)) {
            return false;
        }
        Points rhs = (Points) o;
        return Objects.equals(new HashSet<>(this.points), new HashSet<>(rhs.points));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.points);
    }
}