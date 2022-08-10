package com.example.blades.service.blade;

import com.example.blades.service.blade.check.CheckRule;
import com.example.blades.service.blade.check.FirstLevelCheckRule;
import com.example.blades.service.blade.check.SecondLevelCheckRule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: 11102942
 * @Date: 2022/6/27
 * @Description:
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SlotAlgorithm {

    private final CheckRule checkRule;

    public static SlotAlgorithm firstLevel() {
        CheckRule checkRule = new FirstLevelCheckRule();
        return new SlotAlgorithm(checkRule);
    }

    public static SlotAlgorithm secondLevel() {
        CheckRule checkRule = new SecondLevelCheckRule();
        return new SlotAlgorithm(checkRule);
    }

    public List<Slot> run(List<Blade> blades) {
        List<Slot> slots = initSlots(blades.size());
        boolean ok = fill(slots, blades, 0);
        if (!ok) {
            throw new IllegalStateException("找不到解");
        }
        return slots;
    }

    private List<Slot> initSlots(int size) {
        List<Slot> slots = IntStream.range(0, size)
                .boxed()
                .map(Slot::new)
                .collect(Collectors.toList());

        for (Slot slot : slots) {
            int prevIndex = (slot.getIndex() - 1 + size) % size;
            Slot slotPrev = slots.get(prevIndex);
            slot.setPrev(slotPrev);

            int nextIndex = (slot.getIndex() + 1) % size;
            Slot slotNext = slots.get(nextIndex);
            slot.setNext(slotNext);
        }

        return slots;
    }

    private boolean fill(List<Slot> slots, List<Blade> blades, int index) {
        if (index == slots.size()) {
            return checkRule.check(slots);
        }

        Slot slot = slots.get(index);
        for (Blade blade : blades) {
            if (blade.isUsed()) {
                continue;
            }

            blade.setUsed(true);
            slot.setBlade(blade);

            if (checkRule.check(slot)) {
                boolean ok = fill(slots, blades, index + 1);
                if (ok) {
                    return true;
                }
            }

            blade.setUsed(false);
            slot.setBlade(null);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] wArr = new int[]{167, 147, 168, 141, 170, 154, 169, 148, 171, 154, 168, 147, 170, 153, 167, 146, 169, 153, 174, 152, 169, 146, 168, 154};
        int[] zArr = new int[]{912, 835, 906, 819, 905, 829, 908, 820, 905, 829, 911, 826, 907, 827, 909, 822, 906, 826, 910, 827, 913, 826, 913, 834};
        List<Blade> blades = new ArrayList<>();
        for (int i = 0; i < wArr.length; i++) {
            blades.add(new Blade(wArr[i], zArr[i]));
        }

        SlotAlgorithm algorithm = SlotAlgorithm.secondLevel();
        List<Slot> slots = algorithm.run(blades);
        slots.forEach(Slot::println);
    }
}
