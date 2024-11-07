package com.braggbay555.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="shippings")
@Getter @Setter @NoArgsConstructor
public class Shipping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="shipping_id")
	private Integer shippingId;
    
  	@Column(name="tracking_number")
	private String trackingNumber;
    
  	@Column(name="shipping_date")
	private Date shippingDate;
    
  	@Column(name="delivery_date")
	private Date deliveryDate;
    
	




}
