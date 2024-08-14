package com.bitespeedapplication.restservice;



/*
"contact":{
"primaryContactId": 1,
"emails": ["lorraine@hillvalley.edu","mcfly@hillvalley.edu"]
"phoneNumbers": ["123456"]
"secondaryContactIds": [23]
}
*/

public class ContactDTO {
int primaryContactId;
	
	Object[] emails;
	
	Object[] phoneNumbers;
	
	int[] secondaryContactIds;

	public int getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(int primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

	public Object[] getEmails() {
		return emails;
	}

	public void setEmails(Object[] objects) {
		this.emails = objects;
	}

	public Object[] getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Object[] objects) {
		this.phoneNumbers = objects;
	}

	public int[] getSecondaryContactIds() {
		return secondaryContactIds;
	}

	public void setSecondaryContactIds(int[] secondaryContactIds) {
		this.secondaryContactIds = secondaryContactIds;
	}
}
