package modaverse.web.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    @Column(name = "id_usuario")
    private Long idUsuario; 

    private LocalDateTime fecha;
    private Double total;
    private Integer estado;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<Venta> ventas;
}
