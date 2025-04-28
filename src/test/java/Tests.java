import com.bankapp.DAO.AccountDAO;
import com.bankapp.DAO.UserDAO;
import com.bankapp.model.Account;
import com.bankapp.model.User;
import com.bankapp.service.AuthService;
import com.bankapp.service.BankService;
import com.bankapp.service.LoginService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Tests {
    public static void main(String[] args) {
            //SignUp ✅
//      User user = AuthService.register(new Scanner(System.in));
//      AuthService.register(new Scanner(System.in));

            // Login ✅
//        User user = LoginService.login("harsh","harsh19");
//        BankService service = new BankService(user);

            // AccountCreation ✅
//        service.createNewAcc(123423, Account.accountType.Savings,user);

            // Deposit ✅
//        service.deposit(100000, "542740846000");

            // withdraw ✅
//        service.withdraw(10000, "542740846000");
            // Transfer ✅
//        service.transfer(10000, "542740846000", "716506319593");

            // Multiple accounts ✅
//        service.createNewAcc(100123400, Account.accountType.Savings,user);

            // Show Transactions ✅
//        User user = UserDAO.getUserByUsername("harsh");
//        assert user != null;
//        List<Account> accounts = AccountDAO.getAccount(user);
//        BankService service = new BankService(user);
//        service.showAllAccounts(user);

           // CheckBalance ✅
//        User user  = UserDAO.getUserByUsername("harsh");
//        BankService service = new BankService(user);
//        assert user != null;
//        System.out.println(service.ShowBalance("608370773067"));;

            // ShowTransactions ✅
//            Account account = AccountDAO.getAccountByAccNum("542740846000");
//            assert account != null;
//            System.out.println(account.getTransactionList());

            // Close Account ✅
        Account account = AccountDAO.getAccountByAccNum("333497323592");
        AccountDAO.remove("333497323592");



    }
}
