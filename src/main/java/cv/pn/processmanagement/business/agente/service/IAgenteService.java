package cv.pn.processmanagement.business.agente.service;

import cv.pn.processmanagement.business.agente.dto.AgenteDto;
import cv.pn.processmanagement.utilities.APIResponse;

public interface IAgenteService {
    APIResponse createAgente(AgenteDto dto);

    APIResponse createAgenteParams(String numero, String nome);
}
