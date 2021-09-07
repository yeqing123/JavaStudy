package com.yeqin.pims.domain;

import java.math.BigDecimal;

import com.yeqin.pims.util.ann.ColumnName;
import com.yeqin.pims.util.ann.Id;
import com.yeqin.pims.util.ann.IdType;

import lombok.Data;

@Data
public class Product {
	@ColumnName("pid")
	@Id(IdType.AUTO_INCREMENT)
	private Long id; // 商品的ID
	private String name; // 商品名称
	private String note; // 商品简介
	private BigDecimal price; // 商品价格
	private Integer count; // 商品数量
	private Long dir; // 商品所属目录
	private Long subDir;// 商品所属子目录
}
