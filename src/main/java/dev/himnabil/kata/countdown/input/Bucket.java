package dev.himnabil.kata.countdown.input;

public class Bucket {

    static class BucketElement {
        final int limit;
        int count;

        BucketElement(int limit) {
            this.limit = limit;
            this.count = 0;
        }

        void draw() {
            if (count >= limit) {
                throw new BucketOverflowException(limit);
            }
            count++;
        }
    }

    static class BucketOverflowException extends RuntimeException {
        final int limit;

        BucketOverflowException(int limit) {
            this.limit = limit;
          }
    }
}
