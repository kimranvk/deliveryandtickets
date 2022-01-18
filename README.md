# deliveryandtickets
Backend Exercise There is a firm which deals with the online delivery business, their primary focus of the year is to improve the customer experience. They are facing so many complaints so they have to increase the people in their Customer Support department. After going through this Product Manager produce an idea to create an “Automated Ticketing System” which will go scan the system and create the tickets so hired agent will be able to resolve the ticket even before customer reports. For now, consider the system is the food delivery system. It has the following properties Each delivery has the following parameters. 1. Delivery ID 2. Customer Type (VIP, Loyal, New) 3. Delivery Status (Order received, Order Preparing, Order Pickedup, Order Delivered) 4. Expected time of delivery 5. The current distance from the destination 6. Rider rating. 7. Restaurant Mean time to prepare food. 8. Time to reach the destination Above are the fields that are related to Delivery. You need to create a system that will monitor the deliveries and create the ticket, Calculate the priority of each ticket by taking into consideration the above factors. Details: The system will be picking data from the Delivery table with a regular interval time and refreshing the results DELIVERY_DETAILS Fields Data_type delivery_id Int customer_type varchar delivery_status varchar expected_delivery_time timestamp current_distance_from_destination_in_meters int time_to_reach_destination timestamp You need to design the solution keeping some factors in mind. • If the Customer is VIP (which will be rated as per his history of orders) he must have high priority over other customers. • If the Expected time of delivery is passed and the order is still not delivered, its priority automatically becomes higher others. • Calculate the estimated time of delivery by considering (Mean time to prepare food + Time to reach the destination) and raise a ticket if your estimation is greater than the expected time • Try to think of other factors that need to be considered for the prioritization of the tickets. Implementation required • You need to create the login API which will send username and Password and authorize it using any well-known standard to ensure the security of the application. • You need to create the API to return the tickets sorted as per the priority Things to keep in mind in designing the solution • For the backend API you must use Spring boot with Java 8+. • The solution should be designed in such a way that it should be high performant and 10x scalable. (Follow SOLID Principle) • Should take care of the Testing coverage of the code. Must follow the standard guidelines of testing (Ref: Testing Pyramid). • Should have appropriate Exception Handling and the correct level of logging (Recommended to have different log levels as per environment/profile) • You can use any storage (Redis, DynamoDB, MySQL, H2 etc.) • Your Solution should be runnable in multiple environments like Docker etc.

Following are the modules that solve above problem:

delivery
========

That will read from h2 database, apply the rules to set the priority of each delivery, tickets will be generated and send to its session in radis server.

*it is very scalable can expand to any number.

ticketbroadcast
==============
That will read from common radis store and aggregate and sort all tickets.

each module has its own read me file that helps to start the processes.
