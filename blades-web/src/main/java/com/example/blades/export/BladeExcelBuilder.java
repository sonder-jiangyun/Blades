package com.example.blades.export;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;

import java.io.OutputStream;
import java.util.List;


public class BladeExcelBuilder {

    private BladeExcelSheetBuilder bladeExcelSheetBuilder;

    public BladeExcelSheetBuilder sheet(String name) {
        BladeExcelSheetBuilder bladeExcelSheetBuilder = new BladeExcelSheetBuilder(name);
        this.bladeExcelSheetBuilder = bladeExcelSheetBuilder;
        return bladeExcelSheetBuilder;
    }

    public void write(OutputStream outputStream) {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = this.prepareExcelWriter(outputStream);
            this.writeSheet(excelWriter, bladeExcelSheetBuilder);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    private void writeSheet(ExcelWriter excelWriter, BladeExcelSheetBuilder sheetBuilder) {
        String sheetName = sheetBuilder.getName();
        List<BladeExcelSheetBuilder.SheetPart> sheetParts = sheetBuilder.getParts();

        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();

        BladeExcelSheetBuilder.SheetPart sheetPart = sheetParts.get(0);
        this.writeSheetPart(excelWriter, writeSheet, sheetPart);

    }

    private void writeSheetPart(ExcelWriter excelWriter, WriteSheet writeSheet, BladeExcelSheetBuilder.SheetPart sheetPart) {
        String title = sheetPart.getTitle();
        Class dataClass = sheetPart.getDataClass();
        List dataList = sheetPart.getDataList();

        WriteTable writeTable = EasyExcel.writerTable().head(dataClass).build();
        if (title != null) {
            writeTable.setHead(DataUtils.generateHead(dataClass, title));
        }
        excelWriter.write(dataList, writeSheet, writeTable);
    }

    private ExcelWriter prepareExcelWriter(OutputStream outputStream) {
        WriteCellStyle headWriteCellStyle = Styles.head();
        WriteCellStyle contentWriteCellStyle = Styles.content();
        HorizontalCellStyleStrategy cellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        return EasyExcel.write(outputStream).registerWriteHandler(cellStyleStrategy).build();
    }

}
