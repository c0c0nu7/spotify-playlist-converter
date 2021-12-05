package playlistconverter.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class PlaylistService {

    public Object getPlaylistTracks(String token) {
        LinkedHashMap result = (LinkedHashMap) getPlaylist(token);
        return result.get("tracks");
    }

    private Object getPlaylist(String token) {
        String url = "https://api.spotify.com/v1/playlists/6tD4f6aP1uoy3tLkt0apVR?si=7b103805a0e54f2f";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Object> response = new RestTemplate().exchange(
                url, HttpMethod.GET, entity, Object.class);

        System.out.println(response);

        return response.getBody();
    }

}
