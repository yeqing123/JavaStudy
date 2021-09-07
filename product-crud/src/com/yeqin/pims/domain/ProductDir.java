package com.yeqin.pims.domain;

import com.yeqin.pims.util.ann.TableName;

import lombok.Data;

//商品所属目录
@Data
@TableName("product_dir")
public class ProductDir {
    private Long id;  //目录项的ID
    private String dirName; //目录名称
    private String note;  //简介
    private Float profitMargin; //利润率
}
