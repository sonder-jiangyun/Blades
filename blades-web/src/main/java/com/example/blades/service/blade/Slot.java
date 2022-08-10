package com.example.blades.service.blade;

import lombok.Data;

@Data
public class Slot {

    private int index;
    private Slot prev;
    private Slot next;
    private Blade blade;

    public Slot(int index) {
        this.index = index;
    }

    public void println() {
        int wDistance = Math.abs(blade.getWValue() - next.blade.getWValue());
        int zDistance = Math.abs(blade.getZValue() - next.blade.getZValue());
        String p = String.format("%d %d (%d)--%d (%d) ", index + 1, blade.getWValue(), wDistance, blade.getZValue(), zDistance);
        System.out.println(p);
    }
}
