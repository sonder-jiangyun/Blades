package com.example.blades.service;


import com.example.blades.common.BladeLevelType;
import com.example.blades.service.blade.Blade;
import com.example.blades.service.blade.Slot;

import java.util.List;

public interface BladeService {

    List<Slot> arrange(List<Blade> blades, BladeLevelType bladeLevelType);
}
