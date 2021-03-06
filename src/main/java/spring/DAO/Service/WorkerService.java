package spring.DAO.Service;

import spring.DAO.Model.Worker;

import java.util.List;

public interface WorkerService {

    Worker addWorker(Worker worker);
    Worker editWorker(Worker worker);
    List<Worker> getAll();
    void deleteSelected(List<Long> list);
    List<Worker> searchWorker(String str);
    Worker getById(Long id);
}
