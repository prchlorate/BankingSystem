* Banking System*

A simple Java-based console banking system with two storage modes:
1. **JSON Mode** (in-memory data → `accounts.json`)
2. **MySQL Mode** (persistent data in a MySQL database)
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
