package dev.himnabil.kata.countdown.permutations;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.permutations.SubsequencesComputer.subsequences;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SubsequencesComputerTest {

    static Stream<Arguments> data() {
        return Stream.of(
                arguments(
                        List.of(),
                        Set.of(
                                List.of()
                        )
                ),
                arguments(
                        List.of(13),
                        Set.of(
                                List.of(),
                                List.of(13)
                        )
                ),
                arguments(
                        List.of(13, 19),
                        Set.of(
                                List.of(),
                                List.of(13), List.of(19),
                                List.of(13, 19)
                        )
                ),
                arguments(
                        List.of(13, 19, 21),
                        Set.of(
                                List.of(),
                                List.of(13), List.of(19), List.of(21),
                                List.of(13, 19), List.of(13, 21), List.of(19, 21),
                                List.of(13, 19, 21)
                        )
                ),
                arguments(
                        List.of(13, 13),
                        Set.of(
                                List.of(),
                                List.of(13),
                                List.of(13, 13)
                        )
                ),
                arguments(
                        List.of(13, 13, 21),
                        Set.of(
                                List.of(),
                                List.of(13), List.of(21),
                                List.of(13, 13), List.of(13, 21),
                                List.of(13, 13, 21)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void testSubLists(List<Integer> list, Set<List<Integer>> expected) {
        var actual = subsequences(list).toList();
        assertThat(actual).hasSameElementsAs(expected);
        assertThat(actual).hasSameSizeAs(expected);
    }
}