package dev.himnabil.kata.countdown.expression;

import dev.himnabil.kata.countdown.expression.Expression.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Evaluator {

    public static int evaluate(Expression expression) {
        return switch (expression)  {
            case Constant(int value) -> value;
            case Addition(Expression left, Expression right ) -> evaluate(left) + evaluate(right);
            case Subtraction(Expression left, Expression right ) -> evaluate(left) - evaluate(right);
            case Multiplication(Expression left, Expression right ) -> evaluate(left) * evaluate(right);
            case Division(Expression left, Expression right ) -> evaluate(left) / evaluate(right);
        };
    }

}
