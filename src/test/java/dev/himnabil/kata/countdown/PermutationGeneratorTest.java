package dev.himnabil.kata.countdown;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.permutations.PermutationGenerator.permutations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PermutationGeneratorTest {

    static Stream<Arguments> generatedPermutations() {
        return Stream.of(
                arguments(
                        Set.of(),
                        Set.of()
                ),
                arguments(
                        Set.of(4),
                        Set.of(
                                List.of(4)
                        )
                ),
                arguments(
                        Set.of(4, 9),
                        Set.of(
                                List.of(9, 4),
                                List.of(4, 9)
                        )
                ),
                arguments(
                        Set.of(4, 9, 11),
                        Set.of(
                                List.of(11, 9, 4),
                                List.of(9, 11, 4),
                                List.of(9, 4, 11),
    
                                List.of(11, 4, 9),
                                List.of(4, 11, 9),
                                List.of(4, 9, 11)
                        )
                ),
                arguments(
                        Set.of(4, 9, 11, 19),
                        Set.of(
                                List.of(19, 11, 9, 4), List.of(11, 19, 9, 4), List.of(11, 9, 19, 4), List.of(11, 9, 4, 19),
                                List.of(19, 9, 11, 4), List.of(9, 19, 11, 4), List.of(9, 11, 19, 4), List.of(9, 11, 4, 19),
                                List.of(19, 9, 4, 11), List.of(9, 19, 4, 11), List.of(9, 4, 19, 11), List.of(9, 4, 11, 19),
                                List.of(19, 11, 4, 9), List.of(11, 19, 4, 9), List.of(11, 4, 19, 9), List.of(11, 4, 9, 19),
                                List.of(19, 4, 11, 9), List.of(4, 19, 11, 9), List.of(4, 11, 19, 9), List.of(4, 11, 9, 19),
                                List.of(19, 4, 9, 11), List.of(4, 19, 9, 11), List.of(4, 9, 19, 11), List.of(4, 9, 11, 19)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("generatedPermutations")
    @DisplayName("should generatePermutations return valid permutations for set {0}")
    void permutationsTest(Collection<Integer> objects, Set<List<Integer>> expected) {
        var actual = permutations(objects).toList();
        assertThat(actual).containsAll(expected);
        assertThat(actual).hasSameElementsAs(expected);
    }

}