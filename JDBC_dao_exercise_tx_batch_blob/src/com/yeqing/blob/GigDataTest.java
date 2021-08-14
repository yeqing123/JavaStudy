package com.yeqing.blob;


import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.yeqing.exercise.util.JdbcTemplate;
import com.yeqing.exercise.util.JdbcUtil;

public class GigDataTest {
    @Test
    // 将一张图片和一段视频，以二进制的形式保存到数据库的t_image表中
	public void test1() throws Exception {
		String sql = "INSERT INTO t_image (img) VALUES (?)";
		JdbcTemplate.update(sql, new FileInputStream("C:/books.jpg"));
		JdbcTemplate.update(sql, new FileInputStream("C:/WeChat.mp4"));
	}
    // 从数据库中读取图片和视频，保存到电脑磁盘中
    @Test
	public void test2() throws Exception {
		String sql = "SELECT * FROM t_image WHERE id=?";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, 5);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Blob blob = rs.getBlob("img");  // 可以从结果集中获得一个Blob对象
			InputStream input = blob.getBinaryStream(); // 我们无法直接使用Blob对象来查看图片，只能得到 一个输入流
			// 拷贝文件到磁盘的指定位置
			Files.copy(input, Paths.get("C:/users/yeqin/desktop/WeChat.mp4"));
		}
		JdbcUtil.close(conn, ps, rs);
	}
}
