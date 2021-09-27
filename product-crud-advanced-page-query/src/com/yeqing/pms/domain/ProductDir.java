package com.yeqing.pms.domain;

import com.yeqing.pms.util.ann.Id;
import com.yeqing.pms.util.ann.Table;

import lombok.Data;

//商品分类信息
@Data
@Table("product_dir")
public class ProductDir {
	@Id
	private Long id;
    private String dirName;
    private String note;
    private Float profitMargin;
}
