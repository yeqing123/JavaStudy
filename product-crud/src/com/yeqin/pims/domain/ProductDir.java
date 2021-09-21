package com.yeqin.pims.domain;

import lombok.Data;

@Data
public class ProductDir {
    private Long id;
    private String dirName;
    private String note;
    private Float profitMargin;
}
