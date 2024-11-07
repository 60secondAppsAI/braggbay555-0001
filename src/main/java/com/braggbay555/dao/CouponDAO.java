package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.Coupon;





public interface CouponDAO extends GenericDAO<Coupon, Integer> {
  
	List<Coupon> findAll();
	






}


