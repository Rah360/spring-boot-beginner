package come.rahullearnings.service;

import come.rahullearnings.dto.SoftwareEngg;
import come.rahullearnings.repository.SoftwareEnggRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class softwareEnggService {
    private final SoftwareEnggRepository repository;

    @Autowired
    public softwareEnggService(SoftwareEnggRepository repository){
        this.repository=repository;
    }

    public List<SoftwareEngg> getAllEngineers(){
        return repository.findAll();
    }

    public void saveEngineer(SoftwareEngg engg){
        repository.save(engg);
    }

    public SoftwareEngg getById(Integer id){
       return repository.findById(id).orElseThrow(()->new IllegalStateException("id not found"));
    }
}
