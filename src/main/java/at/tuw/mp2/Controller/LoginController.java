package at.tuw.mp2.Controller;

import at.tuw.mp2.DAOs.LoginDAO;
import at.tuw.mp2.DAOs.RegisterDAO;
import at.tuw.mp2.JWTToken;
import at.tuw.mp2.Model.User;
import at.tuw.mp2.Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    public UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @PostMapping("/login")
    public String login(HttpServletRequest req, @ModelAttribute LoginDAO body, @RequestParam(name = "err",defaultValue = "0",required = false) int err, Model model, HttpServletResponse response) {
        if((boolean)req.getAttribute("logedin"))
            return "redirect:/";

        //Optional<User> usr = userRepository.findUserByUser(body.getUser()).stream().findFirst();
        Optional<User> usr = em.createNativeQuery("SELECT * FROM user WHERE user = \"" + body.getUser() + "\" AND pw = \""+ body.getPw() + "\"",User.class)
                .getResultList().stream().findFirst();
        if(!usr.isPresent())
            return "redirect:/?err=1";
        model.addAttribute("user",usr.get());
        model.addAttribute("err",err);

        Cookie cookie = new Cookie("token", JWTToken.generate(usr.get()));
        cookie.setMaxAge(3600); // 1 hour
        cookie.setSecure(true); // use HTTPS in production!
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(name = "err",defaultValue = "0",required = false) int err) {
        model.addAttribute("err",err);
        return "login/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDAO body, Model model) {
        if(!body.getPw().equals(body.getPwrep()))
            return "redirect:/register?err=1";

        User user = new User();
        user.setUser(body.getUser());
        user.setPw(body.getPw());
        userRepository.save(user);
        return "redirect:/";
    }
}
