package com.braggbay555.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShippingPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<ShippingDTO> shippings;
}





