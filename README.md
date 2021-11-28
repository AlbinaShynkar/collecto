# Collecto

Board game Collecto developed in Java.

How to install and run

When you get all the files you see the src folder where the project implementation is. You will need to work only with few files to make program run. 

To make the project run locally you have to first run CollectoServer.java which will kindly ask you to fill the port address you want to host.
You have to enter 8989, for instance. if this number does not work(which is when some other computer connected to network already uses this number), choose any other random number and it should work.
Now server is waiting for clients to be connected. 

Then you need to run CollectoClient.java, where application will ask client for the 
name: enter any name, or change to another if this one is in use.
host address: write localhost here 
and port number: write 4-digit port number of server you just started in step above 

Then server will say HELLO to client and client will be able to send LOGIN message providing the name. Then server will login client and message will be printed saying to user he can type LIST or join QUEUE for new game. When client types QUEUE  server creates game for this client if there is another client on server.

Now you may connect one more client and start the game locally.

##Folders structure and classes relation
In our project we got in the end utils folder with all model classes, client and server interfaces, several exception classes and threads classes used for communication between these two. We also have a modelTest folder with model test classes implemented. Secondly, we got an application folder with two classes regarding game and board business logic. All the business logic of the game, all the rules are implemented there. Then we got the view folder, with the TUI file there. 

Huge part of our project is the client and server part. Server and Client folders contain all classes regarding server and client. In each of these two folders there are sub folders Controller, Services and Services.Controller. The last one contains classes BaseController, which is a simple abstract class, and Resolver, which is responsible for resolving messages coming from client to server and vice versa, also it can tell whether the message is supported and add other controllers. Then we have in each of the Server and Client folders the Services folder. This contains the class NetworkClient. On the server side it sends the messages from the corresponding client and on the client side this class creates new clients on the server, using socket and sends the message from client to output stream. Also, on the client side we have NetworkReceiver class, which basically catches messages for clients in the input stream, calls resolver to resolve the message. On the server side we have a UsersInQueue class. Regarding the Controller folder on each side, these contain classes to handle the requests from the client, e.h HELLO or MOVE. Finally, we have two classes CollectoClient and CollectoServer, running which you can start our server and create and connect new clients to it to run our game on one machine locally. 





