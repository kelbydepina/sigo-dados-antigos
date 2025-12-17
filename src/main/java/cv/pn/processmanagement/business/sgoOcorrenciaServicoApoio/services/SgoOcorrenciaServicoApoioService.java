package cv.pn.processmanagement.business.sgoOcorrenciaServicoApoio.services;


import cv.pn.processmanagement.business.sgoOcorrenciaServicoApoio.dto.SgoOcorrenciaServicoApoioDto;
import cv.pn.processmanagement.business.sgoOcorrenciaServicoApoio.model.SgoOcorrenciaServicoApoio;
import cv.pn.processmanagement.business.sgoOcorrenciaServicoApoio.repository.SgoOcorrenciaServicoApoioRepository;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SgoOcorrenciaServicoApoioService implements ISgoOcorrenciaServicoApoioService{

    private final SgoOcorrenciaServicoApoioRepository sgoOcorrenciaServicoApoioRepository;

    public SgoOcorrenciaServicoApoioService(SgoOcorrenciaServicoApoioRepository sgoOcorrenciaServicoApoioRepository) {
        this.sgoOcorrenciaServicoApoioRepository = sgoOcorrenciaServicoApoioRepository;
    }


    @Override
    public APIResponse createServicoApoio(SgoOcorrenciaServicoApoioDto dto) {

        try {

            SgoOcorrenciaServicoApoio sgoOcorrenciaServicoApoio = new SgoOcorrenciaServicoApoio();
            BeanUtils.copyProperties(dto, sgoOcorrenciaServicoApoio);
            sgoOcorrenciaServicoApoio.setUserCreate("ADMIN");
            sgoOcorrenciaServicoApoioRepository.save(sgoOcorrenciaServicoApoio);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Ocorrencia Serviço Apoio criada com sucesso")
                    .setDetails(Collections.singletonList(sgoOcorrenciaServicoApoio))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar Ocorrencia Serviço Apoio")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}
