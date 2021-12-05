package playlistconverter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import playlistconverter.services.SpotifyAuthorizationService;

import java.net.URISyntaxException;

@Controller
@AllArgsConstructor
public class IndexController {

    private final SpotifyAuthorizationService spotifyAuthorizationService;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String index(Model model) throws URISyntaxException {
        model.addAttribute("url", spotifyAuthorizationService.getAuthorizationUri());
        return "index";
    }


}
