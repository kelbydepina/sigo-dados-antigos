package cv.pn.processmanagement.business.agente.repository;

import cv.pn.processmanagement.business.agente.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, String> {
}
