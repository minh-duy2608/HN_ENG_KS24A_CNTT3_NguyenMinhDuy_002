import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;import jakarta.servlet.http.*;import java.io.IOException;
public class JwtAuthenticationFilter extends OncePerRequestFilter{
 private final JwtService jwtService=new JwtService();
 @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization");
  if(h!=null&&h.startsWith("Bearer ")){
    try{jwtService.parse(h.substring(7));}
    catch(JwtException e){throw new BadCredentialsException("Invalid JWT",e);}
  }
  chain.doFilter(req,res);
 }
}