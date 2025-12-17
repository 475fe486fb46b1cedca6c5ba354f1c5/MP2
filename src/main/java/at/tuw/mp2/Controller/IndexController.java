package at.tuw.mp2.Controller;

import at.tuw.mp2.DAOs.Header;
import at.tuw.mp2.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/")
    public String index(HttpServletRequest req, Model model, @RequestParam(required = false,name = "err",defaultValue = "0") int err) {
        model.addAttribute("err",err);
        if(!(boolean)req.getAttribute("logedin"))
            return "login/login";

        model.addAttribute("user", userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get());
        model.addAttribute("module", Header.HOME);
        return "home/index";

    }
}
