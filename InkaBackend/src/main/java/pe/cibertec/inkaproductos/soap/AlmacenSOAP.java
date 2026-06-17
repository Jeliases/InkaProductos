package pe.cibertec.inkaproductos.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlmacenSOAP", namespace = "http://cibertec.pe/inkaproductos/soap")
public class AlmacenSOAP {
    private int almacenId;
    private String nombre;
    private String ciudad;
    private String direccion;
}