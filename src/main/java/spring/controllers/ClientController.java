package spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.DAO.Model.Client;
import spring.DAO.Model.Converters.ClientConverter;
import spring.DAO.Model.Converters.WorkerConverter;
import spring.DAO.Model.Enum.Position;
import spring.DAO.Model.Worker;
import spring.DAO.Service.ClientService;
import spring.DAO.Service.WorkerService;

import java.util.*;

@Controller
public class ClientController {

    @Autowired
    public ClientService clientService;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String client(Locale locale, Model model) {
        return "client";
    }

    @RequestMapping(value="/addClient", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String addClient(@RequestBody Client client) {
        clientService.addClient(client);
        Gson gson = new GsonBuilder().registerTypeAdapter(Client.class, new ClientConverter()).create();
        return gson.toJson(client);
    }

    @RequestMapping(value="/deleteClientAll", method=RequestMethod.POST,produces = {"application/json; charset=utf-8;"})//,consumes = MediaType.APPLICATION_JSON_VALUE)//produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public void deleteClientAll(@RequestBody List<String> list) {
        List<Long> longList = new ArrayList<>();
        for(String s : list) longList.add(Long.valueOf(s));
        //listBank.forEach(System.out::println);
        clientService.deleteSelected(longList);
        //return new Gson().toJson(listClient);
    }

    @RequestMapping(value="/editClient", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String editClient(@RequestBody Client client) {
        clientService.editClient(client);
        Gson gson = new GsonBuilder().registerTypeAdapter(Client.class, new ClientConverter()).create();
        return gson.toJson(client);
    }

    @RequestMapping(value="/loadClient", method=RequestMethod.GET, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String loadClient() {
        List<Client> list = clientService.getAll();
        Gson gson = new GsonBuilder().registerTypeAdapter(Client.class, new ClientConverter()).create();
        return gson.toJson(list);
    }

    @RequestMapping(value="/getClient", method=RequestMethod.POST, produces = {"application/json; charset=utf-8;"})
    @ResponseBody
    public String getClient(@RequestBody Long id) {
        Client client = clientService.getById(id);
        Gson gson = new GsonBuilder().registerTypeAdapter(Client.class, new ClientConverter()).create();
        return gson.toJson(client);

    }

}
