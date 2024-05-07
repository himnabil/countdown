package dev.himnabil.kata.countdown.expression;

import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.$;
import static dev.himnabil.kata.countdown.expression.Expression.*;
import static dev.himnabil.kata.countdown.expression.ExpressionGenerator.expressions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExpressionGeneratorTest {

    static Stream<Arguments> data() {
        return Stream.of(
                arguments(
                        $(25,6),
                        Set.of(
                                add(25,6),
                                subtract(25,6),
                                multiply(25,6),
                                divide(25,6)
                        )
                ),
                arguments(
                        $(10,$(25,6)),
                        Set.of(
                                add(10,add(25,6)),
                                add(10,subtract(25,6)), 
                                add(10,multiply(25,6)),
                                add(10,divide(25,6)),

                                subtract(10,add(25,6)),
                                subtract(10,subtract(25,6)),
                                subtract(10,multiply(25,6)),
                                subtract(10,divide(25,6)),

                                multiply(10,add(25,6)),
                                multiply(10,subtract(25,6)),
                                multiply(10,multiply(25,6)),
                                multiply(10,divide(25,6)),

                                divide(10,add(25,6)),
                                divide(10,subtract(25,6)),
                                divide(10,multiply(25,6)),
                                divide(10,divide(25,6))
                        )
                ),
                arguments(
                        $($(25,6), 10),
                        Set.of(
                                add(add(25,6), 10),
                                add(subtract(25,6), 10),
                                add(multiply(25,6), 10),
                                add(divide(25,6), 10),

                                subtract(add(25,6), 10),
                                subtract(subtract(25,6), 10),
                                subtract(multiply(25,6), 10),
                                subtract(divide(25,6), 10),

                                multiply(add(25,6), 10),
                                multiply(subtract(25,6), 10),
                                multiply(multiply(25,6), 10),
                                multiply(divide(25,6), 10),

                                divide(add(25,6), 10),
                                divide(subtract(25,6), 10),
                                divide(multiply(25,6), 10),
                                divide(divide(25,6), 10)
                        )
                ),
                arguments(
                        $($(25,6), $(10, 12)),
                        Set.of(
                                add(add(25,6), add(10, 12)),
                                add(subtract(25,6), add(10, 12)),
                                add(multiply(25,6), add(10, 12)),
                                add(divide(25,6), add(10, 12)),

                                add(add(25,6), subtract(10, 12)),
                                add(subtract(25,6), subtract(10, 12)),
                                add(multiply(25,6), subtract(10, 12)),
                                add(divide(25,6), subtract(10, 12)),

                                add(add(25,6), multiply(10, 12)),
                                add(subtract(25,6), multiply(10, 12)),
                                add(multiply(25,6), multiply(10, 12)),
                                add(divide(25,6), multiply(10, 12)),

                                add(add(25,6), divide(10, 12)),
                                add(subtract(25,6), divide(10, 12)),
                                add(multiply(25,6), divide(10, 12)),
                                add(divide(25,6), divide(10, 12)),

                                subtract(add(25, 6), add(10, 12)),
                                subtract(subtract(25, 6), add(10, 12)),
                                subtract(multiply(25, 6), add(10, 12)),
                                subtract(divide(25, 6), add(10, 12)),

                                subtract(add(25, 6), subtract(10, 12)),
                                subtract(subtract(25, 6), subtract(10, 12)),
                                subtract(multiply(25, 6), subtract(10, 12)),
                                subtract(divide(25, 6), subtract(10, 12)),

                                subtract(add(25, 6), multiply(10, 12)),
                                subtract(subtract(25, 6), multiply(10, 12)),
                                subtract(multiply(25, 6), multiply(10, 12)),
                                subtract(divide(25, 6), multiply(10, 12)),

                                subtract(add(25, 6), divide(10, 12)),
                                subtract(subtract(25, 6), divide(10, 12)),
                                subtract(multiply(25, 6), divide(10, 12)),
                                subtract(divide(25, 6), divide(10, 12)),


                                multiply(add(25, 6), add(10, 12)),
                                multiply(subtract(25, 6), add(10, 12)),
                                multiply(multiply(25, 6), add(10, 12)),
                                multiply(divide(25, 6), add(10, 12)),

                                multiply(add(25, 6), subtract(10, 12)),
                                multiply(subtract(25, 6), subtract(10, 12)),
                                multiply(multiply(25, 6), subtract(10, 12)),
                                multiply(divide(25, 6), subtract(10, 12)),

                                multiply(add(25, 6), multiply(10, 12)),
                                multiply(subtract(25, 6), multiply(10, 12)),
                                multiply(multiply(25, 6), multiply(10, 12)),
                                multiply(divide(25, 6), multiply(10, 12)),

                                multiply(add(25, 6), divide(10, 12)),
                                multiply(subtract(25, 6), divide(10, 12)),
                                multiply(multiply(25, 6), divide(10, 12)),
                                multiply(divide(25, 6), divide(10, 12)),
                                
                                
                                divide(add(25,6), add(10, 12)),
                                divide(subtract(25,6), add(10, 12)),
                                divide(multiply(25,6), add(10, 12)),
                                divide(divide(25,6), add(10, 12)),

                                divide(add(25,6), subtract(10, 12)),
                                divide(subtract(25,6), subtract(10, 12)),
                                divide(multiply(25,6), subtract(10, 12)),
                                divide(divide(25,6), subtract(10, 12)),

                                divide(add(25,6), multiply(10, 12)),
                                divide(subtract(25,6), multiply(10, 12)),
                                divide(multiply(25,6), multiply(10, 12)),
                                divide(divide(25,6), multiply(10, 12)),

                                divide(add(25,6), divide(10, 12)),
                                divide(subtract(25,6), divide(10, 12)),
                                divide(multiply(25,6), divide(10, 12)),
                                divide(divide(25,6), divide(10, 12))
                                )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(BinaryTreeNode tree, Set<Expression> expected) {
        var actual = expressions(tree).toList();
        assertThat(actual).containsAll(expected);
        assertThat(actual).hasSameElementsAs(expected);

    }







}