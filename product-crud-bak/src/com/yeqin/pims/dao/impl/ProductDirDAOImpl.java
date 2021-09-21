package com.yeqin.pims.dao.impl;

import java.util.List;

import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.util.MyHibernate;

public class ProductDirDAOImpl implements IProductDirDAO {
   //使用自定义的MyHibernate工具类，将是接口实现类的实现方法变得非常简单	
    private MyHibernate<ProductDir> h = new MyHibernate<>(ProductDir.class);
	public void save(ProductDir obj) {
        h.save(obj);
	}

	public void delete(Long id) {
		h.delete(id);
	}

	public void update(ProductDir obj) {
		h.update(obj);
	}

	public ProductDir get(Long id) {
		return h.get(id);
	}

	public List<ProductDir> listAll() {
		return h.getAll();
	}

}
