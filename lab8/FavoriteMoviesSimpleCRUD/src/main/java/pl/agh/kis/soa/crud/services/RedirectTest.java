package pl.agh.kis.soa.crud.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/osoby")
public class RedirectTest {
    @GET
    public Response getAllUsers() throws URISyntaxException {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        return Response.status(Response.Status.MOVED_PERMANENTLY)
                .cacheControl(cacheControl)
                .location(new URI(("http://localhost:8080/favoritemovies/api/users"))).build();
    }
}
