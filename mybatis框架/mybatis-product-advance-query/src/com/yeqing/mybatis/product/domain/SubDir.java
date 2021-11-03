package com.yeqing.mybatis.product.domain;

import lombok.Data;

//封装了jdbcdemo数据库中product_subdir表中商品分类的信息
@Data
public class SubDir {
    private Long id;   //分类的ID
    private String name;  //分类的名称
}
