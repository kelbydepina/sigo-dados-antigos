package cv.pn.processmanagement.business.sigoPessoa.services;


import cv.pn.processmanagement.business.sigoPessoa.dto.SigoPessoaDto;
import cv.pn.processmanagement.business.sigoPessoa.repository.SigoPessoaRepository;
import cv.pn.processmanagement.business.sigoPessoa.model.SigoPessoa;
import cv.pn.processmanagement.utilities.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;

@Service
public class SigoPessoaService implements ISigoPessoaService {

    private final SigoPessoaRepository sigoPessoaRepository;

    public SigoPessoaService(SigoPessoaRepository sigoPessoaRepository) {
        this.sigoPessoaRepository = sigoPessoaRepository;
    }

    @Override
    public APIResponse createSigoPessoa(SigoPessoaDto dto) {

        try {

            SigoPessoa sigoPessoa = new SigoPessoa();
            BeanUtils.copyProperties(dto, sigoPessoa);
            sigoPessoa.setUserCreate("ADMIN");
            sigoPessoaRepository.save(sigoPessoa);

            if (dto.getFotoBase64() != null) {
                sigoPessoa.setFoto(Base64.getDecoder().decode(dto.getFotoBase64()));
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("SigoPessoa criada com sucesso")
                    .setDetails(Collections.singletonList(sigoPessoa))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar SigoPessoa")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}
