# Account Management System

## Overview

The **Account Management System** is a Java-based server-client application that manages user accounts and provides features like registration, login, money transfers, and transaction history. It uses a multi-threaded server to handle multiple client connections simultaneously, making it a robust solution for simulating a banking or account management system.

## Features

- **User Registration**: Collects user details (name, PPS, email, address, and balance) and securely stores them.
- **Login System**: Authenticates users based on their name and password.
- **Money Management**:
  - Add money to user accounts.
  - Transfer funds between accounts.
- **Transaction History**: Tracks and displays user transactions.
- **Multi-Threading**: Allows multiple clients to interact with the server simultaneously.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/yourusername/Account-Management-System.git
   ```

2. **Open the Project**:
   Import the project into your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse).

3. **Compile and Run**:
   Ensure you have Java installed (version 8 or higher). Compile and run the `Provider.java` file to start the server:

   ```bash
   javac Provider.java
   java Provider
   ```

## Usage

1. **Start the Server**:

   - Run the `Provider` class to initialize the server.

2. **Client Interaction**:

   - Connect a client to the server using a Java socket connection.
   - Follow the prompts to register, log in, or perform transactions.

3. **Supported Actions**:

   - **Register an Account**: Provide user details to create a new account.
   - **Login**: Authenticate and access your account.
   - **View Balance**: Check your current account balance.
   - **Add Money**: Increase your account balance.
   - **Transfer Money**: Send funds to another user.
   - **View Transactions**: See a history of your transactions.

## Requirements

- Java 8 or higher
- An IDE or terminal for running Java programs

## Example

**Register an Account**:

1. Enter your name, PPS, email, password, address, and initial balance.
2. The account is stored securely and a transaction history file is created.

**Login and Transfer Money**:

1. Enter your credentials to access your account.
2. Transfer money by providing the recipient's details (name, email, and PPS).
3. View the updated balance and transaction history.

