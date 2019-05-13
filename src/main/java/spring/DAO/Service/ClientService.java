package spring.DAO.Service;

import spring.DAO.Model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);
    Client editClient(Client client);
    List<Client> getAll();
    void deleteSelected(List<Long> list);
    Client getById(Long id);

}
