CREATE TABLE user_account (
    username varchar(255) PRIMARY KEY,
    password varchar(255) NOT NULL,
    user_owner bigint NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    username varchar(255) PRIMARY KEY,
    role varchar(255) NOT NULL,
    FOREIGN KEY (username) REFERENCES user_account (username) ON DELETE CASCADE
);

--Создаем администратора при запуске с именем 'maks-admin'

INSERT INTO user_account (username, password, user_owner)
VALUES ('maks-admin', '$2a$10$32yphP6fbjQdhaAtWFiOneA6HEOPtyiQ5KBEeNArDOaQmJ1qx25cm', 3);

INSERT INTO user_roles (username, role) VALUES ('maks-admin', 'ADMIN');

INSERT INTO user_owner (username, created_at, time_of_visit) values ('maks-admin', now(), now());