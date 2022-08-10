package com.example.blades.service.blade.check;

import com.example.blades.service.blade.Slot;

import java.util.List;

public interface CheckRule {

    boolean check(Slot slot);

    boolean check(List<Slot> slots);
}
