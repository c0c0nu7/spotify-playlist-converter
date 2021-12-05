package playlistconverter.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Track {

    private List<Artist> artists;
    private String name;

}
