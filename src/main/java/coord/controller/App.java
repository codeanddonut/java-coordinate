package coord.controller;

import coord.model.figure.ConvexPolygonFactory;
import coord.model.figure.Line;
import coord.model.figure.Points;
import coord.view.InputView;
import coord.view.OutputView;
import coord.view.TryUntilSuccess;

public class App {
    public static void main(String[] argc) {
        TryUntilSuccess.run(App::run);
    }

    private static boolean run() {
        final Points points = InputView.inputCoordinates();
        OutputView.printScreen(points);
        System.out.println();
        if (points.number() == 2) {
            OutputView.printLengthOfLine(new Line(points));
            return true;
        }
        if (points.number() > 2) {
            OutputView.printAreaOfFigure(ConvexPolygonFactory.makeFrom(points));
            return true;
        }
        return false;
    }
}