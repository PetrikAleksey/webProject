package spring.DAO.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import spring.DAO.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("select b from Bank b where b.name = :name")
    Bank findByName(@Param("name") String name);

    @Query("select b from Bank b where b.name like %:name%")
    List<Bank> findAllByNameLike(@Param("name") String name);

    //аннотация @Async указывает, что метод должен выполняться асинхронно.
    //@Modifying говорит о том, что указанный метод должен быть интерпретирован как модифицирующий запрос
    //@Transactional добавляет поддержку транзакций для указанного метода

    @Transactional
    @Modifying
    @Query("delete from Bank b where b.id in (:list)")
    void deleteAllById(@Param("list") List<Long> list);

}
