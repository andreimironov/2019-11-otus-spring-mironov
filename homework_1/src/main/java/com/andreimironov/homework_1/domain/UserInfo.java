package com.andreimironov.homework_1.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private final String name;
    private final String surName;
}
