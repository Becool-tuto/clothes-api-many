package org.demo.clothes.api.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@PreMatching
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorization = containerRequestContext.getHeaderString(AUTHORIZATION_HEADER_KEY);
        if(authorization == null || authorization.isEmpty()){
           containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
           return;
        }
    }
}
