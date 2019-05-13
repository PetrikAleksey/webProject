package spring.DAO.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.DAO.Model.BankAccount;
import spring.DAO.Repository.BankAccountRepository;
import spring.DAO.Repository.BankRepository;
import spring.DAO.Service.BankAccountService;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount addBankAccount(BankAccount bankAccount) {
        BankAccount bankAccountNew = bankAccountRepository.saveAndFlush(bankAccount);
        return bankAccountNew;
    }

    @Override
    public BankAccount editBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.saveAndFlush(bankAccount);
    }

    @Override
    public List<BankAccount> getAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public void deleteSelected(List<Long> list) {
        bankAccountRepository.deleteAllById(list);
    }

    @Override
    public BankAccount getById(Long id) {
        return bankAccountRepository.getBankAccountById(id);
    }
}
