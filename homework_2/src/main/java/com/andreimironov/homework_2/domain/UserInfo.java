package com.andreimironov.homework_2.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private final String name;
    private final String surName;
}
