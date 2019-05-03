package spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.DAO.Model.Converters.WorkerConverter;
import spring.DAO.Model.Worker;
import spring.DAO.Service.WorkerService;

import java.util.List;

@RestController
@RequestMapping(value = "restWorker")
public class RestWorkerController {

    @Autowired
    public WorkerService workerService;

    @PostMapping(value="/searchWorker",produces = {"application/json; charset=utf-8;"})
    public String searchBank(@RequestBody Worker worker) {
        System.out.println(worker);
        List<Worker> list = workerService.searchWorker(worker.getFio());
        list.forEach(System.out::println);
        Gson gson = new GsonBuilder().registerTypeAdapter(Worker.class, new WorkerConverter()).create();
        return gson.toJson(list);
    }
}
