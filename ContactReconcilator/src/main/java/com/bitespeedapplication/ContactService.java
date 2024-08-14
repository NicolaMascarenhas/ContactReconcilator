package com.bitespeedapplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

public class ContactService {

	public ContactDTO insertContact(ContactRequest contact) {
		ContactEntity contactToBeInserted=new ContactEntity();
		ContactDTO contactDTO=new ContactDTO();
		
		contactToBeInserted.setPhoneNumber(contact.getPhoneNumber());
		contactToBeInserted.setEmail(contact.getEmail());
		contactToBeInserted.setCreatedAt(LocalDateTime.now());
		contactToBeInserted.setUpdatedAt(LocalDateTime.now());
		
		List<ContactEntity> existingContactByPhone=contactRepository.findByPhoneNumber(contact.getPhoneNumber());
		List<ContactEntity> existingContactByEmail=contactRepository.findByEmail(contact.getEmail());
		
		if(existingContactByPhone.isEmpty()&&existingContactByEmail.isEmpty()) {
			System.out.println("For primary entry ");

			contactToBeInserted.setLinkPrecedence("primary");
			contactRepository.save(contactToBeInserted);
			
			contactDTO.setPrimaryContactId(contactToBeInserted.getId());
			contactDTO.setEmails(new String[] {contact.getEmail()});
			contactDTO.setPhoneNumbers(new String[] {Long.toString(contact.getPhoneNumber())});
			contactDTO.setSecondaryContactIds(new int[0]);
			
		} 
		else { 
			contactToBeInserted.setLinkPrecedence("secondary");
			System.out.println("For secondary entry ");
			System.out.println("Phone exists "+existingContactByPhone.toString());
			System.out.println("Email exists "+existingContactByEmail.toString());
			// Decided if both present, to assume phoneNo one as primary
			if(existingContactByPhone.isEmpty()) {
				contactToBeInserted.setLinkedId(existingContactByEmail.get(0).getId());
				
				List<String> allEmails = existingContactByEmail.stream()
					    .map(eachContact -> eachContact.getEmail())
					    .collect(Collectors.toList());
				allEmails.add(contact.getEmail());
				contactDTO.setEmails(allEmails.toArray());
				List<String> allPhoneNumbers = existingContactByEmail.stream()
					    .map(eachContact -> String.valueOf(eachContact.getPhoneNumber()))
					    .collect(Collectors.toList());
				allPhoneNumbers.add(String.valueOf(contact.getPhoneNumber()));
				contactDTO.setPhoneNumbers(allPhoneNumbers.toArray());	
			}
			else {
				contactToBeInserted.setLinkedId(existingContactByPhone.get(0).getId());
				
				List<String> allPhoneNumbers = existingContactByPhone.stream()
					    .map(eachContact -> String.valueOf(eachContact.getPhoneNumber()))
					    .collect(Collectors.toList());
				allPhoneNumbers.add(String.valueOf(contact.getPhoneNumber()));
				contactDTO.setPhoneNumbers(allPhoneNumbers.toArray());
				List<String> allEmails = existingContactByPhone.stream()
					    .map(eachContact -> eachContact.getEmail())
					    .collect(Collectors.toList());
				allEmails.add(contact.getEmail());
				contactDTO.setEmails(allEmails.toArray());
			}
			contactRepository.save(contactToBeInserted);
			contactDTO.setPrimaryContactId(contactToBeInserted.getLinkedId());
			contactDTO.setSecondaryContactIds(new int[] {contactToBeInserted.getId()});	
		}
		return contactDTO;
	}
	
	@Autowired
	ContactRepository contactRepository;

}
