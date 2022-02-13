package playlistconverter.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import playlistconverter.domain.Playlist;
import playlistconverter.domain.Track;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService {

    private SpotifyClient spotifyClient;

    public Playlist getPlaylist(String token) {
        return spotifyClient.getPlaylist(token);
    }

}
