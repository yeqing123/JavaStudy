package com.yeqing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.dao.IProductDAO;
import com.yeqing.domain.Product;
import com.yeqing.util.JdbcUtil;

public class ProductDAOImpl implements IProductDAO {

	@Override
	public void save(Product p) {
		String sql = "INSERT INTO product (name,note,price,count,types_id,subtypes_id)" + 
	            " VALUES (?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1,加载注册驱动
			// 2,连接数据库
			conn = JdbcUtil.getConn();
			// 3,获得SQL语句对象
			ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getNote());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getCount());
            ps.setInt(5, p.getTypes_id());
            ps.setInt(6, p.getSubtypes_id());
			// 4,执行SQL
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		JdbcUtil.close(conn, ps, null);
	}

	@Override
	public void delete(Long pid) {
		// 拼接SQL语句
		String sql = "DELETE FROM product WHERE pid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1,加载注册驱动
			// 2,连接数据库
			conn = JdbcUtil.getConn();
			// 3,获得SQL语句对象
			ps = conn.prepareStatement(sql);
			ps.setLong(1, pid);
			// 4,执行SQL
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		JdbcUtil.close(conn, ps, null);
	}

	@Override
	public void update(Long pid, Product newP) {
		// 拼接SQL语句
		String sql = "UPDATE product SET name = ?,note = ?,price = ?,count = ?" +
		        ",types_id = ?,subtypes_id = ? WHERE pid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1,加载注册驱动
			// 2,连接数据库
			conn = JdbcUtil.getConn();
			// 3,获得SQL语句对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, newP.getName());
            ps.setString(2, newP.getNote());
            ps.setDouble(3, newP.getPrice());
            ps.setInt(4, newP.getCount());
            ps.setInt(5, newP.getTypes_id());
            ps.setInt(6, newP.getSubtypes_id());
            ps.setLong(7, pid);
			// 4,执行SQL
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		JdbcUtil.close(conn, ps, null);
	}

	@Override
	public Product get(Long pid) {
		// 拼接SQL
		String sql = "SELECT * FROM product WHERE pid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1,加载注册驱动
			// 2,连接数据库
			conn = JdbcUtil.getConn();
			// 3,获得SQL语句对象
			ps = conn.prepareStatement(sql);
            ps.setLong(1, pid);
			// 4,执行SQL
			rs = ps.executeQuery();
			// 处理结果集
			if (rs.next()) {
				Product p = new Product();
				p.setPid(rs.getLong("pid"));
				p.setName(rs.getString("name"));
				p.setNote(rs.getString("note"));
				p.setPrice(rs.getDouble("price"));
				p.setCount(rs.getInt("count"));
				p.setTypes_id(rs.getInt("types_id"));
				p.setSubtypes_id(rs.getInt("subtypes_id"));
				return p;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		JdbcUtil.close(conn, ps, rs);
		return null;
	}

	@Override
	public List<Product> getAll() {
		// 拼接SQL
		String sql = "SELECT * FROM product";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList();
		try {
			// 1,加载注册驱动
			// 2,连接数据库
			conn = JdbcUtil.getConn();
			// 3,获得SQL语句对象
			ps = conn.prepareStatement(sql);
			// 4,执行SQL
			rs = ps.executeQuery();
			// 处理结果集
			while (rs.next()) {
				Product p = new Product();
				p.setPid(rs.getLong("pid"));
				p.setName(rs.getString("name"));
				p.setNote(rs.getString("note"));
				p.setPrice(rs.getDouble("price"));
				p.setCount(rs.getInt("count"));
				p.setTypes_id(rs.getInt("types_id"));
				p.setSubtypes_id(rs.getInt("subtypes_id"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		JdbcUtil.close(conn, ps, rs);
		return list;
	}

}
