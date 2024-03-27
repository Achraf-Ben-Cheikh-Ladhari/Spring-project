package com.ladharitech.ladhari.entity.token;


import com.ladharitech.ladhari.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="confirmation_token")
public class ConfirmationEmailToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id= UUID.randomUUID();

    private String confirmationToken;
    @Column(nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdAt;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;
    @Column(nullable = false)
    private boolean isValid;
    public ConfirmationEmailToken() {

    }
    public ConfirmationEmailToken(User user){
        this.user=user;
        confirmationToken = UUID.randomUUID().toString();
        this.isValid=true;
    }

    public boolean getValid(){
        return this.isValid;
    }

}
