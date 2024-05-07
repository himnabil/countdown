package dev.himnabil.kata.countdown.permutations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermutationGenerator {

    public static <T> Stream<List<T>> permutations(@NotNull Collection<T> objects) {
        return generateOrders(objects.size())
                .map(permute(objects));
    }

    @Contract(pure = true)
    @SuppressWarnings("unchecked")
    private static <T> @NotNull Function<List<Integer>,List<T>> permute(@NotNull Collection<T> objects){
        T[] array = (T[]) objects.toArray();
        return order -> order.stream()
                .map( index -> array[index] )
                .toList();
    }

    private static Stream<List<Integer>> generateOrders(int size){
        if (size == 0) {
            return Stream.empty();
        }
        if (size == 1) {
            return Stream.of(List.of(0));
        }
        return generateOrders(size - 1).flatMap(PermutationGenerator::upperOrder);
    }

    private static Stream<List<Integer>> upperOrder(@NotNull List<Integer> lowerOrder) {
        var builder = Stream.<List<Integer>>builder();
        for (int i = lowerOrder.size() ; i >=0 ; i--) {
            LinkedList<Integer> order = new LinkedList<>(lowerOrder);
            order.add(i, lowerOrder.size());
            builder.add(List.copyOf(order));
        }
        return builder.build();
    }

}
