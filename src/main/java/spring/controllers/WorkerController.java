package spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.DAO.Model.Converters.WorkerConverter;
import spring.DAO.Model.Enum.Position;
import spring.DAO.Model.Worker;
import spring.DAO.Service.WorkerService;

import java.util.*;

@Controller
public class WorkerController {

    public static String WORKER_MODAL_ADD = "workerModalAdd";

    @Autowired
    public WorkerService workerService;

    @RequestMapping(value="/listPosition", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String loadPosition() {
        Map<Integer,String> mapPositions = new HashMap<Integer,String>();
        Position[] positions = Position.values();
        for(int i = 0; i < positions.length;i++)
            mapPositions.put(positions[i].ordinal(),positions[i].toString());
        return new Gson().toJson(mapPositions);
    }

    @RequestMapping(value="/addWorker", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String addWorker(@RequestBody Worker worker) {
        System.out.println("Зашли");
        workerService.addWorker(worker);
        Gson gson = new GsonBuilder().registerTypeAdapter(Worker.class, new WorkerConverter()).create();
        return gson.toJson(worker);
    }

    @RequestMapping(value="/deleteWorkerAll", method=RequestMethod.POST,produces = {"application/json; charset=utf-8;"})//,consumes = MediaType.APPLICATION_JSON_VALUE)//produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public void deleteWorkerAll(@RequestBody List<String> list) {
        List<Long> longList = new ArrayList<>();
        for(String s : list) longList.add(Long.valueOf(s));
        //longList.forEach(System.out::println);
        workerService.deleteSelected(longList);
        //return new Gson().toJson(list);
    }

    @RequestMapping(value="/editWorker", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String editWorker(@RequestBody Worker worker) {
        workerService.editWorker(worker);
        Gson gson = new GsonBuilder().registerTypeAdapter(Worker.class, new WorkerConverter()).create();
        return gson.toJson(worker);
    }

    @RequestMapping(value="/loadWorker", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String loadWorker() {
        List<Worker> list = workerService.getAll();
        Gson gson = new GsonBuilder().registerTypeAdapter(Worker.class, new WorkerConverter()).create();
        return gson.toJson(list);
    }

    @RequestMapping(value="/getWorker", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String getWorker(@RequestBody Long id) {
        Worker client = workerService.getById(id);
        Gson gson = new GsonBuilder().registerTypeAdapter(Worker.class, new WorkerConverter()).create();
        return gson.toJson(client);
    }


    @GetMapping(value="/workerModalAdd")
    public ModelAndView workerModalAdd() {
        ModelAndView m = new ModelAndView();
        m.setViewName(WORKER_MODAL_ADD);
        return m;
    }

}
