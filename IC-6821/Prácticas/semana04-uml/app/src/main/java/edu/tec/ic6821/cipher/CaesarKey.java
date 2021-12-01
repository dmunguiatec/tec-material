package edu.tec.ic6821.cipher;

public class CaesarKey implements Key<Integer> {

    private Integer key;

    public CaesarKey(Integer key) {
        this.key = key;
    }

    @Override
    public Integer get() {
        return this.key;
    }
}
