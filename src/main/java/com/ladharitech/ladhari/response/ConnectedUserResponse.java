package com.ladharitech.ladhari.response;

import com.ladharitech.ladhari.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectedUserResponse {
    private User user;
    private boolean isConnected;
}
