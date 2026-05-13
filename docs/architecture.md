frontend
backend/API
database
auth
background worker / socket server if used


### Layers
 # 1) Node interface + BaseNode abstract class
    - These 2 java classes allow us to define behavior and state
    of every node in the system. This layer defines the components by which the ring functions around.
 # 2)
## Node interface:
    We create a node interface to handle initialization of nodes
    (BootServer & Nserver).

# 3) Command routing 
   - Users command:
      - I implemented a class Messages with Enum Type messages. The idea is to create object Messages and sent it through the router, then the router will decide what method to call. 
   - Node communication:
      - 

