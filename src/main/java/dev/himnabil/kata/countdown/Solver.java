package dev.himnabil.kata.countdown;

import dev.himnabil.kata.countdown.birarytree.BinaryTreeComputer;
import dev.himnabil.kata.countdown.expression.Expression;
import dev.himnabil.kata.countdown.expression.ExpressionGenerator;
import dev.himnabil.kata.countdown.input.Input;
import dev.himnabil.kata.countdown.permutations.PermutationGenerator;

import java.util.Comparator;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.expression.Evaluator.evaluate;
import static dev.himnabil.kata.countdown.permutations.SubsequencesComputer.subsequences;
import static java.lang.Math.abs;

public class Solver {

    public record Result(Expression expression, int value) {}

    public static Stream<Result> solve(Input input){
       return subsequences(input.elements())
               .filter(seq -> seq.size() >= 2)
               .flatMap(PermutationGenerator::permutations)
               .flatMap(BinaryTreeComputer::trees)
               .flatMap(ExpressionGenerator::expressions)
               .map(expr -> new Result(expr, evaluate(expr)))
               .sorted(Comparator.comparing(r -> abs(input.target() - r.value)));
    }
}
