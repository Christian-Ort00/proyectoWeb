package modaverse.web.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoID")
    private Long productoID;

    private String nombre;
    private String descripcion;
    private Double precio;

    @Column(name = "ruta_imagen") 
    private String rutaImagen;

    @ManyToOne
    @JoinColumn(name = "categoriaID")
    private Categoria categoria;
}
