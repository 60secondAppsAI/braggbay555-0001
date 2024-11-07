package com.braggbay555.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay555.domain.ProductCategory;
import com.braggbay555.dto.ProductCategoryDTO;
import com.braggbay555.dto.ProductCategorySearchDTO;
import com.braggbay555.dto.ProductCategoryPageDTO;
import com.braggbay555.dto.ProductCategoryConvertCriteriaDTO;
import com.braggbay555.service.GenericService;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ProductCategoryService extends GenericService<ProductCategory, Integer> {

	List<ProductCategory> findAll();

	ResultDTO addProductCategory(ProductCategoryDTO productCategoryDTO, RequestDTO requestDTO);

	ResultDTO updateProductCategory(ProductCategoryDTO productCategoryDTO, RequestDTO requestDTO);

    Page<ProductCategory> getAllProductCategorys(Pageable pageable);

    Page<ProductCategory> getAllProductCategorys(Specification<ProductCategory> spec, Pageable pageable);

	ResponseEntity<ProductCategoryPageDTO> getProductCategorys(ProductCategorySearchDTO productCategorySearchDTO);
	
	List<ProductCategoryDTO> convertProductCategorysToProductCategoryDTOs(List<ProductCategory> productCategorys, ProductCategoryConvertCriteriaDTO convertCriteria);

	ProductCategoryDTO getProductCategoryDTOById(Integer productCategoryId);







}





