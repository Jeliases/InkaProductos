package pe.cibertec.inkaproductos.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pe.cibertec.inkaproductos.models.Almacen;
import jakarta.persistence.EntityManager;

@Endpoint
public class AlmacenSoapEndpoint {

    private static final String NAMESPACE_URI = "http://cibertec.pe/inkaproductos/soap";

    @Autowired
    private EntityManager entityManager;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAlmacenRequest")
    @ResponsePayload
    public GetAlmacenResponse getAlmacen(@RequestPayload GetAlmacenRequest request) {
        GetAlmacenResponse response = new GetAlmacenResponse();
        AlmacenSOAP almacenSOAP = new AlmacenSOAP();

        // Buscar en BD por ID
        Almacen almacen = entityManager.find(Almacen.class, request.getId());

        if (almacen != null) {
            almacenSOAP.setAlmacenId(almacen.getAlmacenId());
            almacenSOAP.setNombre(almacen.getNombre());
            almacenSOAP.setCiudad(almacen.getCiudad());
            almacenSOAP.setDireccion(almacen.getDireccion());
        } else {
            almacenSOAP.setNombre("Almacén no encontrado");
        }

        response.setAlmacen(almacenSOAP);
        return response;
    }
}