package spring.DAO.Service;

import spring.DAO.Model.BankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount addBankAccount(BankAccount bankAccount);
    BankAccount editBankAccount(BankAccount bankAccount);
    List<BankAccount> getAll();
    void deleteSelected(List<Long> list);

}
