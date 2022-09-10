package playlistconverter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import playlistconverter.domain.Playlist;
import playlistconverter.services.PlaylistService;
import playlistconverter.interfaces.IExcelService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final IExcelService excelService;

    @GetMapping(value = "/playlist", produces = MediaType.TEXT_HTML_VALUE)
    public String getPlaylist(
            Model model,
            HttpSession session
    ) {
        String token = (String) session.getAttribute("token");

        Playlist playlist = playlistService.getPlaylist(token);

        excelService.generateExcel(playlist);

        model.addAttribute("tracks", playlist.getTracks());

        return "playlist";
    }

}
