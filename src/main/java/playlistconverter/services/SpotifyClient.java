package playlistconverter.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import playlistconverter.domain.Artist;
import playlistconverter.domain.Playlist;
import playlistconverter.domain.Track;
import playlistconverter.dto.ItemDto;
import playlistconverter.dto.PlaylistDto;

@Component
public class SpotifyClient {

    public Playlist getPlaylist(String token) {
        String url = "https://api.spotify.com/v1/playlists/6tD4f6aP1uoy3tLkt0apVR?si=7b103805a0e54f2f";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<PlaylistDto> response = new RestTemplate().exchange(
                url, HttpMethod.GET, entity, PlaylistDto.class);

        return mapPlaylistDtoToPlaylist(response.getBody());
    }

    private Playlist mapPlaylistDtoToPlaylist(PlaylistDto dto) {
        Playlist playlist = Playlist.builder().build();

        dto.getTracks().getItems().stream()
                .map(ItemDto::getTrack)
                .forEach(trackDto -> {
                    Track track = Track.builder()
                            .name(trackDto.getName())
                            .build();

                    trackDto.getArtists().forEach(artistDto -> {
                        Artist artist = Artist.builder()
                                .name(artistDto.getName())
                                .build();
                        track.getArtists().add(artist);
                    });

                    playlist.getTracks().add(track);
                });

        return playlist;
    }

}
