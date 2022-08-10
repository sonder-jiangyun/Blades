package com.example.blades.service.impl;


import com.alibaba.excel.EasyExcel;
import com.example.blades.help.BladeExcelListener;
import com.example.blades.service.BladeDataParser;
import com.example.blades.service.blade.Blade;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelParserForFirstLevel implements BladeDataParser {

    @Override
    public List<Blade> parse(MultipartFile file) {
        try {
            BladeExcelListener listener = new BladeExcelListener();
            EasyExcel.read(file.getInputStream(), BladeExcelListener.BladeItem.class, listener)
                    .headRowNumber(2).sheet().doRead();

            return listener.getBlades();
        } catch (IOException e) {
            throw new IllegalStateException("解析Excel文件获取扇叶数据失败");
        }
    }

}
