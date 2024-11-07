package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.Dispute;





public interface DisputeDAO extends GenericDAO<Dispute, Integer> {
  
	List<Dispute> findAll();
	






}


