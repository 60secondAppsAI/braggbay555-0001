package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.ProductCategory;





public interface ProductCategoryDAO extends GenericDAO<ProductCategory, Integer> {
  
	List<ProductCategory> findAll();
	






}


