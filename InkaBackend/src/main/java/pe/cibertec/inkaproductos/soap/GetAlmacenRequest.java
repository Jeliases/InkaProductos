package pe.cibertec.inkaproductos.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "GetAlmacenRequest", namespace = "http://cibertec.pe/inkaproductos/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAlmacenRequest {
    @XmlElement(namespace = "http://cibertec.pe/inkaproductos/soap")
    private int id;
}