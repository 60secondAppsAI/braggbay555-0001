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

import com.braggbay555.domain.Dispute;
import com.braggbay555.dto.DisputeDTO;
import com.braggbay555.dto.DisputeSearchDTO;
import com.braggbay555.dto.DisputePageDTO;
import com.braggbay555.service.DisputeService;
import com.braggbay555.dto.common.RequestDTO;
import com.braggbay555.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/dispute")
@RestController
public class DisputeController {

	private final static Logger logger = LoggerFactory.getLogger(DisputeController.class);

	@Autowired
	DisputeService disputeService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Dispute> getAll() {

		List<Dispute> disputes = disputeService.findAll();
		
		return disputes;	
	}

	@GetMapping(value = "/{disputeId}")
	@ResponseBody
	public DisputeDTO getDispute(@PathVariable Integer disputeId) {
		
		return (disputeService.getDisputeDTOById(disputeId));
	}

 	@RequestMapping(value = "/addDispute", method = RequestMethod.POST)
	public ResponseEntity<?> addDispute(@RequestBody DisputeDTO disputeDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = disputeService.addDispute(disputeDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/disputes")
	public ResponseEntity<DisputePageDTO> getDisputes(DisputeSearchDTO disputeSearchDTO) {
 
		return disputeService.getDisputes(disputeSearchDTO);
	}	

	@RequestMapping(value = "/updateDispute", method = RequestMethod.POST)
	public ResponseEntity<?> updateDispute(@RequestBody DisputeDTO disputeDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = disputeService.updateDispute(disputeDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
