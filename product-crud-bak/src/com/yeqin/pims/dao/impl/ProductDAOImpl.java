package com.yeqin.pims.dao.impl;

import java.util.List;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.domain.Product;
import com.yeqin.pims.util.MyHibernate;

public class ProductDAOImpl implements IProductDAO {
    private MyHibernate<Product> h = new MyHibernate<>(Product.class); //使用自定义的工具类MyHibernate，在配合其他工具类，可以将DAO的实现简化的极致
	public void save(Product p) {
        h.save(p);
        
	}

	public void delete(Long id) {
		h.delete(id);
	}

	public void update(Product p) {
		h.update(p);
	}

	public Product get(Long id) {
		return h.get(id);
	}

	public List<Product> listAll() {
		return h.getAll();
	}

}
