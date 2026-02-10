package cv.pn.processmanagement.business.antecedentes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/sigoOcorrenciaPessoa")
public class AntecedenteController {

    private final IAntecedenteService iAntecedenteService;

    public AntecedenteController(IAntecedenteService iAntecedenteService) {
        this.iAntecedenteService = iAntecedenteService;
    }


    @GetMapping("/pessoa/{oracleId}/antecedentes")
    public ResponseEntity<?> consultar(@PathVariable Long oracleId) {
        return ResponseEntity.ok(iAntecedenteService.consultarAntecedentes(oracleId));
    }
}
