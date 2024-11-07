package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


