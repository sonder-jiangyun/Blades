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
        int wightSum = 0;
        if ((this.index + 1) % 6 == 0) {
            int count = 6;
            Slot temp = this;
            while (count > 0) {
                wightSum += temp.blade.getWight();
                temp = temp.prev;
                count--;
            }
        }
        String p = String.format("%d %d (%d)--%d (%d) --%d (%d) ", index + 1, blade.getWValue(), wDistance, blade.getZValue(), zDistance, blade.getWight(), wightSum);
        System.out.println(p);
    }
}
