package playlistconverter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Track {
    private String name;
    private List<Artist> artists = new ArrayList<>();
}
