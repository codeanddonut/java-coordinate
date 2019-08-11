package coord.model.figure;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    private static final String validator = "\\([ ]*[0-9]+[ ]*,[ ]*[0-9]+[ ]*\\)";
    private static final String delimiter = "\\(|,|\\)";

    public static Points getCoordinates(String input) {
        return Optional.ofNullable(input).map(String::trim)
                                        .map(x -> new Points(parse(x)))
                                        .orElseThrow(() ->
                                                new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.")
                                        );
    }

    private static List<Point> parse(String input) {
        return Stream.of(input.split("-")).filter(token -> token.matches(validator))
                                            .map(Parser::parseEachToken)
                                            .map(pair -> new Point(pair.get(0), pair.get(1)))
                                            .collect(Collectors.toList());
    }

    private static List<Integer> parseEachToken(String token) {
        final String[] tokens = token.split(delimiter);
        return Arrays.asList(Integer.parseInt(tokens[1].trim()), Integer.parseInt(tokens[2].trim()));
    }
}