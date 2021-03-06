package com.yeqing.jdbc.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.yeqing.jdbc.dao.IStudentDAO;
import com.yeqing.jdbc.dao.impl.StudentDAOImpl;
import com.yeqing.jdbc.domain.Student;
import com.yeqing.jdbc.handler.IResultSetHandler;
import com.yeqing.jdbc.util.JdbcTemplate;

public class StudentDAOTest {
    private IStudentDAO studentDAO = new StudentDAOImpl();
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setName("阿紫");
		stu.setAge(23);
		studentDAO.save(stu);
	}

	@Test
	public void testDelete() {
		studentDAO.delete(64425L);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student();
		stu.setName("AAA");
		stu.setAge(29);
		studentDAO.update(64436L, stu);
	}

	@Test
	public void testGet() {
		Student stu = studentDAO.get(64434L);
		System.out.println(stu);
	}

	@Test
	public void testListAll() {
		List<Student> list = studentDAO.listAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}
    @Test
	public void testCount() throws Exception {
		String sql = "SELECT COUNT(id) FROM t_students";
		Long totalCount = JdbcTemplate.query(sql, new IResultSetHandler<Long>() {
			public Long handler(ResultSet rs) {
				try {
					if(rs.next()) {
						return rs.getLong(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0L;
			}
		});
        System.out.println(totalCount);		
	}
    @Test
	public void testDesignatedQuery() throws Exception {
		String sql = "SELECT name FROM t_students WHERE id = ?";
		String name = JdbcTemplate.query(sql, new IResultSetHandler<String>() {
			public String handler(ResultSet rs) {
				try {
					if(rs.next()) {
						return rs.getString(1);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
		}, 5L);
		System.out.println(name);
	}
}
