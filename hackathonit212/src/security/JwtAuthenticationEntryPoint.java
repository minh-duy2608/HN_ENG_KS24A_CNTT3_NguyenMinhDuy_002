import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import jakarta.servlet.http.*;import java.io.IOException;
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
 public void commence(HttpServletRequest req,HttpServletResponse res,AuthenticationException ex)throws IOException{
  res.setStatus(401);res.setContentType("application/json");
  res.getWriter().write("{\"error\":\"INVALID_TOKEN\",\"message\":\""+ex.getMessage()+"\"}");
 }
}