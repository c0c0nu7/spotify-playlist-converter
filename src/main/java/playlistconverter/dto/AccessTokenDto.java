package playlistconverter.dto;

import lombok.Getter;

@Getter
public class AccessTokenDto {

    String access_token;
    String token_type;
    String scope;
    Integer expires_in;
    String refresh_token;


}
