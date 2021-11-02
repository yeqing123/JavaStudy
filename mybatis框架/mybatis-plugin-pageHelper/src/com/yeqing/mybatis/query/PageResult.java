package com.yeqing.mybatis.query;

import java.util.List;

import lombok.Getter;

//封装了分页查询的页面信息
@Getter
public class PageResult {
	private List<?> result; // 查询出的数据集合
	private Integer currentPage; // 用户需要跳转的页面，默认是1
	private Integer pageSize; // 每页最多容纳的数据，默认是3
	private Integer prevPage; // 上一页
	private Integer nextPage; // 下一页
	private Integer totalCount; // 符合条件的数据的总数
	private Integer totalPage; // 末页

	public PageResult(List<?> result, Integer currentPage, Integer pageSize, Integer totalCount) {
		this.result = result;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.prevPage = currentPage - 1 <= 1 ? 1 : currentPage - 1;
		this.nextPage = currentPage + 1 >= totalPage ? totalPage : currentPage + 1;
		currentPage = currentPage > totalPage ? totalPage : currentPage;
	}
}
