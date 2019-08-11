package coord.view;

import coord.model.figure.Parser;
import coord.model.figure.Points;

import java.util.Scanner;

public class InputView {
    private static final Scanner input = new Scanner(System.in);

    public static Points inputCoordinates() {
        return TryUntilSuccess.run(() -> {
            System.out.println("좌표를 입력하세요.");
            return Parser.getCoordinates(input.nextLine());
        });
    }
}