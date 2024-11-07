package com.braggbay555.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbay555.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbay555.domain.ProductCategory;
import com.braggbay555.dto.ProductCategoryDTO;
import com.braggbay555.dto.ProductCategorySearchDTO;
import com.braggbay555.dto.ProductCategoryPageDTO;
import com.braggbay555.service.ProductCategoryService;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/productCategory")
@RestController
public class ProductCategoryController {

	private final static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);

	@Autowired
	ProductCategoryService productCategoryService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ProductCategory> getAll() {

		List<ProductCategory> productCategorys = productCategoryService.findAll();
		
		return productCategorys;	
	}

	@GetMapping(value = "/{productCategoryId}")
	@ResponseBody
	public ProductCategoryDTO getProductCategory(@PathVariable Integer productCategoryId) {
		
		return (productCategoryService.getProductCategoryDTOById(productCategoryId));
	}

 	@RequestMapping(value = "/addProductCategory", method = RequestMethod.POST)
	public ResponseEntity<?> addProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = productCategoryService.addProductCategory(productCategoryDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/productCategorys")
	public ResponseEntity<ProductCategoryPageDTO> getProductCategorys(ProductCategorySearchDTO productCategorySearchDTO) {
 
		return productCategoryService.getProductCategorys(productCategorySearchDTO);
	}	

	@RequestMapping(value = "/updateProductCategory", method = RequestMethod.POST)
	public ResponseEntity<?> updateProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = productCategoryService.updateProductCategory(productCategoryDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
