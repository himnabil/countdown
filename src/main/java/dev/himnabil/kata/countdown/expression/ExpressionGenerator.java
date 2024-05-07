package dev.himnabil.kata.countdown.expression;

import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode;
import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.Final;
import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.LeftFinal;
import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.Middle;
import dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.RightFinal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.expression.Expression.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpressionGenerator {

    public static Stream<Expression> expressions(BinaryTreeNode tree) {
        return switch (tree){
            case Final(int l, int r) -> allExpressions(val(l), val(r));
            case LeftFinal(int l, BinaryTreeNode right) -> expressions(right)
                    .flatMap(r -> allExpressions(val(l),r));
            case RightFinal(BinaryTreeNode left, int r) -> expressions(left)
                    .flatMap(l -> allExpressions(l,val(r)));
            case Middle(BinaryTreeNode left, BinaryTreeNode right) ->
                    cartesianProduct(expressions(left), expressions(right), ExpressionGenerator::allExpressions);
        };
    }

    private static Stream<Expression> allExpressions(Expression l, Expression r) {
        return Stream.of(add(l,r), subtract(l,r), multiply(l,r), divide(l,r));
    }


    private static <T> Stream<T> cartesianProduct(Stream<T> left, Stream<T> right, BiFunction<T,T,Stream<T>> merge) {
        var rightList = right.toList();
        return left.flatMap(l -> rightList.stream().flatMap(r -> merge.apply(l,r) ));
    }

}
