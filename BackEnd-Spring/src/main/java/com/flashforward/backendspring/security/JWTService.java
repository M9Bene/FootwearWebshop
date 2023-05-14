package com.flashforward.backendspring.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JWTService {

    //  a 256bit Encryption key in HEX for: sign in key
    private static final String SECRET_KEY = "66546A576E5A7234753778214125442A462D4A614E645267556B587032733576";

    // convert the SECRET_KEY String into bytes then (convert into and ) return it as Key
    private Key getSignInKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
