package com.acme.serializer.appdirect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Marketplace {
	private String partner;
	private String baseUrl;
}
