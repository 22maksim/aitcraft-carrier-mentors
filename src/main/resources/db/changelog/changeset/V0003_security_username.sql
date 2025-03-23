CREATE TABLE user_account (
    username varchar(55) PRIMARY KEY,
    password varchar(255) NOT NULL,
    user_owner bigint NOT NULL UNIQUE
);