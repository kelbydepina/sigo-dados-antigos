package cv.pn.processmanagement.business.ocorrencia.repository;

import cv.pn.processmanagement.business.ocorrencia.model.SgoOcorrencia;
import cv.pn.processmanagement.business.projection.OcorrenciaMotivoProjection;
import cv.pn.processmanagement.business.sigoPessoa.model.SigoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SgoOcorrenciaRepository extends JpaRepository<SgoOcorrencia, String> /*, JpaSpecificationExecutor<SgoOcorrencia>*/ {

    @Query(value = """
        SELECT
            o.num_ocorrencia AS numOcorrencia,
            o.motivo         AS motivo
        FROM sgo_ocorrencia o
        WHERE o.num_ocorrencia = :num
    """, nativeQuery = true)
    OcorrenciaMotivoProjection buscarMotivo(
            @Param("num") String num
    );
}
