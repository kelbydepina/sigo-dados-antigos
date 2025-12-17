package cv.pn.processmanagement.business.ocorrencia.services;

import com.google.gson.Gson;
import cv.pn.processmanagement.business.ocorrencia.dto.SgoOcorrenciaDto;
import cv.pn.processmanagement.business.ocorrencia.model.SgoOcorrencia;
import cv.pn.processmanagement.business.ocorrencia.repository.SgoOcorrenciaRepository;
import cv.pn.processmanagement.utilities.APIResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;


@Service
public class SgoOcorrenciaService implements ISgoOcorrenciaService{

    private final SgoOcorrenciaRepository sgoOcorrenciaRepository;

    private final JdbcTemplate jdbcTemplate;

    public SgoOcorrenciaService(SgoOcorrenciaRepository sgoOcorrenciaRepository, JdbcTemplate jdbcTemplate) {
        this.sgoOcorrenciaRepository = sgoOcorrenciaRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public APIResponse createOcorrencia(SgoOcorrenciaDto dto) {
        try {


            System.out.println("DTO RECEBIDO:");
           // System.out.println(new Gson().toJson(dto));

            SgoOcorrencia sgoOcorrencia = new SgoOcorrencia();

            sgoOcorrencia.setId(null);
            BeanUtils.copyProperties(dto, sgoOcorrencia);

            //System.out.println(new Gson().toJson(sgoOcorrencia));
            sgoOcorrencia.setUserCreate("ADMIN");


              sgoOcorrenciaRepository.save(sgoOcorrencia);



            System.out.println("ENTITY APÓS SAVE:");
            //System.out.println(new Gson().toJson(salvo));


            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Ocorrencia  criada com sucesso")
                    .setDetails(Collections.singletonList(sgoOcorrencia))
                    .builder();


        } catch (Exception e) {

            e.printStackTrace();
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar Ocorrencia ")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}
