Programm task from seeker capital
==================

 This project is a programm test for Seeker Capital, It is a microservice application

This project includes a spring-cloud-gateway as entrance, a spring-cloud-eureka-server as service discovery center, a MongoDB to store data, a RabbitMQ as message service

A spring-boot application named publisher generate random int (mock price) every 3 seconds and send it to RabbitMQ.

Another spring-boot application named api receives the price messages from RabbitMQ and then save it to MongoDB, it also provides REST API to calculate the average value of the last X prices

The spring-boot application web provides a html page which built with vuejs and minCSS to user accessing the average value of the last X prices

All this nodes based on docker.

Run this application:
	
1. Clone this repository
2. run `docker-compose -f docker-compose.yml up`
3. open http://localhost:8090/price/web/index.html
