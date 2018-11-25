INSERT INTO user (id, username, password, name, email) VALUES (1, 'admin', '$2a$10$03OM6tJ7Ea5xNZ4jpibgM.7VtIZPQZ4UfUWxVkqf3G1KFPVZOY2gG', '宇智波攀', 'i@waylau.com');
INSERT INTO user (id, username, password, name, email)  VALUES (2, 'pan', '$2a$10$03OM6tJ7Ea5xNZ4jpibgM.7VtIZPQZ4UfUWxVkqf3G1KFPVZOY2gG', 'Way Lau', 'waylau@waylau.com');

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
