package cv.pn.processmanagement.business.sgoHistoricoVitimaVbg.service;


import cv.pn.processmanagement.business.sgoHistoricoVitimaVbg.dto.SgoHistoricoVitimaVbgDTO;
import cv.pn.processmanagement.business.sgoHistoricoVitimaVbg.model.SgoHistoricoVitimaVbg;
import cv.pn.processmanagement.business.sgoHistoricoVitimaVbg.repository.SgoHistoricoVitimaVbgRepository;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SgoHistoricoVitimaVbgService implements ISgoHistoricoVitimaVbgService{

    private final SgoHistoricoVitimaVbgRepository sgoHistoricoVitimaVbgRepository;

    public SgoHistoricoVitimaVbgService(SgoHistoricoVitimaVbgRepository sgoHistoricoVitimaVbgRepository) {
        this.sgoHistoricoVitimaVbgRepository = sgoHistoricoVitimaVbgRepository;
    }

    @Override
    public APIResponse createVitimaVbg(SgoHistoricoVitimaVbgDTO dto) {

        try {

            System.out.println("DTO RECEBIDO:");

            SgoHistoricoVitimaVbg sgoHistoricoVitimaVbg = new SgoHistoricoVitimaVbg();
            BeanUtils.copyProperties(dto, sgoHistoricoVitimaVbg);

            sgoHistoricoVitimaVbgRepository.save(sgoHistoricoVitimaVbg);

            System.out.println("ENTITY APÓS SAVE:");

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("SigoVitimaVbg criada com sucesso")
                    .setDetails(Collections.singletonList(sgoHistoricoVitimaVbg))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar SigoVitimaVbg")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}
