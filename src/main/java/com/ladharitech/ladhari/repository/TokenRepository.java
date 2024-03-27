package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository  extends JpaRepository<Token,UUID> {
    @Query("""
select token from Token token join token.user u
where u.id = :userId and (token.expired = false or token.revoked = false)
""")
    List<Token> findAllValidTokensByUsers(UUID userId);

    Optional<Token> findByToken(String token);
}
