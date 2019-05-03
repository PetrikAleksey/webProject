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

//    @Modifying
//    @Query("delete from Bank b where b.id in :list")
//    void deleteByIdIn(@Param("list") List<Long> listId);
//    //Bank deleteByIdIn(List<Long> listId);


//    @Query("select b from Bank b where b.name like CONCAT('%',:name,'%')")
//    List<Bank> searchBank(@Param("name") String name);

    @Query("select b from Bank b where b.name like %:name%")
    List<Bank> findAllByNameLike(@Param("name") String name);
    //List<Bank> findAllByNameLike(String name);

    //List<Bank> findAllByNameLike

}
