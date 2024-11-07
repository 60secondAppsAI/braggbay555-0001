package com.braggbay555.dao;

import java.util.List;

import com.braggbay555.dao.GenericDAO;
import com.braggbay555.domain.ReturnRequest;





public interface ReturnRequestDAO extends GenericDAO<ReturnRequest, Integer> {
  
	List<ReturnRequest> findAll();
	






}


