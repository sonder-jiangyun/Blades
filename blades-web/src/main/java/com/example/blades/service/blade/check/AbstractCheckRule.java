package com.example.blades.service.blade.check;

import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;

import java.util.List;
import java.util.function.Function;

abstract class AbstractCheckRule implements CheckRule {

    private final List<SpecialArea> specialAreas = this.defineSpecialAreas();

    @Override
    public boolean check(List<Slot> slots) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean check(Slot slot) {
        return this.checkPrev(slot) && this.checkPrevOfPrev(slot);
    }

    private boolean checkPrev(Slot slot) {
        Slot slotPrev = slot.getPrev();
        Blade bladePrev = slotPrev.getBlade();
        if (bladePrev != null) {
            Function<Blade, Integer> valueExtractor = this.getValueExtractor();
            Blade blade = slot.getBlade();
            int value = valueExtractor.apply(blade);

            int valuePrev = valueExtractor.apply(bladePrev);
            int distance = Math.abs(valuePrev - value);
            if (distance < this.getMinDistance()) {
                return false;
            }

            int expectedDistance = getExpectedDistance(slot);
            if (distance < expectedDistance) {
                return specialAreas.stream()
                        .filter(area -> area.isInArea(slot.getIndex()))
                        .map(SpecialArea::setUsed)
                        .findFirst()
                        .orElse(false);
            }
        }

        return true;
    }

    private boolean checkPrevOfPrev(Slot slot) {
        Slot slotPrevOfPrev = slot.getPrev().getPrev();
        Blade bladePrevOfPrev = slotPrevOfPrev.getBlade();
        if (bladePrevOfPrev != null) {
            Function<Blade, Integer> valueExtractor = this.getValueExtractor();
            Blade blade = slot.getBlade();
            int value = valueExtractor.apply(blade);

            int valuePrevOfPrev = valueExtractor.apply(bladePrevOfPrev);
            if (valuePrevOfPrev == value) {
                return false;
            }
        }

        return true;
    }

    protected abstract List<SpecialArea> defineSpecialAreas();

    protected abstract Function<Blade, Integer> getValueExtractor();

    protected abstract int getMinDistance();

    protected abstract int getExpectedDistance(Slot slot);
}
