package com.yeqing.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yeqing.dao.IProductDAO;
import com.yeqing.domain.Product;

public class ProductDAOImpl implements IProductDAO {

	@Override
	public void save(Product p) {
		// 拼接SQL语句
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO product (name,note,price,count,types_id,subtypes_id) VALUES ('") //
				.append(p.getName()).append("','") // 商品名称
				.append(p.getNote()).append("',") // 商品简介
				.append(p.getPrice()).append(",") // 商品单价
				.append(p.getCount()).append(",") // 商品库存
				.append(p.getTypes_id()).append(",") // 商品分类ID
				.append(p.getSubtypes_id()).append(")"); // 商品子分类ID

		Connection conn = null;
		Statement st = null;
		try {
			// 1,加载注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2,连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai", "root",
					"mysqladmin");
			// 3,获得SQL语句对象
			st = conn.createStatement();
			// 4,执行SQL
			st.executeUpdate(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Long pid) {
		// 拼接SQL语句
		String sql = "DELETE FROM product WHERE pid=" + pid;
		Connection conn = null;
		Statement st = null;
		try {
			// 1,加载注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2,连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai", "root",
					"mysqladmin");
			// 3,获得SQL语句对象
			st = conn.createStatement();
			// 4,执行SQL
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Long pid, Product newP) {
		// 拼接SQL语句
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE product SET name='").append(newP.getName()).append("',") // 加入商品名称
				.append("note='").append(newP.getNote()).append("',") // 加入商品简介
				.append("price=").append(newP.getPrice()).append(",") // 加入商品单价
				.append("count=").append(newP.getCount()).append(",") // 加入商品库存
				.append("types_id=").append(newP.getTypes_id()).append(",") // 加入商品分类ID
				.append("subtypes_id=").append(newP.getSubtypes_id()) // 加入商品子分类
				.append(" WHERE pid=").append(pid); // 加入过滤条件
		Connection conn = null;
		Statement st = null;
		try {
			// 1,加载注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2,连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai", "root",
					"mysqladmin");
			// 3,获得SQL语句对象
			st = conn.createStatement();
			// 4,执行SQL
			st.executeUpdate(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5,释放资源
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Product get(Long pid) {
		// 拼接SQL
		String sql = "SELECT * FROM product WHERE pid=" + pid;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1,加载注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2,连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai", "root",
					"mysqladmin");
			// 3,获得SQL语句对象
			st = conn.createStatement();
			// 4,执行SQL
			rs = st.executeQuery(sql);
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
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public List<Product> getAll() {
		// 拼接SQL
		String sql = "SELECT * FROM product";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList();
		try {
			// 1,加载注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2,连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai", "root",
					"mysqladmin");
			// 3,获得SQL语句对象
			st = conn.createStatement();
			// 4,执行SQL
			rs = st.executeQuery(sql);
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
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
