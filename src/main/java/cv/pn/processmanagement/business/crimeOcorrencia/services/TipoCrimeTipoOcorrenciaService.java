package cv.pn.processmanagement.business.crimeOcorrencia.services;


import cv.pn.processmanagement.business.crimeOcorrencia.dto.TipoCrimeTipoOcorrenciaDto;
import cv.pn.processmanagement.business.crimeOcorrencia.repository.TipoCrimeTipoOcorrenciaRepository;
import cv.pn.processmanagement.business.crimeOcorrencia.model.TipoCrimeTipoOcorrencia;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TipoCrimeTipoOcorrenciaService implements ITipoCrimeTipoOcorrenciaService {

    private final TipoCrimeTipoOcorrenciaRepository tipoCrimeTipoOcorrenciaRepository;

    public TipoCrimeTipoOcorrenciaService(TipoCrimeTipoOcorrenciaRepository tipoCrimeTipoOcorrenciaRepository) {
        this.tipoCrimeTipoOcorrenciaRepository = tipoCrimeTipoOcorrenciaRepository;
    }

    @Override
    public APIResponse createTipoCrime(TipoCrimeTipoOcorrenciaDto dto) {

        try {
            TipoCrimeTipoOcorrencia entity = new TipoCrimeTipoOcorrencia();
            BeanUtils.copyProperties(dto, entity);
            entity.setUserCreate("ADMIN");

            tipoCrimeTipoOcorrenciaRepository.save(entity);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("TipoCrimeTipoOcorrencia criada com sucesso")
                    .setDetails(Collections.singletonList(entity))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar TipoCrimeTipoOcorrencia")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}
