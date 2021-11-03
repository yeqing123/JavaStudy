package com.yeqing.mybatis.product.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeqing.mybatis.product.dao.mapper.ProductMapper;
import com.yeqing.mybatis.product.dao.mapper.SubDirMapper;
import com.yeqing.mybatis.product.dao.mapper.UserMapper;
import com.yeqing.mybatis.product.dao.util.MybatisUtil;
import com.yeqing.mybatis.product.domain.Product;
import com.yeqing.mybatis.product.domain.SubDir;
import com.yeqing.mybatis.product.domain.User;
import com.yeqing.mybatis.product.query.QueryObject;

public class App {
	@Test // 测试查询出所有商品分类信息
	void testListAll() throws Exception {
		SubDirMapper subDirMapper = MybatisUtil.getMapper(SubDirMapper.class);
		List<SubDir> list = subDirMapper.listAll();
		for (SubDir sd : list) {
			System.out.println(sd);
		}
		MybatisUtil.close();
	}

	@Test //高级查询，并使用PageHelper插件进行分页查询
	void testQuery() throws Exception {
		ProductMapper productMapper = MybatisUtil.getMapper(ProductMapper.class);
		QueryObject qo = new QueryObject();
//		qo.setMinprice(new BigDecimal(30));
//		qo.setMaxprice(new BigDecimal(100));
		PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
		List<Product> list = productMapper.query(qo);
		PageInfo<Product> pageInfo = new PageInfo<>(list);
		System.out.println("--------------一共查询到" + pageInfo.getTotal() + "条数据----------------");
		for (Product p : pageInfo.getList()) {
			System.out.println(p);
		}
		System.out.println(
				"当前页：" + pageInfo.getPageNum() + ", 上页：" + pageInfo.getPrePage() + ", 下页：" + pageInfo.getNextPage()
						+ ", 末页：" + pageInfo.getPages() + "  每页最多显示" + pageInfo.getPageSize() + "条数据。");
		MybatisUtil.close();
	}
	@Test
	void testGetUser() throws Exception {
		UserMapper userMapper = MybatisUtil.getMapper(UserMapper.class);
		User u = userMapper.get("admin");
		System.out.println(u);
		MybatisUtil.close();
	}
}
