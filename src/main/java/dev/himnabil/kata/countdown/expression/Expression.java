package dev.himnabil.kata.countdown.expression;

public sealed interface Expression {

    static Expression val(int value) {
        return new Constant(value);
    }

    static Expression add(Expression left, Expression right) {
        return new Addition(left, right);
    }

    static Expression add(int left, Expression right) {
        return new Addition(val(left), right);
    }

    static Expression add(Expression left, int right) {
        return new Addition(left, val(right));
    }

    static Expression add(int left, int right) {
        return new Addition(val(left), val(right));
    }

    static Expression subtract(Expression left, Expression right) {
        return new Subtraction(left, right);
    }

    static Expression subtract(int left, Expression right) {
        return new Subtraction(val(left), right);
    }

    static Expression subtract(Expression left, int right) {
        return new Subtraction(left, val(right));
    }

    static Expression subtract(int left, int right) {
        return new Subtraction(val(left), val(right));
    }

    static Expression multiply(Expression left, Expression right) {
        return new Multiplication(left, right);
    }

    static Expression multiply(int left, Expression right) {
        return new Multiplication(val(left), right);
    }

    static Expression multiply(Expression left, int right) {
        return new Multiplication(left, val(right));
    }

    static Expression multiply(int left, int right) {
        return new Multiplication(val(left), val(right));
    }

    static Expression divide(Expression left, Expression right) {
        return new Division(left, right);
    }

    static Expression divide(int left, Expression right) {
        return new Division(val(left), right);
    }

    static Expression divide(Expression left, int right) {
        return new Division(left, val(right));
    }

    static Expression divide(int left, int right) {
        return new Division(val(left), val(right));
    }

    record Constant(int value) implements Expression {
        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
    record Addition(Expression left, Expression right) implements Expression {
        @Override
        public String toString() {
            return switch (left){
                case Constant(int l) -> switch (right){
                    case Constant(int r) -> STR."\{l} + \{r}";
                    case Addition(var rl, var rr) -> STR."\{l} + \{rl} + \{rr}";
                    case Subtraction(var rl, var rr) -> STR."\{l} + \{rl} - \{rr}";
                    case Multiplication _, Division _ -> STR."\{l} + (\{right})";
                };
                case Addition(var ll , var lr ) -> switch (right){
                    case Constant(int r) -> STR."\{ll} + \{lr} + \{r}";
                    case Addition(var rl, var rr) -> STR."\{ll} + \{lr} + \{rl} + \{rr}";
                    case Subtraction _, Multiplication _, Division _ -> STR."\{ll} + \{lr} + (\{right})";
                };
                case Subtraction _, Multiplication _, Division _ -> switch (right){
                    case Constant(int r) -> STR."(\{left}) + \{r}";
                    case Addition(var rl, var rr) -> STR."(\{left}) + \{rl} + \{rr}";
                    case Subtraction _, Multiplication _, Division _ -> STR."(\{left}) + (\{right})";
                };
            };
        }
    }
    record Subtraction(Expression left, Expression right) implements Expression {
        @Override
        public String toString() {
            return switch (left){
                case Constant(int l) -> switch (right){
                    case Constant(int r) -> STR."\{l} - \{r}";
                    case Subtraction(var rl, var rr) -> STR."\{l} - \{rl} - \{rr}";
                    
                };
            };
        }
    }
    record Multiplication(Expression left, Expression right) implements Expression {}
    record Division(Expression left, Expression right) implements Expression {}

}
