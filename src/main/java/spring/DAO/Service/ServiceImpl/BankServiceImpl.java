package spring.DAO.Service.ServiceImpl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import spring.DAO.Model.Bank;
import spring.DAO.Repository.BankRepository;
import spring.DAO.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {
	
	//@Autowired
    private BankRepository bankRepository;
	
	@Autowired
    public BankServiceImpl(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}
    
	@Override
    public Bank addBank(Bank bank) {
        Bank savedBank = bankRepository.saveAndFlush(bank);

        return savedBank;
    }

    @Override
    public void delete(Long Id) {
        bankRepository.deleteById(Id);
    }

    @Override
    public Bank getByName(String name) {
        return bankRepository.findByName(name);
    }

    @Override
    public Bank editBank(Bank bank) {
        return bankRepository.saveAndFlush(bank);
    }

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getBank(Long id) {
        return bankRepository.getOne(id);
    }

    @Override
    @Transactional
    public void deleteSelected(List<Long> list) {
        bankRepository.deleteAllById(list);
    }

    @Override
    public List<Bank> searchBank(String name) {
        return bankRepository.findAllByNameLike(name);
    }


}
