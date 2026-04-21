Project Overview:
- This project implements a distributed key-value store using consistent hashing. Nodes (servers) are dynamically join
- a logical ring and are assigned a responsibility for a range of key-values.
---


## Architecture Summary:
   - **BootServer  (bootServer.java)**
   This is the main server that initializes the system
   Handle join requests from new nodes
   Maintains initial ring structure
 
  - **NameServer (nameServer.java)**
  Represents an instance of a node in the system
  It is assigned a range of keys that will store
  Stores key-value pairs locally
  Maintains knowledge of successor and predecessor

**Configuration Files**
- bconfigfile_example.txt
- nconfigfile_example.txt
Configuration files for BootServer/NameServer

## How it works:
- The system uses a hash space (0-1023)
- Each Node is assigned an ID that represents the maximum of its range
- [predecessorId+1 , ID]
- When nodes join:
      - BootServer helps to determine the position of the new node in hash space
      - Successor and Predecessor are updated
      - Key/Value pairs are transferred to the new node from its new successor
## Features
- Distributed key storage
- Dynamic node join
- Consistent hash ring
- LookUp
- Insert
- Delete
- Entry
- Exit

## Tech Stack:
- Java +17
- Socket
- TreeMap

## Setup Steps:
- It is necessary to have 1 instance of bnserver.java
- 1 bnconfig_example.txt to initiate bnserver.java
- There can be multiple instances of nserver.java
- Each instance of nserver.java needs its own configfile
## How to run locally:
- Start Boot Server
    // java bnserver.java bnconfig_example.txt
- Start Name Server(s)
   // java nserver.java nconfig_example.txt
## Test Instructions:
  LOOKUP <key>
  INSERT <key> <value>
  DELETE <key>
  ENTRY  
  EXIT
## Folder Structure:
    ConsistentHashing/
      |--bnserver.java
      |--nserver.java
  
## Links to detailed docs:
 - Folder docs contained the follow documents:
