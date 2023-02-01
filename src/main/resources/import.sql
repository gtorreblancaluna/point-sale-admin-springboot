INSERT INTO roles (name) VALUES ("Super Admin");
INSERT INTO roles (name) VALUES ("Admin");
INSERT INTO roles (name) VALUES ("User");
INSERT INTO roles (name) VALUES ("Cashier");
INSERT INTO roles (name) VALUES ("Manager");

INSERT INTO users (name,last_name,email,phone_number,password,username,enabled,created_at,updated_at) VALUES ("admin","admin admin", "admin@admin.com","1234567890","$2a$10$JTjUOWgUg5GPv7aN7VfbE.vFAhneNKaRzcGjMP6emKC3cHuAMbY3.","adminusernmae",1,"2023-01-01 00:00:00","2023-01-01 00:00:00");

INSERT INTO users_roles (user_id,role_id) VALUES (1,1);