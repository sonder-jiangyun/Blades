package com.example.blades.service.blade.check;

import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FirstLevelCheckRule implements CheckRule {

    private final List<CheckRule> subRules = Arrays.asList(new SubRuleForValueW(), new SubRuleForValueZ(), new SubRuleForWight());

    @Override
    public boolean check(Slot slot) {
        return subRules.stream().allMatch(checkRule -> checkRule.check(slot));
    }

    @Override
    public boolean check(List<Slot> slots) {
        Slot first = slots.get(0);
        Slot second = slots.get(1);
        return this.check(first) && this.check(second) && subRules.get(2).check(slots);
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

    private static class SubRuleForWight implements CheckRule {

        private static final List<Integer> SPECIAL_LOCATION = Arrays.asList(5, 11, 17, 23);
        private static final Integer EXPECTED_WIGHT_VALUE = 200;
        private static final int AREA_CURSOR = 6;
        private static final int FIRST_AREA_INDEX = 5;


        @Override
        public boolean check(List<Slot> slots) {
            Slot slotArea1 = slots.get(5);
            Slot slotArea4 = slots.get(23);
            int wightArea1 = this.getSum(slotArea1);
            int wightArea4 = this.getSum(slotArea4);
            return Math.abs(wightArea1 - wightArea4) <= EXPECTED_WIGHT_VALUE;
        }

        @Override
        public boolean check(Slot slot) {
            Integer index = slot.getIndex();
            boolean needComputeWeight = SPECIAL_LOCATION.stream().noneMatch(o -> o.equals(index));
            if (needComputeWeight) {
                return true;
            }

            return isValidWightDistance(slot);
        }


        private boolean isValidWightDistance(Slot slot) {
            int index = slot.getIndex();
            if (index == FIRST_AREA_INDEX) {
                return true;
            }
            Slot preAreaSlot = slot;
            int cursor = AREA_CURSOR;
            while (cursor-- > 0) {
                preAreaSlot = preAreaSlot.getPrev();
            }

            int curAreaSum = this.getSum(slot);
            int preAreaSum = this.getSum(preAreaSlot);
            return Math.abs(curAreaSum - preAreaSum) <= EXPECTED_WIGHT_VALUE;
        }

        private Integer getSum(Slot slot) {
            int cursor = AREA_CURSOR;
            int sum = 0;
            while (cursor-- > 0 && null != slot && null != slot.getBlade()) {
                sum += slot.getBlade().getWight();
                slot = slot.getPrev();
            }
            return sum;
        }
    }
}
