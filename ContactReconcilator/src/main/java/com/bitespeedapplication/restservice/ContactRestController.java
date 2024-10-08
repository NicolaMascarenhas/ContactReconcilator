package com.bitespeedapplication.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitespeedapplication.restservice.ContactService;


@RestController
public class ContactRestController {
	@PostMapping(value="/identify", consumes = "application/json", produces = "application/json")
	public ContactDTO insertContact(@RequestBody ContactRequest newContact) {
		return contactService.insertContact(newContact);
	}
	
	@Autowired	
	ContactService contactService;
}
