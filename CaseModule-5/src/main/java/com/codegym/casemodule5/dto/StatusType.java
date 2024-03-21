package com.codegym.casemodule5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {
    IS_ACTIVATED(true),
    NOT_ACTIVATED(false);
    private final Boolean isActivated;
}
