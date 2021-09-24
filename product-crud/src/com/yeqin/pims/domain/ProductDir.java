package com.yeqin.pims.domain;

import com.yeqin.pims.ann.Table;

import lombok.Data;

@Data
@Table("product_dir")
public class ProductDir {
    private Long id;
    private String dirName;
    private String note;
    private Float profitMargin;
}
