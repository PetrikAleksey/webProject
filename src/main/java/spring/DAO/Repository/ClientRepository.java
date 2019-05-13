package spring.DAO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.DAO.Model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

//    @Query("select c from Client c where c.id = :id")
//    Client getClient(@Param("id") Long id);
//
//    Optional<Client> findById(Long id);

    Client getClientById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Client c where c.id in (:list)")
    void deleteAllById(@Param("list") List<Long> list);

}
