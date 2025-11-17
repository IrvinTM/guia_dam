package edu.sv.ues.dam235.apirestdemo.utilities;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    // Conjunto en memoria de tokens inválidos
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    public void blacklistToken(String token) {
        if (token != null && !token.isBlank()) {
            blacklist.add(token);
        }
    }

    public boolean isBlacklisted(String token) {
        return token != null && blacklist.contains(token);
    }
}