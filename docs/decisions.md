001 - Use java as main language
002 - ThreadPool technique to reduce load on CPU
003 - Applying Strategic pattern (Node Types), Command Patter(message handler) and Factory patter (creating messages)
004 - Implement a combination of Interface (Node) and Abstract  class (BaseNode). 
    - Reasoning: Both type of nodes have the similar state (variables) and  behavior (methods). Therefore we'll use
    abstract class (BaseNode) to reuse code. Both nodes also have require behavior. In order for future components to interact with any node no matter implementation, we have added an interface (Node).
005 - Protocol messages are manage by class Message that 
      contains enums MessageType
    - Reasoning: Enums holds instance objects of a class. Messages class implements Serialization. This means we can send instances of messages objects through streams (communication among servers) in a more structure way.
006 - Applying Command Pattern for our comunnication layer
    -Reasoning: It makes the code more readable and easy to fix.
    For example, we can trace the issue among layers thanks to different exceptions in different classes together with print statements. Scoping the errors to one class, reduces complexity.  
    
007 - Transfering Data 
    After a new node enters the ring, it request data to its new successor. We are going about how to transfer this data.
    1) Simplest way that matches structure. Sending a message (Creating new socket) everytime we send a pair key/value data. The structure is "Sending_Data key value", and this is done equal to the number of key/value pairs sent. 
    Disadventages - Creating a new socket everytime
    2) We could try to create another constructor for Message() where content is a Array of key/value pairs. We probably need to keep in mind the type of Array
    3) We could  reuse sockets to send every key/value pair. This solution deals with creating new socket every time





Each ADR should answer:

decision
why
alternatives considered
tradeoffs
consequences

003 - Creating an interface/abstract class for nodes. Why x?


##
1) Interface/abstract
2) class Message/ enum MessageType why?
3) Create Router/Dispatcher (Design Pattern Command Pattern) NetworkManager
4) Architecture.md diagrams

Note: Apply SOLID principles

