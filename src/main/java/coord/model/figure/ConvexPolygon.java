package coord.model.figure;

public class ConvexPolygon extends Figure {
    protected ConvexPolygon(Points points) {
        super(points);
    }

    @Override
    public double area() {
        if (Double.compare(this.areaCache, .0) == 0) {
            this.areaCache = area(.0, 0);
        }
        return this.areaCache;
    }

    private double area(double result, int i) {
        if (i + 2 == this.points.number()) {
            return result;
        }
        return area(
                result + new Triangle(
                        this.points.get(0),
                        this.points.get(i + 1),
                        this.points.get(i + 2)
                ).area(),
                i + 1
        );
    }
}