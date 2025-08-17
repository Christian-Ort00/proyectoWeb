package modaverse.web.dao;

import modaverse.web.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta, Long> {
}
