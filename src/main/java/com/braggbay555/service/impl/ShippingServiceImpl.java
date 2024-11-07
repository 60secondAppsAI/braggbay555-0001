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
import com.braggbay555.dao.ShippingDAO;
import com.braggbay555.domain.Shipping;
import com.braggbay555.dto.ShippingDTO;
import com.braggbay555.dto.ShippingSearchDTO;
import com.braggbay555.dto.ShippingPageDTO;
import com.braggbay555.dto.ShippingConvertCriteriaDTO;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import com.braggbay555.service.ShippingService;
import com.braggbay555.util.ControllerUtils;





@Service
public class ShippingServiceImpl extends GenericServiceImpl<Shipping, Integer> implements ShippingService {

    private final static Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);

	@Autowired
	ShippingDAO shippingDao;

	


	@Override
	public GenericDAO<Shipping, Integer> getDAO() {
		return (GenericDAO<Shipping, Integer>) shippingDao;
	}
	
	public List<Shipping> findAll () {
		List<Shipping> shippings = shippingDao.findAll();
		
		return shippings;	
		
	}

	public ResultDTO addShipping(ShippingDTO shippingDTO, RequestDTO requestDTO) {

		Shipping shipping = new Shipping();

		shipping.setShippingId(shippingDTO.getShippingId());


		shipping.setTrackingNumber(shippingDTO.getTrackingNumber());


		shipping.setShippingDate(shippingDTO.getShippingDate());


		shipping.setDeliveryDate(shippingDTO.getDeliveryDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shipping = shippingDao.save(shipping);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Shipping> getAllShippings(Pageable pageable) {
		return shippingDao.findAll(pageable);
	}

	public Page<Shipping> getAllShippings(Specification<Shipping> spec, Pageable pageable) {
		return shippingDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShippingPageDTO> getShippings(ShippingSearchDTO shippingSearchDTO) {
	
			Integer shippingId = shippingSearchDTO.getShippingId(); 
 			String trackingNumber = shippingSearchDTO.getTrackingNumber(); 
     			String sortBy = shippingSearchDTO.getSortBy();
			String sortOrder = shippingSearchDTO.getSortOrder();
			String searchQuery = shippingSearchDTO.getSearchQuery();
			Integer page = shippingSearchDTO.getPage();
			Integer size = shippingSearchDTO.getSize();

	        Specification<Shipping> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shippingId, "shippingId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, trackingNumber, "trackingNumber"); 
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("trackingNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Shipping> shippings = this.getAllShippings(spec, pageable);
		
		//System.out.println(String.valueOf(shippings.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shippings.getTotalPages()));
		
		List<Shipping> shippingsList = shippings.getContent();
		
		ShippingConvertCriteriaDTO convertCriteria = new ShippingConvertCriteriaDTO();
		List<ShippingDTO> shippingDTOs = this.convertShippingsToShippingDTOs(shippingsList,convertCriteria);
		
		ShippingPageDTO shippingPageDTO = new ShippingPageDTO();
		shippingPageDTO.setShippings(shippingDTOs);
		shippingPageDTO.setTotalElements(shippings.getTotalElements());
		return ResponseEntity.ok(shippingPageDTO);
	}

	public List<ShippingDTO> convertShippingsToShippingDTOs(List<Shipping> shippings, ShippingConvertCriteriaDTO convertCriteria) {
		
		List<ShippingDTO> shippingDTOs = new ArrayList<ShippingDTO>();
		
		for (Shipping shipping : shippings) {
			shippingDTOs.add(convertShippingToShippingDTO(shipping,convertCriteria));
		}
		
		return shippingDTOs;

	}
	
	public ShippingDTO convertShippingToShippingDTO(Shipping shipping, ShippingConvertCriteriaDTO convertCriteria) {
		
		ShippingDTO shippingDTO = new ShippingDTO();
		
		shippingDTO.setShippingId(shipping.getShippingId());

	
		shippingDTO.setTrackingNumber(shipping.getTrackingNumber());

	
		shippingDTO.setShippingDate(shipping.getShippingDate());

	
		shippingDTO.setDeliveryDate(shipping.getDeliveryDate());

	

		
		return shippingDTO;
	}

	public ResultDTO updateShipping(ShippingDTO shippingDTO, RequestDTO requestDTO) {
		
		Shipping shipping = shippingDao.getById(shippingDTO.getShippingId());

		shipping.setShippingId(ControllerUtils.setValue(shipping.getShippingId(), shippingDTO.getShippingId()));

		shipping.setTrackingNumber(ControllerUtils.setValue(shipping.getTrackingNumber(), shippingDTO.getTrackingNumber()));

		shipping.setShippingDate(ControllerUtils.setValue(shipping.getShippingDate(), shippingDTO.getShippingDate()));

		shipping.setDeliveryDate(ControllerUtils.setValue(shipping.getDeliveryDate(), shippingDTO.getDeliveryDate()));



        shipping = shippingDao.save(shipping);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShippingDTO getShippingDTOById(Integer shippingId) {
	
		Shipping shipping = shippingDao.getById(shippingId);
			
		
		ShippingConvertCriteriaDTO convertCriteria = new ShippingConvertCriteriaDTO();
		return(this.convertShippingToShippingDTO(shipping,convertCriteria));
	}







}
