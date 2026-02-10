package cv.pn.processmanagement.business.antecedentes;


import cv.pn.processmanagement.business.projection.OcorrenciaResumoProjection;
import cv.pn.processmanagement.business.projection.dto.OcorrenciaResumoDto;
import cv.pn.processmanagement.business.sigoOcorrenciaPessoa.repository.SgoOcorrenciaPessoaRepository;
import cv.pn.processmanagement.sgoPecaPessoa.repository.SgoPecaPessoaRepository;
import org.springframework.stereotype.Service;


import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AntecedenteService implements IAntecedenteService{

    private final SgoOcorrenciaPessoaRepository sgoOcorrenciaPessoaRepository;
    private final SgoPecaPessoaRepository sgoPecaPessoaRepository;

    public AntecedenteService(SgoOcorrenciaPessoaRepository sgoOcorrenciaPessoaRepository, SgoPecaPessoaRepository sgoPecaPessoaRepository) {
        this.sgoOcorrenciaPessoaRepository = sgoOcorrenciaPessoaRepository;
        this.sgoPecaPessoaRepository = sgoPecaPessoaRepository;
    }


    @Override
    public Map<String, Object> consultarAntecedentes(Long oracleId) {

        //  sempre lista (mesmo que tenha só um id)
        //List<SigoPessoa> pessoas = List.of(oracleId);

        /*//  Extrair todos os oracleIds encontrados
        List<Long> oracleIds = pessoas.stream()
                .map(SigoPessoa::getOracleId)
                .distinct()
                .toList();*/

       // List<Long> oracleIds = List.of(oracleId);

        // ✅ PASSO 2: agora sim buscar os detalhes
        List<OcorrenciaResumoProjection> ocorrencias =
                sgoOcorrenciaPessoaRepository.buscarAntecedentesPorPessoa(oracleId);

        Map<String, Object> resp = new HashMap<>();

        if (ocorrencias.isEmpty()) {
            resp.put("possuiOcorrencia", false);
            resp.put("mensagem",
                    "Esta pessoa não possui antecedentes policiais.");
            return resp;
        }

        // ✅ PASSO 1: valida se existe antecedente REAL
      /*  boolean possuiAntecedente =
                sgoOcorrenciaPessoaRepository.existsAntecedenteReal(oracleId);*/

       /* if (!possuiAntecedente) {
            resp.put("possuiOcorrencia", false);
            resp.put("mensagem", "Esta pessoa não possui antecedentes policiais.");
            return resp;
        }*/



       /* List<OcorrenciaResumoProjection> ocorrencias =
                sgoOcorrenciaPessoaRepository.buscarAntecedentesPorPessoa(oracleId);*/

       // Map<String, Object> resp = new HashMap<>();

      /*  if (ocorrencias.isEmpty()) {
            resp.put("possuiOcorrencia", false);
            resp.put("mensagem", "Esta pessoa não possui antecedentes policiais.");
            return resp;
        }*/

        //tem ocorrencia
        resp.put("possuiOcorrencia", true);
        resp.put("quantidade", ocorrencias.size());

        List<OcorrenciaResumoDto> lista = ocorrencias.stream().map(p -> {

           //Optional<SgoPecaPessoa> pessoatipo = sgoPecaPessoaRepository.findByIdPessoa(p.getId());
            OcorrenciaResumoDto dto = new OcorrenciaResumoDto();
            dto.setNumOcorrencia(p.getNumOcorrencia());
            dto.setNatureza(p.getNatureza());
            dto.setAutoPrincipal(p.getAutoPrincipal());
            dto.setCondicao(p.getCondicao());
            dto.setUnidade(p.getUnidade());
            dto.setFase(p.getFase());

            dto.setDataRegisto(
                    p.getDataRegisto() != null
                            ? p.getDataRegisto().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
                            : null
            );

            return dto;
        }).toList();

        resp.put("ocorrencias", lista);
        return resp;
    }

}
