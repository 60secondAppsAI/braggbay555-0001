package com.braggbay555.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay555.domain.Dispute;
import com.braggbay555.dto.DisputeDTO;
import com.braggbay555.dto.DisputeSearchDTO;
import com.braggbay555.dto.DisputePageDTO;
import com.braggbay555.dto.DisputeConvertCriteriaDTO;
import com.braggbay555.service.GenericService;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DisputeService extends GenericService<Dispute, Integer> {

	List<Dispute> findAll();

	ResultDTO addDispute(DisputeDTO disputeDTO, RequestDTO requestDTO);

	ResultDTO updateDispute(DisputeDTO disputeDTO, RequestDTO requestDTO);

    Page<Dispute> getAllDisputes(Pageable pageable);

    Page<Dispute> getAllDisputes(Specification<Dispute> spec, Pageable pageable);

	ResponseEntity<DisputePageDTO> getDisputes(DisputeSearchDTO disputeSearchDTO);
	
	List<DisputeDTO> convertDisputesToDisputeDTOs(List<Dispute> disputes, DisputeConvertCriteriaDTO convertCriteria);

	DisputeDTO getDisputeDTOById(Integer disputeId);







}





