package com.yeqin.pims.dao.impl;

import java.util.List;

import com.yeqin.pims.dao.IProductSubDirDAO;
import com.yeqin.pims.domain.ProductSubDir;
import com.yeqin.pims.util.MyHibernate;

public class ProductSubDirDAOImpl implements IProductSubDirDAO {
	
	private MyHibernate<ProductSubDir> h = new MyHibernate<>(ProductSubDir.class);
	public void save(ProductSubDir obj) {
		h.save(obj);
	}

	public void delete(Long id) {
		h.delete(id);
	}

	public void update(ProductSubDir obj) {
		h.update(obj);
	}

	public ProductSubDir get(Long id) {
		return h.get(id);
	}

	public List<ProductSubDir> listAll() {
		return h.getAll();
	}

}
