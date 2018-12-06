INSERT INTO user (id, username, password) VALUES (1, 'FantasticPan', '$2a$10$03OM6tJ7Ea5xNZ4jpibgM.7VtIZPQZ4UfUWxVkqf3G1KFPVZOY2gG');

INSERT INTO authority (id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, role) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
insert into blog (catalog, content, create_time, html_content, summary, tags, title, user_id) values (1, 1, 1, 1, 1, 1, 1, 1);
# INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
