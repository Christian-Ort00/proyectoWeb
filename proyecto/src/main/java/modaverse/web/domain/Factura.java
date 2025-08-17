/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modaverse.web.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

    @Column(name = "id_usuario") // <- importante si tu columna es snake_case
    private Long idUsuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private double total;

    private int estado;

    public Factura() {
        // Opcional: inicializar por defecto
        this.fecha = Calendar.getInstance().getTime();
        this.estado = 1;
        this.total = 0.0;
    }

    public Factura(Long idUsuario) {
        this.idUsuario = idUsuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado = 1;
        this.total = 0.0;
    }
}
