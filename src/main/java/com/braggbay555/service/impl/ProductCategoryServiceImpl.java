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
import com.braggbay555.dao.ProductCategoryDAO;
import com.braggbay555.domain.ProductCategory;
import com.braggbay555.dto.ProductCategoryDTO;
import com.braggbay555.dto.ProductCategorySearchDTO;
import com.braggbay555.dto.ProductCategoryPageDTO;
import com.braggbay555.dto.ProductCategoryConvertCriteriaDTO;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import com.braggbay555.service.ProductCategoryService;
import com.braggbay555.util.ControllerUtils;





@Service
public class ProductCategoryServiceImpl extends GenericServiceImpl<ProductCategory, Integer> implements ProductCategoryService {

    private final static Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

	@Autowired
	ProductCategoryDAO productCategoryDao;

	


	@Override
	public GenericDAO<ProductCategory, Integer> getDAO() {
		return (GenericDAO<ProductCategory, Integer>) productCategoryDao;
	}
	
	public List<ProductCategory> findAll () {
		List<ProductCategory> productCategorys = productCategoryDao.findAll();
		
		return productCategorys;	
		
	}

	public ResultDTO addProductCategory(ProductCategoryDTO productCategoryDTO, RequestDTO requestDTO) {

		ProductCategory productCategory = new ProductCategory();

		productCategory.setProductCategoryId(productCategoryDTO.getProductCategoryId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		productCategory = productCategoryDao.save(productCategory);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ProductCategory> getAllProductCategorys(Pageable pageable) {
		return productCategoryDao.findAll(pageable);
	}

	public Page<ProductCategory> getAllProductCategorys(Specification<ProductCategory> spec, Pageable pageable) {
		return productCategoryDao.findAll(spec, pageable);
	}

	public ResponseEntity<ProductCategoryPageDTO> getProductCategorys(ProductCategorySearchDTO productCategorySearchDTO) {
	
			Integer productCategoryId = productCategorySearchDTO.getProductCategoryId(); 
 			String sortBy = productCategorySearchDTO.getSortBy();
			String sortOrder = productCategorySearchDTO.getSortOrder();
			String searchQuery = productCategorySearchDTO.getSearchQuery();
			Integer page = productCategorySearchDTO.getPage();
			Integer size = productCategorySearchDTO.getSize();

	        Specification<ProductCategory> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, productCategoryId, "productCategoryId"); 
			

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

		Page<ProductCategory> productCategorys = this.getAllProductCategorys(spec, pageable);
		
		//System.out.println(String.valueOf(productCategorys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(productCategorys.getTotalPages()));
		
		List<ProductCategory> productCategorysList = productCategorys.getContent();
		
		ProductCategoryConvertCriteriaDTO convertCriteria = new ProductCategoryConvertCriteriaDTO();
		List<ProductCategoryDTO> productCategoryDTOs = this.convertProductCategorysToProductCategoryDTOs(productCategorysList,convertCriteria);
		
		ProductCategoryPageDTO productCategoryPageDTO = new ProductCategoryPageDTO();
		productCategoryPageDTO.setProductCategorys(productCategoryDTOs);
		productCategoryPageDTO.setTotalElements(productCategorys.getTotalElements());
		return ResponseEntity.ok(productCategoryPageDTO);
	}

	public List<ProductCategoryDTO> convertProductCategorysToProductCategoryDTOs(List<ProductCategory> productCategorys, ProductCategoryConvertCriteriaDTO convertCriteria) {
		
		List<ProductCategoryDTO> productCategoryDTOs = new ArrayList<ProductCategoryDTO>();
		
		for (ProductCategory productCategory : productCategorys) {
			productCategoryDTOs.add(convertProductCategoryToProductCategoryDTO(productCategory,convertCriteria));
		}
		
		return productCategoryDTOs;

	}
	
	public ProductCategoryDTO convertProductCategoryToProductCategoryDTO(ProductCategory productCategory, ProductCategoryConvertCriteriaDTO convertCriteria) {
		
		ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
		
		productCategoryDTO.setProductCategoryId(productCategory.getProductCategoryId());

	

		
		return productCategoryDTO;
	}

	public ResultDTO updateProductCategory(ProductCategoryDTO productCategoryDTO, RequestDTO requestDTO) {
		
		ProductCategory productCategory = productCategoryDao.getById(productCategoryDTO.getProductCategoryId());

		productCategory.setProductCategoryId(ControllerUtils.setValue(productCategory.getProductCategoryId(), productCategoryDTO.getProductCategoryId()));



        productCategory = productCategoryDao.save(productCategory);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ProductCategoryDTO getProductCategoryDTOById(Integer productCategoryId) {
	
		ProductCategory productCategory = productCategoryDao.getById(productCategoryId);
			
		
		ProductCategoryConvertCriteriaDTO convertCriteria = new ProductCategoryConvertCriteriaDTO();
		return(this.convertProductCategoryToProductCategoryDTO(productCategory,convertCriteria));
	}







}
