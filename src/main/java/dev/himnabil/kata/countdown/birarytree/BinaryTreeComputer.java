package dev.himnabil.kata.countdown.birarytree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

import static dev.himnabil.kata.countdown.birarytree.BinaryTreeNode.$;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryTreeComputer {

    public static Stream<BinaryTreeNode> trees(List<Integer> elements) {
        return computeTrees(elements, 0, elements.size() - 1);
    }

    private static Stream<BinaryTreeNode> computeTrees(List<Integer> elements, int start, int end) {
        return Stream.iterate(start, i -> i < end, i -> i + 1)
                .flatMap(pivot -> recursiveTrees(elements, start, pivot, end));
    }

    public static Stream<BinaryTreeNode> recursiveTrees(List<Integer> elements, int start, int pivot, int end) {
        if ( end - start == 1 ){
            return Stream.of($(elements.get(start), elements.get(end)));
        }
        if(pivot == start ){
            return computeTrees(elements, start + 1, end)
                    .map(tree -> $(elements.get(start), tree ));
        }
        if(pivot == end - 1){
            return computeTrees(elements, start, end - 1)
                    .map(tree -> $(tree, elements.get(end) ));
        }
        if (pivot > start && pivot < end) {
            var left = computeTrees(elements, start, pivot);
            var right = computeTrees(elements, pivot + 1, end);
            return cartesianProduct(left, right);
        }
        return Stream.empty();
    }

    private static Stream<BinaryTreeNode> cartesianProduct(Stream<BinaryTreeNode> left, Stream<BinaryTreeNode> right) {
        var rightList = right.toList();
        return left.flatMap( l -> rightList.stream().map( r -> $(l, r) ));
    }

}