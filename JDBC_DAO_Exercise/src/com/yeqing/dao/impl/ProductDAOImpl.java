package com.yeqing.dao.impl;

import java.util.List;
import com.yeqing.dao.IProductDAO;
import com.yeqing.domain.Product;
import com.yeqing.util.JdbcTemplate;

public class ProductDAOImpl implements IProductDAO {

	@Override
	public void save(Product p) {
		String sql = "INSERT INTO product (name,note,price,count,types_id,subtypes_id)" + 
	            " VALUES (?,?,?,?,?,?)";
		JdbcTemplate.update(sql, p.getName(), p.getNote(), p.getPrice(), 
				p.getCount(),p.getTypes_id(),p.getSubtypes_id());
	}

	@Override
	public void delete(Long pid) {
		// 拼接SQL语句
		String sql = "DELETE FROM product WHERE pid = ?";
		JdbcTemplate.update(sql, pid);
	}

	@Override
	public void update(Long pid, Product newP) {
		// 拼接SQL语句
		String sql = "UPDATE product SET name = ?,note = ?,price = ?,count = ?" +
		        ",types_id = ?,subtypes_id = ? WHERE pid = ?";
		JdbcTemplate.update(sql, newP.getName(), newP.getNote(), newP.getPrice(), 
				newP.getCount(), newP.getTypes_id(), newP.getSubtypes_id(), pid);
	}

	@Override
	public Product get(Long pid) {
		String sql = "SELECT * FROM product WHERE pid = ?";
		List<Product> list = JdbcTemplate.query(sql, Product.class, pid);
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<Product> getAll() {
		String sql = "SELECT * FROM product";
		return JdbcTemplate.query(sql, Product.class);
	}

}
