package com.example.blades.service.blade.check;

public class SpecialArea {

    private int fromIndex;
    private int toIndex;
    private boolean used;

    public SpecialArea(int fromIndex, int toIndex) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    public boolean isInArea(int index) {
        return fromIndex <= index && index <= toIndex;
    }

    public boolean setUsed() {
        if (this.used) {
            return false;
        } else {
            this.used = true;
            return true;
        }
    }
}
