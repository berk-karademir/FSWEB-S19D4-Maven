package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;

import java.util.List;

public record MovieResponse(
        Long id,
        String name,
        String directorName,
        double rating,
        List<Actor>actors
){}
