package spring.DAO.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.DAO.Model.Worker;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {

    @Query("select w from Worker w where w.fio like %:strSearch% or w.phone like %:strSearch% or w.position like %:strSearch% or w.bank like %:strSearch%")
    //@Query("select w from Worker w where w.fio like %:fio%")
    List<Worker> findAllByFioLike(@Param("strSearch") String strSearch);

    Worker getWorkerById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Worker w where w.id in (:list)")
    void deleteAllById(@Param("list") List<Long> list);
}
