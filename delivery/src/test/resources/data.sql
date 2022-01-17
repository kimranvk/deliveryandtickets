insert into delivery (delivery_id, customer_type, delivery_status, expected_delivery_time, current_distance_from_destination_in_meters, time_to_reach_destination,mean_time_to_prepare_mins)
values(1, 'VIP', 'Order received', CURRENT_TIMESTAMP()+2, 10, CURRENT_TIMESTAMP()-9,10);

insert into delivery (delivery_id, customer_type, delivery_status, expected_delivery_time, current_distance_from_destination_in_meters, time_to_reach_destination,mean_time_to_prepare_mins)
values(2, 'New', ' Order Preparing', CURRENT_TIMESTAMP()+2, 10, CURRENT_TIMESTAMP()-9,20);
