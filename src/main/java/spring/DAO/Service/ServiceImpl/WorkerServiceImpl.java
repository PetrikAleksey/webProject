package spring.DAO.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.DAO.Model.Worker;
import spring.DAO.Repository.WorkerRepository;
import spring.DAO.Service.WorkerService;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker addWorker(Worker worker) {
        Worker workernew = workerRepository.saveAndFlush(worker);
        return workernew;
    }

    @Override
    public Worker editWorker(Worker worker) {
        return workerRepository.saveAndFlush(worker);
    }

    @Override
    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    @Override
    public void deleteSelected(List<Worker> listWorker) {
        workerRepository.deleteAll(listWorker);
    }
}
