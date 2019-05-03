package spring.DAO.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.DAO.Model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
}
