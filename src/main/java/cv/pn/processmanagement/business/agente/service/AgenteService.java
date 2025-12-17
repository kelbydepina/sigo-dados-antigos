package cv.pn.processmanagement.business.agente.service;


import cv.pn.processmanagement.business.agente.dto.AgenteDto;
import cv.pn.processmanagement.business.agente.model.Agente;
import cv.pn.processmanagement.business.agente.repository.AgenteRepository;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AgenteService implements IAgenteService {

    private final AgenteRepository agenteRepository;

    public AgenteService(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    @Override
    public APIResponse createAgente(AgenteDto dto) {

        try {

            Agente agente = new Agente();
            BeanUtils.copyProperties(dto, agente);
            agente.setUserCreate("ADMIN");

           /* byte [] content = dto.getFoto().getBytes();
            agente.setFoto(content);*/

            agenteRepository.save(agente);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Agente criada com sucesso")
                    .setDetails(Collections.singletonList(agente))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar Agente")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

    @Override
    public APIResponse createAgenteParams(String numero, String nome) {

        try {

            Agente agente1 = new Agente();
            agente1.setNumero(numero);
            agente1.setNome(nome);
            agente1.setUserCreate("ADMIN");

            agenteRepository.save(agente1);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Agente numero, nome foi criada com sucesso")
                    .setDetails(Collections.singletonList(agente1))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar Agente")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }



    }
}
