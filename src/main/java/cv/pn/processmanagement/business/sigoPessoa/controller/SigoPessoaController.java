package cv.pn.processmanagement.business.sigoPessoa.controller;


import cv.pn.processmanagement.business.sigoPessoa.dto.SigoPessoaDto;
import cv.pn.processmanagement.business.sigoPessoa.services.ISigoPessoaService;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sgopessoas")
public class SigoPessoaController {

    private final ISigoPessoaService iSigoPessoaService;

    public SigoPessoaController(ISigoPessoaService iSigoPessoaService) {
        this.iSigoPessoaService = iSigoPessoaService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody SigoPessoaDto dto) {
        return ResponseEntity.ok(iSigoPessoaService.createSigoPessoa(dto));
    }
}
