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

import com.braggbay555.domain.Coupon;
import com.braggbay555.dto.CouponDTO;
import com.braggbay555.dto.CouponSearchDTO;
import com.braggbay555.dto.CouponPageDTO;
import com.braggbay555.service.CouponService;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/coupon")
@RestController
public class CouponController {

	private final static Logger logger = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	CouponService couponService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Coupon> getAll() {

		List<Coupon> coupons = couponService.findAll();
		
		return coupons;	
	}

	@GetMapping(value = "/{couponId}")
	@ResponseBody
	public CouponDTO getCoupon(@PathVariable Integer couponId) {
		
		return (couponService.getCouponDTOById(couponId));
	}

 	@RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
	public ResponseEntity<?> addCoupon(@RequestBody CouponDTO couponDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = couponService.addCoupon(couponDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/coupons")
	public ResponseEntity<CouponPageDTO> getCoupons(CouponSearchDTO couponSearchDTO) {
 
		return couponService.getCoupons(couponSearchDTO);
	}	

	@RequestMapping(value = "/updateCoupon", method = RequestMethod.POST)
	public ResponseEntity<?> updateCoupon(@RequestBody CouponDTO couponDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = couponService.updateCoupon(couponDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
