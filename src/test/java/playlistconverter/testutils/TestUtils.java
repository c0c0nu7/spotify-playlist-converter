package playlistconverter.testutils;

import playlistconverter.domain.Artist;
import playlistconverter.domain.Track;

import java.util.List;

public class TestUtils {

    public static Track createTrack(String name, String artistName) {
        return Track.builder()
                .name(name)
                .artists(
                        List.of(Artist.builder()
                                        .name(artistName)
                                        .build()))
                .build();
    }

}
