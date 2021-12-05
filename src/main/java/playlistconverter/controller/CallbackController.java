package playlistconverter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import playlistconverter.services.AccessTokenService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class CallbackController {

    private final AccessTokenService accessTokenService;

    @GetMapping(value = "/callback", produces = MediaType.TEXT_HTML_VALUE)
    public String callback(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String error,
            Model model,
            final HttpSession session
    ) {
        if ("access_denied".equals(error)) {
            return "callback_failure";
        }

        String token = accessTokenService.getToken(code);
        session.setAttribute("token", token);
        model.addAttribute("accessToken", token);

        return "callback_success";
    }



}
