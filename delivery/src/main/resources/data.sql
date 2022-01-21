insert into delivery (delivery_id, customer_type, delivery_status, expected_delivery_time, current_distance_from_destination_in_meters, time_to_reach_destination,mean_time_to_prepare_mins)
values(1, 'VIP', 'Order received', CURRENT_TIMESTAMP()+2, 10, CURRENT_TIMESTAMP()-9,10);

insert into delivery (delivery_id, customer_type, delivery_status, expected_delivery_time, current_distance_from_destination_in_meters, time_to_reach_destination,mean_time_to_prepare_mins)
values(2, 'New', ' Order Preparing', CURRENT_TIMESTAMP()+2, 10, CURRENT_TIMESTAMP()-9,20);


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