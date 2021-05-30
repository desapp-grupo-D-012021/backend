package ar.edu.unq.desapp.grupoD.backenddesapptp.security;


import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";
    /*
    static void addAuthentication(HttpServletResponse res, String username){
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(SignatureAlgorithm.HS512,"secret").compact();

        res.addHeader("Authorization", "Bearer" + token);

    }
    static Authentication getAuthentication(HttpServletRequest req){
        String token = req.getHeader("Authorization");

        if(token != null){

            String user = Jwts.parser()
                    .setSigningKey("secret").parseClaimsJws(token.replace("Bearer","")).getBody().getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList()):
                    null;
        }
        return null;
    }
    */

    public String generateToken(UserDetails user){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, user.getUsername());
    }

    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))// token valido por 1 hora
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY). compact();
    }

    public Boolean validateToken(String token, UserDetails user){
        final  String username = user.getUsername();
        return (username.equals(user.getUsername()) && !isTokenExpire(token));
    }

    private boolean isTokenExpire(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
}
