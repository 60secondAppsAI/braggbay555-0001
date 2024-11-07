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
import com.braggbay555.dao.AuctionDAO;
import com.braggbay555.domain.Auction;
import com.braggbay555.dto.AuctionDTO;
import com.braggbay555.dto.AuctionSearchDTO;
import com.braggbay555.dto.AuctionPageDTO;
import com.braggbay555.dto.AuctionConvertCriteriaDTO;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import com.braggbay555.service.AuctionService;
import com.braggbay555.util.ControllerUtils;





@Service
public class AuctionServiceImpl extends GenericServiceImpl<Auction, Integer> implements AuctionService {

    private final static Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);

	@Autowired
	AuctionDAO auctionDao;

	


	@Override
	public GenericDAO<Auction, Integer> getDAO() {
		return (GenericDAO<Auction, Integer>) auctionDao;
	}
	
	public List<Auction> findAll () {
		List<Auction> auctions = auctionDao.findAll();
		
		return auctions;	
		
	}

	public ResultDTO addAuction(AuctionDTO auctionDTO, RequestDTO requestDTO) {

		Auction auction = new Auction();

		auction.setAuctionId(auctionDTO.getAuctionId());


		auction.setStartDate(auctionDTO.getStartDate());


		auction.setEndDate(auctionDTO.getEndDate());


		auction.setStartPrice(auctionDTO.getStartPrice());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		auction = auctionDao.save(auction);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Auction> getAllAuctions(Pageable pageable) {
		return auctionDao.findAll(pageable);
	}

	public Page<Auction> getAllAuctions(Specification<Auction> spec, Pageable pageable) {
		return auctionDao.findAll(spec, pageable);
	}

	public ResponseEntity<AuctionPageDTO> getAuctions(AuctionSearchDTO auctionSearchDTO) {
	
			Integer auctionId = auctionSearchDTO.getAuctionId(); 
      			String sortBy = auctionSearchDTO.getSortBy();
			String sortOrder = auctionSearchDTO.getSortOrder();
			String searchQuery = auctionSearchDTO.getSearchQuery();
			Integer page = auctionSearchDTO.getPage();
			Integer size = auctionSearchDTO.getSize();

	        Specification<Auction> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, auctionId, "auctionId"); 
			
 			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Auction> auctions = this.getAllAuctions(spec, pageable);
		
		//System.out.println(String.valueOf(auctions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(auctions.getTotalPages()));
		
		List<Auction> auctionsList = auctions.getContent();
		
		AuctionConvertCriteriaDTO convertCriteria = new AuctionConvertCriteriaDTO();
		List<AuctionDTO> auctionDTOs = this.convertAuctionsToAuctionDTOs(auctionsList,convertCriteria);
		
		AuctionPageDTO auctionPageDTO = new AuctionPageDTO();
		auctionPageDTO.setAuctions(auctionDTOs);
		auctionPageDTO.setTotalElements(auctions.getTotalElements());
		return ResponseEntity.ok(auctionPageDTO);
	}

	public List<AuctionDTO> convertAuctionsToAuctionDTOs(List<Auction> auctions, AuctionConvertCriteriaDTO convertCriteria) {
		
		List<AuctionDTO> auctionDTOs = new ArrayList<AuctionDTO>();
		
		for (Auction auction : auctions) {
			auctionDTOs.add(convertAuctionToAuctionDTO(auction,convertCriteria));
		}
		
		return auctionDTOs;

	}
	
	public AuctionDTO convertAuctionToAuctionDTO(Auction auction, AuctionConvertCriteriaDTO convertCriteria) {
		
		AuctionDTO auctionDTO = new AuctionDTO();
		
		auctionDTO.setAuctionId(auction.getAuctionId());

	
		auctionDTO.setStartDate(auction.getStartDate());

	
		auctionDTO.setEndDate(auction.getEndDate());

	
		auctionDTO.setStartPrice(auction.getStartPrice());

	

		
		return auctionDTO;
	}

	public ResultDTO updateAuction(AuctionDTO auctionDTO, RequestDTO requestDTO) {
		
		Auction auction = auctionDao.getById(auctionDTO.getAuctionId());

		auction.setAuctionId(ControllerUtils.setValue(auction.getAuctionId(), auctionDTO.getAuctionId()));

		auction.setStartDate(ControllerUtils.setValue(auction.getStartDate(), auctionDTO.getStartDate()));

		auction.setEndDate(ControllerUtils.setValue(auction.getEndDate(), auctionDTO.getEndDate()));

		auction.setStartPrice(ControllerUtils.setValue(auction.getStartPrice(), auctionDTO.getStartPrice()));



        auction = auctionDao.save(auction);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AuctionDTO getAuctionDTOById(Integer auctionId) {
	
		Auction auction = auctionDao.getById(auctionId);
			
		
		AuctionConvertCriteriaDTO convertCriteria = new AuctionConvertCriteriaDTO();
		return(this.convertAuctionToAuctionDTO(auction,convertCriteria));
	}







}
