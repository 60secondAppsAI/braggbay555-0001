package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.Wishlist;





public interface WishlistDAO extends GenericDAO<Wishlist, Integer> {
  
	List<Wishlist> findAll();
	






}


