package com.yeqing._05_blob;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;

public class BlobTest {
    //将一张图片以二进制形式保存到数据库中
	@Test
	public void testSaveImage() throws Exception {
		String sql = "INSERT INTO t_image (img) VALUES (?)";
    	Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBlob(1, new FileInputStream("D:/sam.png"));
		ps.executeUpdate();
		JdbcUtil.close(conn, ps, null);
	}
	// 取回数据库中的一张图片，保存成文件
	@Test
	public void testQueryImage() throws Exception {
		String sql = "SELECT * FROM t_image WHERE id = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, 1L);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	Blob blob = rs.getBlob("img");
        	InputStream in = blob.getBinaryStream();
        	Files.copy(in, Paths.get("D:/123.png"));
        }
        JdbcUtil.close(conn, ps, rs);
	}
}
