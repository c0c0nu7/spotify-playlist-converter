package playlistconverter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import playlistconverter.services.PlaylistService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping(value = "/playlist", produces = MediaType.TEXT_HTML_VALUE)
    public String getPlaylist(
            Model model,
            HttpSession session
    ) {
        String token = (String) session.getAttribute("token");

        Object playlist = playlistService.getPlaylistTracks(token);

        model.addAttribute("tracks", playlist);

        return "playlist";
    }

}
