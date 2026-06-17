package pe.cibertec.inkaproductos.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "GetAlmacenResponse", namespace = "http://cibertec.pe/inkaproductos/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAlmacenResponse {
    @XmlElement(namespace = "http://cibertec.pe/inkaproductos/soap")
    private AlmacenSOAP almacen;
}