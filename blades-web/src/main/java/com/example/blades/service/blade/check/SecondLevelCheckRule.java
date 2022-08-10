package com.example.blades.service.blade.check;

import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SecondLevelCheckRule extends AbstractCheckRule {

    @Override
    protected Function<Blade, Integer> getValueExtractor() {
        return Blade::getWValue;
    }

    @Override
    protected int getMinDistance() {
        return 7;
    }

    @Override
    protected int getExpectedDistance(Slot slot) {
        return Arrays.asList(1, 5, 6, 7, 9, 19, 26).contains(slot.getIndex()) ? 15 : 9;
    }

    @Override
    protected List<SpecialArea> defineSpecialAreas() {
        return Arrays.asList(
                new SpecialArea(3, 7),
                new SpecialArea(11, 17),
                new SpecialArea(21, 24),
                new SpecialArea(28, 33)
        );
    }

    @Override
    public boolean check(List<Slot> slots) {
        Slot first = slots.get(0);
        Slot second = slots.get(1);
        return this.check(first) && this.check(second);
    }
}
