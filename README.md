# ContactReconcilator


Contact Reconcilator is used to link different orders made with different contact information to the same person, when receiving new orders. 

It is a Spring Boot Application which exposes a REST API (/identify) which accepts a Json payload with number and email of person placing order. 
If an incoming request has either of phoneNumber or email common to an existing contact but contains new
information, the service will create a “secondary” Contact row, else it will simply create a new Contact row treating it as a new customer. 
A primary customer can also turn into secondary, if later an order links the phone number to some other email id. 

Response is a json which returns primaryContactId, all associated emails (primary & secondary contacts), associted phone numbers as well as the linked secondary contact ids. 


##Hosting 
The application is hosted on Render using a separte PostgresSQL instance & contanizered Spring Boot instance (https://contactreconcilatorapp-latest.onrender.com).

## Eaxample using Endpoint
URL: https://contactreconcilatorapp-latest.onrender.com/identify
Payload: 
{
    "phoneNumber":9413482358,
    "email":"nm@gmail.com"
}
Response:
{
  "primaryContactId":2,
  "emails":[
    "nm@gmail.com",
    "codinggirl@gmail.com"
  ],
  "phoneNumbers":[
    "9413482358",
    "6538947328"
  ],
  "secondaryContactIds":[3]
}
