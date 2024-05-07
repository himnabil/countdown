package dev.himnabil.kata.countdown;

import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.$;
import static dev.himnabil.kata.countdown.birarytree.BinaryTreeComputer.trees;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BinaryTreeComputerTest {

    static Stream<Arguments> data() {
        return Stream.of(
                arguments(
                        List.of(100, 56),
                        Set.of(
                                $(100, 56)
                        )
                ),
                arguments(
                        List.of(100, 56, 11),
                        Set.of(
                                $(100, $(56, 11)),
                                $($(100,56), 11)
                        )
                ),
                arguments(
                        List.of(100, 56, 11, 2),
                        Set.of(
                                $(100, $(56, $(11, 2))),
                                $(100, $($(56, 11), 2)),
                                $($(100,56), $(11, 2)),
                                $($(100,$(56,11)), 2),
                                $($($(100,56),11), 2)
                        )
                ),
                arguments(
                        List.of(100, 56, 11, 2, 4),
                        Set.of(
                                $(100, $(56, $(11, $(2, 4)))),
                                $(100, $(56, $($(11, 2),4 ))),
                                $(100, $($(56,11), $(2,4 ))),
                                $(100, $($(56,$(11,2)),4 )),
                                $(100, $($($(56,11),2),4 )),
                                $($(100, 56),$(11,$(2,4 ))),
                                $($(100, 56),$($(11,2),4 )),
                                $($(100,$(56,11)),$(2,4 )),
                                $($($(100,56),11),$(2,4 )),
                                $($(100,$(56,$(11,2))),4),
                                $($(100,$($(56,11),2)),4),
                                $($($(100,56),$(11,2)),4),
                                $($($(100,$(56,11)),2),4),
                                $($($($(100,56),11), 2),4)

                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void treesTest(List<Integer> input, Set<BinaryTreeNode> expected) {
        var actual = trees(input).toList();
        assertThat(actual).containsAll(expected);
        assertThat(actual).hasSameElementsAs(expected);
    }
}