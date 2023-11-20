CREATE DATABASE opportunities_management;

CREATE TABLE users
(
  id VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  token VARCHAR(100),
  token_expired_at BIGINT,
  PRIMARY KEY (id),
  UNIQUE (username),
  UNIQUE (token)
);

CREATE TABLE opportunities
(
  id VARCHAR(100) NOT NULL,
  user_id VARCHAR(100) NOT NULL,
  company VARCHAR(100) NOT NULL,
  position VARCHAR(100) NOT NULL,
  description TEXT NOT NULL,
  link VARCHAR(255) NOT NULL,
  company_url VARCHAR(255) NOT NULL,
  status VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),

  FOREIGN KEY fk_users_opportunities (user_id)
  REFERENCES users (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);