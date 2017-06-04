package challenge.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/** Used by the <code>SecurityConfiguration</code> to commence authentication.
* <p>
* For BASIC authentication we call into the
* {@link #commence(HttpServletRequest, HttpServletResponse, AuthenticationException)}
* method below. 
*/

@Component
public class TwitterBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

	 /**
     * @param request
     * @param response
     * @param authEx
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				authEx.getMessage());
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("thousand-eyes");
        super.afterPropertiesSet();
    }
}
