package com.yeqin.pims.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.yeqin.pims.ann.Table;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.IQuery;
import com.yeqin.pims.util.template.JdbcTemplate;
import com.yeqin.pims.util.template.handler.BeanListHandler;
import com.yeqin.pims.util.template.handler.IResultSetHandler;

//数据库查询操作的基本类型
public class BaseQuery {
	/**
	 * 无条件的查询所有信息
	 * @param classType 封装数据的对象类型
	 * @return 返回包含了所有数据对象的集合
	 */
    List<?> listAll(Class<?> classType) {
    	String sql = "SELECT * FROM " + getTableName(classType);
    	System.out.println(sql);
		return JdbcTemplate.query(sql, new BeanListHandler<>(classType));
    }
    
    /**
     * 高级查询+分页查询
     * @param classType 封装数据的对象类型
     * @param qc 封装了查询条件（包含高级查询和分页查询的相关信息）的对象（面向接口）
     * @return 返回封装了查询结果集及页面信息的对象
     */
    PageResult query(Class<?> classType, IQuery qo) {
    	//查询数据总数
    	StringBuilder countSql = new StringBuilder(80);
    	countSql.append("SELECT COUNT(*) FROM ").append(getTableName(classType)).append(qo.getConditions());
    	Integer totalCount = JdbcTemplate.query(countSql.toString(), new IResultSetHandler<Long>(){
			public Long handler(ResultSet rs) throws Exception {
				if(rs.next()) {
					return rs.getLong(1);
				}
				return 0L;
			}
    	}, qo.getParameters().toArray()).intValue();
    	System.out.println(countSql);
    	System.out.println("totalCount：" + totalCount);
    	//----------------------------------------------------
    	//执行条件查询，封装结果集和页面信息
    	StringBuilder resultSql = new StringBuilder(80);
    	resultSql.append("SELECT * FROM ").append(getTableName(classType)).append(qo.getConditions()).append(" LIMIT ?,?");
    	Integer currentPage = qo.getCurrentPage();
    	Integer pageSize = qo.getPageSize();
    	qo.getParameters().add((currentPage-1)*pageSize);
    	qo.getParameters().add(pageSize);
    	System.out.println(resultSql);
    	System.out.println("参数：" + qo.getParameters());
    	List<?> listData = JdbcTemplate.query(resultSql.toString(), new BeanListHandler<>(classType), qo.getParameters().toArray());
    	return new PageResult(listData, qo.getCurrentPage(), qo.getPageSize(), totalCount);
    	
    }
    private String getTableName(Class<?> classType) {
		String tableName = classType.getSimpleName();  //默认用类名作为表名
		Table table = classType.getAnnotation(Table.class);
		if(table != null) { //如果domain类型上添加了Table注解，就使用注解指定的表名
			tableName = table.value();
		}
		return tableName;
	}
}
