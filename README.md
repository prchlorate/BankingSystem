* Banking System*

A simple Java-based console banking system with two storage modes:
1. **JSON Mode** (in-memory data → `accounts.json`)
2. **MySQL Mode** (persistent data in a MySQL database)

Use JSON mode for quick prototyping; switch to MySQL mode once you set up the database. Both modes share the same service and UI layers—only the data‐loading/persistence implementation differs.

---

## Features

- **Customer operations**  
  - Check balance  
  - Deposit funds  
  - Withdraw funds  

- **Admin operations**  
  - Open new account  
  - Close existing account  
  - Modify account name/PIN  

- **Storage modes**  
  - **JSON Mode**:  
    - Reads/writes a local `accounts.json` file  
    - Ideal for rapid testing without a database  
  - **MySQL Mode**:  
    - Reads/writes a real MySQL “accounts” table  
    - Suitable for production‐like persistence
   
    - ^^^ Unsure how it can be tested to see if it runs on other machines.

---

## Prerequisites

1. **Java 11+** (tested on Java 11, 17, and 19)  
2. **Gson 2.10.1** JAR  
   - Place `gson-2.10.1.jar` in `lib/`  
3. **MySQL 8.x** (only if using MySQL mode)  
   - Place MySQL Connector/J 8.x JAR (`mysql-connector-java-8.0.xx.jar`) in `lib/`  

Your project tree should look like:
