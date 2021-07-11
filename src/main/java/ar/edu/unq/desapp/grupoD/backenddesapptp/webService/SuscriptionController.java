package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Suscription;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.SuscriptionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@Api(tags = "Suscriptions")
public class SuscriptionController {

    @Autowired
    private SuscriptionService suscriptionService;

    @PostMapping("/suscription")
    public ResponseEntity<Suscription> addSuscription(@RequestBody Suscription suscription) {
        suscriptionService.addSuscription(suscription);
        return ResponseEntity.ok().body(suscription);
    }

}
