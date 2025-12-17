package cv.pn.processmanagement.business.intervinientes.repository;

import cv.pn.processmanagement.business.intervinientes.model.Intervenientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervenientesRepository extends JpaRepository<Intervenientes, String> {
}
