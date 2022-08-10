package com.example.blades.service;


import com.example.blades.service.blade.Blade;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BladeDataParser {

    List<Blade> parse(MultipartFile file);

}
