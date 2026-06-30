import io.jsonwebtoken.*;
public class JwtService{
 private static final String SECRET="rikkei_secret_key_super_secure_do_not_share";
 public Claims parse(String token){
   return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
 }
}