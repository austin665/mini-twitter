# Mini Twitter
 
A Spring Boot based backend for a mini messaging service, inspired by Twitter. It should have a RESTful API where all endpoints require HTTP Basic authentication and generate output in JSON format. Implement the the following basic functionality:
 
An endpoint to read the message list for the current user (as identified by their HTTP Basic authentication credentials). Include messages they have sent and messages sent by users they follow. Support a “search=” parameter that can be used to further filter messages based on keyword.
Endpoints to get the list of people the user is following as well as the followers of the user.
An endpoint to start following another user.
An endpoint to unfollow another user.
An endpoint that returns a list of all users, paired with their most "popular" follower. The more followers someone has, the more "popular" they are. 

The project provides an H2 in-memory SQL database as a storage backend. 
 
HTTP Basic authentication is done using Spring Security.
