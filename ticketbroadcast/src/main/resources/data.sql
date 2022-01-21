
CREATE TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(64) NOT NULL,
  role varchar(45) NOT NULL,
  enabled tinyint(4) DEFAULT NULL,
  PRIMARY KEY (user_id)
);


INSERT INTO users (username,password,role,enabled)
VALUES ('admin',
'$2a$12$hkMHq7PPBAWQmz.kPq08w.yuURbk/lHuYKNz/pI8Gs.iV8aA8jFdW',
'ROLE_ADMIN', 1);