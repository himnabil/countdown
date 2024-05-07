package dev.himnabil.kata.countdown.birarytree;

public sealed interface BinaryTreeNode {

    static BinaryTreeNode $(int left, int right){
        return new Final(left, right);
    }

    static BinaryTreeNode $(int left, BinaryTreeNode right){
        return new LeftFinal(left, right);
    }

    static BinaryTreeNode $(BinaryTreeNode left, int right){
        return new RightFinal(left, right);
    }

    static BinaryTreeNode $(BinaryTreeNode left, BinaryTreeNode right){
        return new Middle(left, right);
    }

    record Final (int left, int right) implements BinaryTreeNode {
        @Override public String toString(){ return str(this); }
    }
    record LeftFinal (int left, BinaryTreeNode right) implements BinaryTreeNode {
        @Override public String toString(){ return str(this);}
    }
    record RightFinal (BinaryTreeNode left, int right) implements BinaryTreeNode {
        @Override public String toString(){ return str(this);}
    }
    record Middle(BinaryTreeNode left, BinaryTreeNode right) implements BinaryTreeNode{
        @Override public String toString(){ return str(this);}
    }


    static String str( BinaryTreeNode node ){
        return switch (node){
            case Final(int l,int r) -> STR."\{l} @ \{r}" ;
            case LeftFinal(int l, BinaryTreeNode r) -> STR."\{l} @ (\{r})" ;
            case RightFinal(BinaryTreeNode l, int r) -> STR."(\{l}) @ \{r}";
            case Middle(BinaryTreeNode l, BinaryTreeNode r) -> STR."(\{l}) @ (\{r})";
        };
    }
}