package com.braggbay555.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay555.domain.ReturnRequest;
import com.braggbay555.dto.ReturnRequestDTO;
import com.braggbay555.dto.ReturnRequestSearchDTO;
import com.braggbay555.dto.ReturnRequestPageDTO;
import com.braggbay555.dto.ReturnRequestConvertCriteriaDTO;
import com.braggbay555.service.GenericService;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ReturnRequestService extends GenericService<ReturnRequest, Integer> {

	List<ReturnRequest> findAll();

	ResultDTO addReturnRequest(ReturnRequestDTO returnRequestDTO, RequestDTO requestDTO);

	ResultDTO updateReturnRequest(ReturnRequestDTO returnRequestDTO, RequestDTO requestDTO);

    Page<ReturnRequest> getAllReturnRequests(Pageable pageable);

    Page<ReturnRequest> getAllReturnRequests(Specification<ReturnRequest> spec, Pageable pageable);

	ResponseEntity<ReturnRequestPageDTO> getReturnRequests(ReturnRequestSearchDTO returnRequestSearchDTO);
	
	List<ReturnRequestDTO> convertReturnRequestsToReturnRequestDTOs(List<ReturnRequest> returnRequests, ReturnRequestConvertCriteriaDTO convertCriteria);

	ReturnRequestDTO getReturnRequestDTOById(Integer returnRequestId);







}





