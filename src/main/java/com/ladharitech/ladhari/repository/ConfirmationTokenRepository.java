package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.token.ConfirmationEmailToken;
import com.ladharitech.ladhari.entity.token.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationEmailToken,UUID> {
    ConfirmationEmailToken findByConfirmationToken(String confirmationToken);

    @Query("""
select confirmation_token from ConfirmationEmailToken confirmation_token join confirmation_token.user u
where u.id = :userId and confirmation_token.isValid = true
""")
    List<ConfirmationEmailToken> findAllConfirmationTokensByUser(UUID userId);
}
