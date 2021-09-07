package com.yeqin.pims.domain;

import com.yeqin.pims.util.ann.TableName;

import lombok.Data;

//商品的所属子目录
@Data
@TableName("product_subdir")
public class ProductSubDir {
	private Long id; //子目录ID
	private Long dir; //子目录所属的目录ID
	private String subDirName; //名称
	private String note; //简介
}
