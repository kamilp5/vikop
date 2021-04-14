package io.spring.vikop.vote;

public enum VoteType {
    VOTE_UP(1), VOTE_DOWN(-1);

    private int value;

    VoteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
