package playlistconverter.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TrackDto {

    private String name;
    private List<ArtistDto> artists;


}
