## Customer Management Application

This application allows you to add a contact, delete a contact, and update a contact. It is built using Spring boot.

# Endpoints

### GET /contacts
returns list of contacts 

### GET /contacts/{id}
return a contact by id if found

### POST /contacts
Sample json: 
{firstName: "john", lastName: "doe", email: "john.doe@gmail.com" }

### PUT /contacts/{id}
Update a contact

### DELETE /contacts/{id}
Delete a contact if found
