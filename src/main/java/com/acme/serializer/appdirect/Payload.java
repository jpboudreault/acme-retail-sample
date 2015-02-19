package com.acme.serializer.appdirect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Fields within the payload depend on the event type
 * 'create' have a company and order field
 * 'change' have a change field
 * 'cancel' have a cancel field
 * 'status' have a status field
 * 'user assignment' have a user field
 * 'user unassignment' have a user field
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {
	private Company company;
	private Account account;
	private Order order;
	private Notice notice;
	private User user;
}
