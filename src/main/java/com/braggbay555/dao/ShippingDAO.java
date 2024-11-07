package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.Shipping;





public interface ShippingDAO extends GenericDAO<Shipping, Integer> {
  
	List<Shipping> findAll();
	






}


