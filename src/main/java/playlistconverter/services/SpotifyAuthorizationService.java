package playlistconverter.services;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class SpotifyAuthorizationService {

    @Value("${authorization.client_id}")
    private String CLIENT_ID;

    public String getAuthorizationUri() throws URISyntaxException {
        //TODO add state param https://developer.spotify.com/documentation/general/guides/authorization/code-flow/

        return new URIBuilder("https://accounts.spotify.com/authorize")
                .addParameter("client_id", CLIENT_ID)
                .addParameter("response_type", "code")
                .addParameter("redirect_uri", "http://localhost:8080/callback")
                .addParameter("scope", "user-read-private user-read-email")
                .addParameter("show_dialog", "true")
                .build()
                .toString();
    }

}
