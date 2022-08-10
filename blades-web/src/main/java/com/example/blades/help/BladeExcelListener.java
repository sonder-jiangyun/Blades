package com.example.blades.help;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.blades.service.blade.Blade;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BladeExcelListener extends AnalysisEventListener<BladeExcelListener.BladeItem> {

    private List<Blade> blades = new ArrayList<>();

    @Override
    public void invoke(BladeItem bladeItem, AnalysisContext analysisContext) {
        Blade blade = this.format(bladeItem);
        blades.add(blade);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private Blade format(BladeItem bladeItem) {
        String bladeName = bladeItem.getBladeName();
        Integer wValue = bladeItem.getBladeValueW();
        Integer zValue = bladeItem.getBladeValueZ();

        Blade blade = new Blade();
        blade.setName(bladeName);
        blade.setWValue(wValue);
        blade.setZValue(zValue);
        return blade;
    }

    @Data
    public static class BladeItem {

        /**
         * 序号
         */
        @ExcelProperty(index = 0)
        private Long id;

        /**
         * 扇叶名称
         */
        @ExcelProperty(index = 1)
        private String bladeName;

        /**
         * 弯曲频率
         */
        @ExcelProperty(index = 2)
        private Integer bladeValueW;

        /**
         * 扭转频率
         */
        @ExcelProperty(index = 3)
        private Integer bladeValueZ;
    }
}
