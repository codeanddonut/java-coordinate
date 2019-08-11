package coord.view;

import java.util.function.Supplier;

public class TryUntilSuccess {
    public static <T> T run(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}