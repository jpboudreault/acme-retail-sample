package com.acme.serializer.appdirect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	private String editionCode;
	private String pricingDuration;

	private List<Item> items;
}
