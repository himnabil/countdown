package dev.himnabil.kata.countdown.permutations;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubsequencesComputer {

    public static Stream<List<Integer>> subsequences(List<Integer> list) {
        if (list.isEmpty()) {
            return Stream.of(List.of());
        }

        var lastElement = list.getLast();
        var subList = list.subList(0, list.size() -1);

        return subsequences(subList)
                .flatMap(l -> Stream.of(l, withElement(l, lastElement)))
                .distinct();
    }

    private static <T> List<T> withElement(List<T> list, T element) {
        return Stream.concat(list.stream(),Stream.of(element)).toList();
    }
}
