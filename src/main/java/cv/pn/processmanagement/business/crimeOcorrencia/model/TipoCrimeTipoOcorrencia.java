package cv.pn.processmanagement.business.crimeOcorrencia.model;


import cv.pn.processmanagement.commons.CommonsAttributes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TP_crime_TP_ocorrencia")
public class TipoCrimeTipoOcorrencia extends CommonsAttributes {

    @Column(name = "ID_TIPO_CRIME")
    private Long idTipoCrime;

    @Column(name = "ID_TP_OCORRENCIA")
    private Long idTpOcorrencia;

    public Long getIdTipoCrime() {
        return idTipoCrime;
    }

    public void setIdTipoCrime(Long idTipoCrime) {
        this.idTipoCrime = idTipoCrime;
    }

    public Long getIdTpOcorrencia() {
        return idTpOcorrencia;
    }

    public void setIdTpOcorrencia(Long idTpOcorrencia) {
        this.idTpOcorrencia = idTpOcorrencia;
    }
}
