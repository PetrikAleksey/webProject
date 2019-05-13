package spring.DAO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.DAO.Model.BankAccount;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    BankAccount getBankAccountById(Long id);

    @Transactional
    @Modifying
    @Query("delete from BankAccount ba where ba.id in (:list)")
    void deleteAllById(@Param("list") List<Long> list);

}
