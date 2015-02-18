package com.acme.serializer.appdirect;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
// FIXME validate this part @XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	private String editionCode;
	private String pricingDuration;

	@XmlElement(name="item")
	private List<Item> items;
}
