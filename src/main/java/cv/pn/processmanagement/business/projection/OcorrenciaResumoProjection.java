package cv.pn.processmanagement.business.projection;

import java.util.Date;

public interface OcorrenciaResumoProjection {

    String getNumOcorrencia();
    String getNatureza();          // sgo_ocorrencia.NUM_OCORRENCIA
    String getAutoPrincipal();
    String getCondicao();          // sgo_ocorrencia_pessoa.CONDICAO
    String getUnidade();           // esquadra
    String getFase();              // estado / fase do processo
    Date getDataRegisto();         // data da ocorrência
}
