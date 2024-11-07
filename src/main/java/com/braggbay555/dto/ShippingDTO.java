package com.braggbay555.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ShippingDTO {

	private Integer shippingId;

	private String trackingNumber;

	private Date shippingDate;

	private Date deliveryDate;






}
