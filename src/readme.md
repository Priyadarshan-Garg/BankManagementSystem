# Bank Management System (Java + Hibernate + CLI)

A simple yet powerful Bank Management System built using:
- Java (Core + Collections)
- Hibernate ORM
- SLF4J (for Logging)
- MySQL Database
- Command Line Interface (CLI)

---

## 📋 Prerequisites

- Java JDK 22 or higher
- MySQL 8.0 or higher
- Maven (for dependency management)
- Minimum 4GB RAM recommended
- 500MB free disk space

---

## ✨ Features

- **User Account Management:**
    - Create new Bank Account
    - View all accounts
    - Close an account

- **Transactions:**
    - Deposit money
    - Withdraw money
    - Transfer money to another account
    - View all transactions
    - View first/last `n` transactions

- **Security:**
    - Password Hashing using BCrypt
    - Safe and optimized Hibernate transactions
    - Logging important activities and errors

---

## 🛠 Technologies Used

| Technology | Purpose |
|:-----------|:--------|
| Java       | Core Application |
| Hibernate | ORM (Object Relational Mapping) |
| MySQL      | Database Management |
| SLF4J + Logback | Logging Framework |
| BCrypt     | Password Hashing |

---


## 🚀 Detailed Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/bank-management-system.git
   cd bank-management-system
   ```

2. **Database Setup:**
   ```sql
   CREATE DATABASE bankdb;
   CREATE USER 'bankapp'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON bankdb.* TO 'bankapp'@'localhost';
   ```

3. **Application Configuration:**
   - `src/main/resources/application.properties` me database credentials update karen
   - Logging configuration check karen

4. **Build & Run:**
   ```bash
   mvn clean install
   java -jar target/bank-management-system.jar
   ```

---

## 💡 Usage Examples

1. **Create New Account:**
   ```
   > create-account
   Enter Name: John Doe
   Enter Initial Deposit: 5000
   Account created successfully! Account number: 1001
   ```

2. **Deposit Money:**
   ```
   > deposit
   Enter Account Number: 1001
   Enter Amount: 2000
   Deposit successful! New balance: 7000
   ```

3. **Transfer Money:**
   ```
   > transfer
   From Account: 1001
   To Account: 1002
   Amount: 1000
   Transfer successful! New balance: 6000
   ```

---

## ❗ Troubleshooting

Common issues and solutions:

1. **Database Connection Failed**
   - Check if MySQL service is running
   - Verify database credentials in configuration
   - Ensure database 'bankdb' exists

2. **Hibernate Errors**
   - Check if tables are properly created
   - Verify entity mappings
   - Ensure hibernate.cfg.xml is properly configured

3. **Performance Issues**
   - Check available memory
   - Monitor database connections
   - Review transaction isolation levels

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/AmazingFeature`
3. Commit changes: `git commit -m 'Add AmazingFeature'`
4. Push to branch: `git push origin feature/AmazingFeature`
5. Open a Pull Request

Please ensure you follow our coding standards and include appropriate tests.

---

## 🗺️ Future Roadmap

- [ ] Add support for multiple currencies
- [ ] Implement email notifications
- [ ] Create REST API interface
- [ ] Add unit test coverage
- [ ] Implement admin dashboard
- [ ] Mobile app integration
- [ ] Enhanced security features
- [ ] Performance optimization

---

## License 📝

This project is licensed under the MIT License - see the [docs/LICENSE.md](docs/LICENSE.md) file for details.

---

## 📞 Support

If you encounter any issues or need assistance, please:
- Open an issue on GitHub
- Contact the maintainers at: `gargpriyadarshan18@gmail.com`