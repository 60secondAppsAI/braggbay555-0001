package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.Order;





public interface OrderDAO extends GenericDAO<Order, Integer> {
  
	List<Order> findAll();
	






}


