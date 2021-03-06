package spring.DAO.Service;

import spring.DAO.Model.Bank;

import java.util.ArrayList;
import java.util.List;

public interface BankService {

    Bank addBank(Bank bank);
    void delete(Long id);
    Bank getByName(String name);
    Bank editBank(Bank bank);
    List<Bank> getAll();
    Bank getById(Long id);
    void deleteSelected(List<Long> list);
    List<Bank> searchBank(String name);
}
