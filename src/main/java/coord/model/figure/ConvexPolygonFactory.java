package coord.model.figure;

import coord.model.utils.Combinations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvexPolygonFactory {
    public static ConvexPolygon makeFrom(Points points) {
        final Points actualPoints = removeRedundantPoints(points);
        if (actualPoints.number() < 3) {
            throw new IllegalArgumentException("점의 개수가 너무 적습니다. 3점 이상이 일직선상에 존재합니다.");
        }
        return makeFromValidated(actualPoints);
    }

    private static Points removeRedundantPoints(Points points) {
        return removeRedundantPoints(new ArrayList<>(points.toList()), new ArrayList<>());
    }

    private static Points removeRedundantPoints(List<Point> points, List<Point> result) {
        points.add(points.remove(0));
        if (result.contains(points.get(0))) {
            return new Points(result);
        }
        final Combinations comb = new Combinations(points.size() - 1, 2);
        while (comb.hasNext()) {
            final List<Integer> pair = comb.next();
            if (new Line(points.get(pair.get(0) + 1), points.get(pair.get(1) + 1)).includes(points.get(0))) {
                return removeRedundantPoints(points, result);
            }
        }
        result.add(points.get(0));
        return removeRedundantPoints(points, result);
    }

    private static ConvexPolygon makeFromValidated(Points points) {
        if (points.number() == 3) {
            return new Triangle(points);
        }
        final List<Line> outlines = getOutlines(points);
        if (outlines.size() != points.number()) {
            throw new IllegalArgumentException("볼록도형이 아닙니다.");
        }
        return new ConvexPolygon(deconstructOutlines(alignOutlines(outlines)));
    }

    private static List<Line> getOutlines(Points points) {
        final List<Line> result = getEveryLines(points);
        final List<Line> exclude = getIntersectingLines(result);
        result.removeAll(exclude);
        return result;
    }

    private static List<Line> getEveryLines(Points points) {
        final List<Line> everyLines = new ArrayList<>();
        final Combinations comb = new Combinations(points.number(), 2);
        while (comb.hasNext()) {
            final List<Integer> pair = comb.next();
            everyLines.add(new Line(points.get(pair.get(0)), points.get(pair.get(1))));
        }
        return everyLines;
    }

    private static List<Line> getIntersectingLines(List<Line> lines) {
        final Set<Line> result = new HashSet<>();
        final Combinations comb = new Combinations(lines.size(), 2);
        while (comb.hasNext()) {
            final List<Integer> pair = comb.next();
            if (lines.get(pair.get(0)).intersectsWith(lines.get(pair.get(1)))) {
                result.add(lines.get(pair.get(0)));
                result.add(lines.get(pair.get(1)));
            }
        }
        return new ArrayList<>(result);
    }

    private static List<Line> alignOutlines(List<Line> outlines) {
        return alignOutlines(outlines, 0);
    }

    private static List<Line> alignOutlines(List<Line> outlines, int i) {
        if (i == outlines.size() - 1) {
            return outlines;
        }
        int target = i + 1;
        while (!outlines.get(i).isConnectedTo(outlines.get(target))) {
            target++;
        }
        outlines.add(i + 1, outlines.remove(target));
        return alignOutlines(outlines, i + 1);
    }

    private static Points deconstructOutlines(List<Line> outlines) {
        final List<Point> result = new ArrayList<>();
        final Line first = outlines.get(0);
        Line current;
        Line next = outlines.remove(0);
        while (!outlines.isEmpty()) {
            current = next;
            next = outlines.remove(0);
            result.add(current.getCommonPointWith(next));
        }
        result.add(next.getCommonPointWith(first));
        return new Points(result);
    }

    public static ConvexPolygon makeFrom(Point ... points) {
        return makeFrom(new Points(points));
    }
}
