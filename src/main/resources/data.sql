--Category Table
INSERT INTO category (category_id,category_desc) 
values 
(1,"Hardware"),
(2,"Software"),
(3,"Access Management");

--Sub_Category
INSERT INTO sub_category (sub_category_id,sub_category_desc,category_id) 
VALUES
(1,"Allocate Laptop",(SELECT category_id FROM category WHERE category_id =1)),
(2,"Allocate Hardware",(SELECT category_id FROM category WHERE category_id =1)),
(3,"Hardware Repalce",(SELECT category_id FROM category WHERE category_id =1)),
(4,"Software Installation",(SELECT category_id FROM category WHERE category_id =2)),
(5,"Antivirus",(SELECT category_id FROM category WHERE category_id =2)),
(6,"Email password update",(SELECT category_id FROM category WHERE category_id =2)),
(7,"Laptop slowness issue",(SELECT category_id FROM category WHERE category_id =2)),
(8,"Software issue",(SELECT category_id FROM category WHERE category_id =2)),
(9,"Software Access",(SELECT category_id FROM category WHERE category_id =3)),
(10,"Wi-fi Access",(SELECT category_id FROM category WHERE category_id =3)),
(11,"Database Access",(SELECT category_id FROM category WHERE category_id =3)),
(12,"VPN Access",(SELECT category_id FROM category WHERE category_id =3));

--Admin_team
INSERT INTO admin_team (admin_id,name,email)
VALUES
(1,"admin1","admin1@gmail.com"),
(2,"admin2","admin2@gmail.com"),
(3,"admin3","admin3@gmail.com");

INSERT INTO users (user_id,name,email) 
VALUES
(1,"user1","user1@gmail.com"),
(2,"user2","user2@gmail.com"),
(3,"user3","user3@gmail.com"),
(4,"user4","user4@gmail.com"),
(5,"user5","user5@gmail.com");

INSERT INTO status (status_id,status_desc)
VALUES
(1,"Open"),
(2,"Assigned"),
(3,"In Progess"),
(4,"Completed");

INSERT INTO priority (priority_id,priority_desc)
VALUES 
(1,"Low"),
(2,"Medium"),
(3,"High"),
(4,"Critical");
