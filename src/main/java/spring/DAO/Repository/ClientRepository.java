package spring.DAO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.DAO.Model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

//    @Query("select c from Client c where c.id = :id")
//    Client getClient(@Param("id") Long id);
//
//    Optional<Client> findById(Long id);

    Client getClientById(Long id);

}
