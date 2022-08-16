package com.example.blades.help;

import cn.hutool.core.bean.BeanUtil;
import com.example.blades.export.BladeExcelBuilder;
import com.example.blades.export.FirstLevleBladeExcelModel;
import com.example.blades.export.SecondLevelBladeExcelModel;
import com.example.blades.vo.BladeSlotData;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class BladeExcelExportHelper {

    private static final int FIRST_LEVEL_BLADE_NUMBER = 24;

    public void doExport(List<BladeSlotData> bladeSlots, OutputStream outputStream) {
        if (this.isFirstLevelBlade(bladeSlots)) {
            List<FirstLevleBladeExcelModel> firstLevleBladeExcelModels = bladeSlots.stream().map(this::format).collect(Collectors.toList());
            BladeExcelBuilder bladeExcelBuilder = new BladeExcelBuilder();
            bladeExcelBuilder.sheet("一级扇叶排频结果").addList("一级扇叶排频结果", FirstLevleBladeExcelModel.class, firstLevleBladeExcelModels);
            bladeExcelBuilder.write(outputStream);
        } else {
            List<SecondLevelBladeExcelModel> secondLevelBladeExcelModels = bladeSlots.stream().map(this::convert).collect(Collectors.toList());
            BladeExcelBuilder bladeExcelBuilder = new BladeExcelBuilder();
            bladeExcelBuilder.sheet("二级扇叶排频结果").addList("二级扇叶排频结果", SecondLevelBladeExcelModel.class, secondLevelBladeExcelModels);
            bladeExcelBuilder.write(outputStream);
        }

    }

    private FirstLevleBladeExcelModel format(BladeSlotData data) {
        FirstLevleBladeExcelModel model = new FirstLevleBladeExcelModel();
        BeanUtil.copyProperties(data, model);
        return model;
    }

    private SecondLevelBladeExcelModel convert(BladeSlotData data) {
        SecondLevelBladeExcelModel model = new SecondLevelBladeExcelModel();
        BeanUtil.copyProperties(data, model);
        return model;
    }

    private boolean isFirstLevelBlade(List<BladeSlotData> bladeSlots) {
        return bladeSlots.size() == FIRST_LEVEL_BLADE_NUMBER;
    }
}
