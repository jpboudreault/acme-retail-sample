package com.acme.serializer.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String editionCode;
    private String pricingDuration;

    private List<Item> items;
}
