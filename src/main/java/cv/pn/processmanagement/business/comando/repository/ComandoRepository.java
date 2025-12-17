package cv.pn.processmanagement.business.comando.repository;

import cv.pn.processmanagement.business.comando.model.Comando;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandoRepository extends JpaRepository<Comando, String> {
}
