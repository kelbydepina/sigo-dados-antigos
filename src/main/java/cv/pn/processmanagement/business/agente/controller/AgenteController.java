package cv.pn.processmanagement.business.agente.controller;


import cv.pn.processmanagement.business.agente.service.IAgenteService;
import cv.pn.processmanagement.business.agente.dto.AgenteDto;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agente")
public class AgenteController {

    private final IAgenteService iAgenteService;

    public AgenteController(IAgenteService iAgenteService) {
        this.iAgenteService = iAgenteService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody AgenteDto dto) {
        return ResponseEntity.ok(iAgenteService.createAgente(dto));
    }

    @PostMapping("/params")
    public ResponseEntity<APIResponse> createAgenteParams(@RequestParam String numero,
                                                           @RequestParam String nome){
        return ResponseEntity.ok(iAgenteService.createAgenteParams(numero, nome));
    }
}
