package com.yeqing.pms.domain;

import java.math.BigDecimal;

import com.yeqing.pms.util.ann.Column;
import com.yeqing.pms.util.ann.Id;
import com.yeqing.pms.util.ann.IdType;
import com.yeqing.pms.util.ann.Table;

import lombok.Data;

//商品信息
@Data
@Table("product")
public class Product {
	@Id(IdType.AUTO_INCREMENT)  //该注解表示该属性对应的是表的主键，如果不写参数值，默认为IdType.AUTO_INCREMENT
	@Column("pid")  //标注了属性对应的列名
	private Long id;          //商品ID
    private String name;      //商品名称
    private String note;      //商品简介
    private BigDecimal price; //商品单价
    private Integer count;    //商品库存数量
    private Long dir;         //商品分类编号
}