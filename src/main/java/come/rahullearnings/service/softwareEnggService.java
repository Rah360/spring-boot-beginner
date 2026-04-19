package come.rahullearnings.service;

import come.rahullearnings.dto.SoftwareEngg;
import come.rahullearnings.repository.SoftwareEnggRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class softwareEnggService {
    private final SoftwareEnggRepository repository;
    private final NotificationService ns;
    @Autowired
    public softwareEnggService(SoftwareEnggRepository repository, NotificationService ns){
        this.repository=repository;
        this.ns = ns;
    }

    public List<SoftwareEngg> getAllEngineers(){
        return repository.findAll();
    }

    public void saveEngineer(SoftwareEngg engg){
        repository.save(engg);
        ns.notify("New Engineer Hired: " + engg.getName());
    }

    public SoftwareEngg getById(Integer id){
       return repository.findById(id).orElseThrow(()->new IllegalStateException("id not found"));
    }
}
