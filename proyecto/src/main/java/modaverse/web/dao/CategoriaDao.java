package modaverse.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import modaverse.web.domain.Categoria;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {
}
