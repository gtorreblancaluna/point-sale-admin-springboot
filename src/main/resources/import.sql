INSERT INTO roles (name) VALUES ("Admin");
INSERT INTO roles (name) VALUES ("User");
INSERT INTO roles (name) VALUES ("Cashier");
INSERT INTO roles (name) VALUES ("Manager");

INSERT INTO users (name,last_name,email,phone_number,password,username,enabled) VALUES ("admin","admin admin", "admin@admin.com","1234567890","$2a$10$JTjUOWgUg5GPv7aN7VfbE.vFAhneNKaRzcGjMP6emKC3cHuAMbY3.","adminusernmae",1);

INSERT INTO users_roles (user_id,role_id) VALUES (1,1);