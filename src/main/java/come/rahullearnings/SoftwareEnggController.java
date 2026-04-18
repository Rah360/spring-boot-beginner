package come.rahullearnings;

import come.rahullearnings.dto.SoftwareEngg;
import come.rahullearnings.service.softwareEnggService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEnggController {
    public final softwareEnggService service;

    @Autowired
    public SoftwareEnggController(softwareEnggService service) {
        this.service = service;
    }

    @GetMapping
    public List<SoftwareEngg> getEngineers(){
        return service.getAllEngineers();
    }

    @PostMapping
    public void hireEngg(@Valid @RequestBody SoftwareEngg engg){
        service.saveEngineer(engg);
    }

    @GetMapping("/{id}")
    public SoftwareEngg postMapping(@PathVariable int id){
        return service.getById(id);
    }

}
