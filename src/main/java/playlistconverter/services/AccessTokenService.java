package playlistconverter.services;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import playlistconverter.dto.AccessTokenDto;

import java.net.URISyntaxException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final RestTemplate restTemplate;

    @Value("${authorization.client_id}")
    private String CLIENT_ID;
    @Value("${authorization.client_secret}")
    private String CLIENT_SECRET;

    public String getToken(String code) {
        String authHeader = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedIdAndSecret = Base64.getEncoder().encodeToString(authHeader.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedIdAndSecret);

        try {
            URIBuilder uriBuilder = new URIBuilder("https://accounts.spotify.com/api/token")
                    .addParameter("grant_type", "authorization_code")
                    .addParameter("code", code)
                    .addParameter("redirect_uri", "http://localhost:8080/callback");

            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            ResponseEntity<AccessTokenDto> response = restTemplate.exchange(uriBuilder.build(), HttpMethod.POST, entity, AccessTokenDto.class);

            if (HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody().getAccess_token();
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "failure";
    }

}
