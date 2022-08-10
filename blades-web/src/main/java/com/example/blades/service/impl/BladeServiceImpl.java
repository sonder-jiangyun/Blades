package com.example.blades.service.impl;

import com.example.blades.common.BladeLevelType;
import com.example.blades.help.BladeChecker;
import com.example.blades.service.BladeService;
import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;
import com.example.blades.service.blade.SlotAlgorithm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BladeServiceImpl implements BladeService {

    @Override
    public List<Slot> arrange(List<Blade> blades, BladeLevelType bladeLevelType) {
        boolean isValid = BladeChecker.isValid(blades, bladeLevelType);
        if (!isValid) {
            throw new IllegalArgumentException("弯曲频率最小值与最大值的差值未满足阈值要求!");
        }
        return BladeLevelType.FIRST_LEVEL.equals(bladeLevelType)
                ? SlotAlgorithm.firstLevel().run(blades)
                : SlotAlgorithm.secondLevel().run(blades);
    }
}
