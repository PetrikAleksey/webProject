package spring.DAO.Service;

import spring.DAO.Model.Worker;

import java.util.List;

public interface WorkerService {

    Worker addWorker(Worker worker);
    Worker editWorker(Worker worker);
    List<Worker> getAll();
    void deleteSelected(List<Worker> listWorker);
    List<Worker> searchWorker(String str);
}
