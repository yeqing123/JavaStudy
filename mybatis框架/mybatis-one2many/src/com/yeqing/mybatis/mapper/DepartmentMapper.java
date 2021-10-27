package com.yeqing.mybatis.mapper;

import com.yeqing.mybatis.domain.Department;

public interface DepartmentMapper {

	void save(Department d);

	Department get4another(Long id);

	Department get4join(Long id);


}
