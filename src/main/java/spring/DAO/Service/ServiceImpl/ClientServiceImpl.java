package spring.DAO.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.DAO.Model.Client;
import spring.DAO.Repository.ClientRepository;
import spring.DAO.Service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(Client client) {
        Client clientnew = clientRepository.saveAndFlush(client);
        return clientnew;
    }

    @Override
    public Client editClient(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteSelected(List<Long> list) {
        clientRepository.deleteAllById(list);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.getClientById(id);
    }
}
