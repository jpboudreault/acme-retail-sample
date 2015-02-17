package com.acme.serializer.appdirect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String email;
	private String firstName;
	private String lastName;
	private String openId;
	private String language;
}