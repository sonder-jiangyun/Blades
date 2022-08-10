package com.example.blades.service.blade.check;

import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FirstLevelCheckRule implements CheckRule {

    private final List<CheckRule> subRules = Arrays.asList(new SubRuleForValueW(), new SubRuleForValueZ());

    @Override
    public boolean check(Slot slot) {
        return subRules.stream().allMatch(checkRule -> checkRule.check(slot));
    }

    @Override
    public boolean check(List<Slot> slots) {
        Slot first = slots.get(0);
        Slot second = slots.get(1);
        return this.check(first) && this.check(second);
    }

    private static class SubRuleForValueZ extends AbstractCheckRule {

        @Override
        protected int getMinDistance() {
            return 40;
        }

        @Override
        protected Function<Blade, Integer> getValueExtractor() {
            return Blade::getZValue;
        }

        @Override
        protected int getExpectedDistance(Slot slot) {
            return Arrays.asList(1, 8, 16).contains(slot.getIndex()) ? 80 : 50;
        }

        @Override
        protected List<SpecialArea> defineSpecialAreas() {
            return Arrays.asList(
                    new SpecialArea(3, 6),
                    new SpecialArea(10, 14),
                    new SpecialArea(19, 23)
            );
        }
    }

    private static class SubRuleForValueW extends AbstractCheckRule {

        @Override
        protected int getMinDistance() {
            return 11;
        }

        @Override
        protected Function<Blade, Integer> getValueExtractor() {
            return Blade::getWValue;
        }

        @Override
        protected int getExpectedDistance(Slot slot) {
            return Arrays.asList(1, 5, 6, 8, 16).contains(slot.getIndex()) ? 20 : 13;
        }

        @Override
        protected List<SpecialArea> defineSpecialAreas() {
            return Arrays.asList(
                    new SpecialArea(3, 6),
                    new SpecialArea(10, 14),
                    new SpecialArea(19, 23)
            );
        }
    }
}
