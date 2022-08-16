package com.example.blades.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class SecondLevelBladeExcelModel {

    @ColumnWidth(15)
    @ExcelProperty("序号")
    private String index;

    @ColumnWidth(15)
    @ExcelProperty("扇叶炉号编码")
    private String bladeName;

    @ColumnWidth(15)
    @ExcelProperty("弯曲频率")
    private Integer bending;

    @ColumnWidth(15)
    @ExcelProperty("弯曲频率差值")
    private Integer bendingDistance;

    @ColumnWidth(15)
    @ExcelProperty("区域编码")
    private String areaName;
}
