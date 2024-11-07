package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.Seller;





public interface SellerDAO extends GenericDAO<Seller, Integer> {
  
	List<Seller> findAll();
	






}


