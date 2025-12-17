package cv.pn.processmanagement.business.sgoOcorrenciaMeios.services;


import cv.pn.processmanagement.business.sgoOcorrenciaMeios.dto.SgoOcorrenciaMeiosDto;
import cv.pn.processmanagement.business.sgoOcorrenciaMeios.model.SgoOcorrenciaMeios;
import cv.pn.processmanagement.business.sgoOcorrenciaMeios.repository.SgoOcorrenciaMeiosRepository;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SgoOcorrenciaMeiosService implements ISgoOcorrenciaMeiosService{

    private final SgoOcorrenciaMeiosRepository sgoOcorrenciaMeiosRepository;

    public SgoOcorrenciaMeiosService(SgoOcorrenciaMeiosRepository sgoOcorrenciaMeiosRepository) {
        this.sgoOcorrenciaMeiosRepository = sgoOcorrenciaMeiosRepository;
    }

    @Override
    public APIResponse createEstado(SgoOcorrenciaMeiosDto dto) {

        try {

            SgoOcorrenciaMeios sgoOcorrenciaMeios = new SgoOcorrenciaMeios();
            BeanUtils.copyProperties(dto, sgoOcorrenciaMeios);
            sgoOcorrenciaMeios.setUserCreate("ADMIN");

            sgoOcorrenciaMeiosRepository.save(sgoOcorrenciaMeios);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Ocorrencia Meios criada com sucesso")
                    .setDetails(Collections.singletonList(sgoOcorrenciaMeios))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar Ocorrencia Meios")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}
