package com.braggbay555.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay555.dao.GenericDAO;
import com.braggbay555.service.GenericService;
import com.braggbay555.service.impl.GenericServiceImpl;
import com.braggbay555.dao.DisputeDAO;
import com.braggbay555.domain.Dispute;
import com.braggbay555.dto.DisputeDTO;
import com.braggbay555.dto.DisputeSearchDTO;
import com.braggbay555.dto.DisputePageDTO;
import com.braggbay555.dto.DisputeConvertCriteriaDTO;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import com.braggbay555.service.DisputeService;
import com.braggbay555.util.ControllerUtils;





@Service
public class DisputeServiceImpl extends GenericServiceImpl<Dispute, Integer> implements DisputeService {

    private final static Logger logger = LoggerFactory.getLogger(DisputeServiceImpl.class);

	@Autowired
	DisputeDAO disputeDao;

	


	@Override
	public GenericDAO<Dispute, Integer> getDAO() {
		return (GenericDAO<Dispute, Integer>) disputeDao;
	}
	
	public List<Dispute> findAll () {
		List<Dispute> disputes = disputeDao.findAll();
		
		return disputes;	
		
	}

	public ResultDTO addDispute(DisputeDTO disputeDTO, RequestDTO requestDTO) {

		Dispute dispute = new Dispute();

		dispute.setDisputeId(disputeDTO.getDisputeId());


		dispute.setDescription(disputeDTO.getDescription());


		dispute.setStatus(disputeDTO.getStatus());


		dispute.setResolutionDate(disputeDTO.getResolutionDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		dispute = disputeDao.save(dispute);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Dispute> getAllDisputes(Pageable pageable) {
		return disputeDao.findAll(pageable);
	}

	public Page<Dispute> getAllDisputes(Specification<Dispute> spec, Pageable pageable) {
		return disputeDao.findAll(spec, pageable);
	}

	public ResponseEntity<DisputePageDTO> getDisputes(DisputeSearchDTO disputeSearchDTO) {
	
			Integer disputeId = disputeSearchDTO.getDisputeId(); 
 			String description = disputeSearchDTO.getDescription(); 
 			String status = disputeSearchDTO.getStatus(); 
   			String sortBy = disputeSearchDTO.getSortBy();
			String sortOrder = disputeSearchDTO.getSortOrder();
			String searchQuery = disputeSearchDTO.getSearchQuery();
			Integer page = disputeSearchDTO.getPage();
			Integer size = disputeSearchDTO.getSize();

	        Specification<Dispute> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, disputeId, "disputeId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Dispute> disputes = this.getAllDisputes(spec, pageable);
		
		//System.out.println(String.valueOf(disputes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(disputes.getTotalPages()));
		
		List<Dispute> disputesList = disputes.getContent();
		
		DisputeConvertCriteriaDTO convertCriteria = new DisputeConvertCriteriaDTO();
		List<DisputeDTO> disputeDTOs = this.convertDisputesToDisputeDTOs(disputesList,convertCriteria);
		
		DisputePageDTO disputePageDTO = new DisputePageDTO();
		disputePageDTO.setDisputes(disputeDTOs);
		disputePageDTO.setTotalElements(disputes.getTotalElements());
		return ResponseEntity.ok(disputePageDTO);
	}

	public List<DisputeDTO> convertDisputesToDisputeDTOs(List<Dispute> disputes, DisputeConvertCriteriaDTO convertCriteria) {
		
		List<DisputeDTO> disputeDTOs = new ArrayList<DisputeDTO>();
		
		for (Dispute dispute : disputes) {
			disputeDTOs.add(convertDisputeToDisputeDTO(dispute,convertCriteria));
		}
		
		return disputeDTOs;

	}
	
	public DisputeDTO convertDisputeToDisputeDTO(Dispute dispute, DisputeConvertCriteriaDTO convertCriteria) {
		
		DisputeDTO disputeDTO = new DisputeDTO();
		
		disputeDTO.setDisputeId(dispute.getDisputeId());

	
		disputeDTO.setDescription(dispute.getDescription());

	
		disputeDTO.setStatus(dispute.getStatus());

	
		disputeDTO.setResolutionDate(dispute.getResolutionDate());

	

		
		return disputeDTO;
	}

	public ResultDTO updateDispute(DisputeDTO disputeDTO, RequestDTO requestDTO) {
		
		Dispute dispute = disputeDao.getById(disputeDTO.getDisputeId());

		dispute.setDisputeId(ControllerUtils.setValue(dispute.getDisputeId(), disputeDTO.getDisputeId()));

		dispute.setDescription(ControllerUtils.setValue(dispute.getDescription(), disputeDTO.getDescription()));

		dispute.setStatus(ControllerUtils.setValue(dispute.getStatus(), disputeDTO.getStatus()));

		dispute.setResolutionDate(ControllerUtils.setValue(dispute.getResolutionDate(), disputeDTO.getResolutionDate()));



        dispute = disputeDao.save(dispute);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DisputeDTO getDisputeDTOById(Integer disputeId) {
	
		Dispute dispute = disputeDao.getById(disputeId);
			
		
		DisputeConvertCriteriaDTO convertCriteria = new DisputeConvertCriteriaDTO();
		return(this.convertDisputeToDisputeDTO(dispute,convertCriteria));
	}







}
