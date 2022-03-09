# IT-Support
Project
ITO IT Support Tracker

Build an ITO IT Support Tracker which will be helpful for manage request to IT team and track request by creator.
Note: In is Assessment you must generate REST APIs for the following operations.

 There will be following Module in the Application
o	IT Team Module
	View Ticket
	View Ticket List
	Set Assignee
	Change the Status
	Comment on Ticket

o	User Module
	Create Ticket
	View Ticket list and Details.
	Comment on Ticket.







Database Tables:
Ticket (ticket_id, category_id, sub-category_id, assignee_Id, reported_Id, subject, description, status_id, priority_id, create_datetime, last_modified_datetime)
Comment (comment_id, 	ticket_id, user_id, message)

Create Script to generate data for following table
	Category (category_id, category_desc)
Sub_Category (sub_category_id, category_id , sub_category_desc)
	Admin_team (admin_id, name, emailId)
	User (user_id, name, emailId)

Category and SubCategory list:
o	Hardware
	Allocate Laptop
	Allocate Hardware
	Hardware replacement
o	Software
	Software Installation
	Antivirus.
	Email Password update
	Laptop Slowness issue.
	Software Issue

o	Access Management 
	Software access
	Wifi Access
	Database Access
	VPN Access


o	Status:
	Open
	Assigned
	In Progress
	Completed

o	Priority:
	Low	
	Medium
	High
	Critical

Create Few Dummy Admin Team member and User 
And insert all into database
		



IT Team Module
•	View Ticket List:
o	IT Team Member can view list of all the created ticket.
o	It will return JSON of all the ticket create and return URL for each ticket
	It will return
•	Ticket_id
•	Category
•	Subcategory
•	Subject
•	Priority
•	Status
•	Assignee
•	Link for ticket
o	If no ticket available show the Message “No data Found”

•	View Ticket 
o	IT Team Member can fetch the ticket using Ticket ID
	Return Complete Information For that ticket and list all the communication in that ticket.
•	Communication message List contain 
o	Sender name
o	Message 
	If Ticket ID is invalid, then send the Message “Invalid Ticket Id”


•	Set Assignee
o	IT Team Member can Set the assignee.
	Send the ticket id and admin
	User id in request param for set the assignee
	Set the current date time value for last_modified_datetime field.
	If ticket id or admin user id invalid show the message “Incorrect Ticket Id” or “Incorrect User Id”


•	Change The Status
o	IT Team Member can change the Status
	Send the Ticket Id, Status Id and User Id as request parameter.
	If any of the id is invalid return appropriate Error message.
	After Updating the status Update the last_modified_datetime with current date time
	If status updated Successfully then send response “Status Change from <Old Status Desc> to <New Status Desc>


•	Comment on Ticket.
o	IT Team Member can comment on the Ticket.
	Send Ticket Id and user Id in request param and 
And message in request body.
	 Add the message into Comment table 
	If any of the id is invalid return appropriate Error message.
	If Comment save successfully then return message “Successfully Added Comment on <Ticket-Id>”

User Module
•	Create Ticket
o	User can create ticket by passing following details:
	category_id
	 sub-category_id
	 Subject
	 Description
	 priority_id
o	Pass User Id in request Param
o	While creating ticket set user id as reporter Id
o	Set default status Id for status (Open)
o	Created datetime will be current datetime 
o	If Ticket created Successfully then Return Message “<Ticket-ID> Created Successfully and return the URL also for that ticket
o	If failed to create, then send Error message.

•	View the Ticket
o	User can get the list of created ticket by him
o	Send the creator user id in request param and generate the JSON response.
	In response return
•	Ticket_id
•	Category
•	Subcategory
•	Subject
•	Priority
•	Status
•	Assignee
•	Link for ticket

o	View By Id: User can View Complete Ticket detail by ticket Id 
o	If User Id or Ticket Id is invalid return the appropriate error message


•	Comment on Ticket.
o	User can comment on the Ticket.
	Send Ticket Id and user Id in request param and 
And message in request body.
	 Add the message into Comment table 
	If any of the id is invalid return appropriate Error message.
	If Comment save successfully then return message “Successfully Added Comment on <Ticket-Id>”
