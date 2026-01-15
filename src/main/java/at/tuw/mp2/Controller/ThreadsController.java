package at.tuw.mp2.Controller;

import at.tuw.mp2.DAOs.CommentDAO;
import at.tuw.mp2.DAOs.Header;
import at.tuw.mp2.DAOs.ThreadDAO;
import at.tuw.mp2.Model.Comment;
import at.tuw.mp2.Model.Thread;
import at.tuw.mp2.Model.User;
import at.tuw.mp2.Repositories.CommentRepository;
import at.tuw.mp2.Repositories.ThreadRepository;
import at.tuw.mp2.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class ThreadsController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ThreadRepository threadRepository;

    @Autowired
    public CommentRepository commentRepository;

    @GetMapping("/threads")
    public String explore(HttpServletRequest req,Model model) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";

        model.addAttribute("user", userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get());
        model.addAttribute("threads", threadRepository.findAll());
        model.addAttribute("module", Header.EXPLORE);
        return "home/explore";
    }

    @GetMapping("/mythreads")
    public String mythreads(HttpServletRequest req,Model model) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";

        User user = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        model.addAttribute("user", user);
        model.addAttribute("threads", Stream.concat(user.getThreads().stream(),user.getComments().stream().map(Comment::getThread)).distinct().toList());
        model.addAttribute("module", Header.MYTHREADS);
        return "home/mythreads";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest req,Model model) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";

        model.addAttribute("user", userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get());
        model.addAttribute("module", Header.CREATE);
        return "home/create";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest req,@ModelAttribute ThreadDAO body){
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";
        User usr = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        Thread th = new Thread();
        th.setCreator(usr);
        th.setBody(body.getContent());
        th.setHd(body.getHeader());
        threadRepository.save(th);

        return "redirect:/thread?id=" + th.getId();
    }

    @GetMapping("thread")
    public String thread(HttpServletRequest req,Model model,@RequestParam(name = "id",required = true) int id) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";
        User usr = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        Optional<Thread> oth = threadRepository.findById(id);
        if(!oth.isPresent())
            return "redirect:/";
        Thread th = oth.get();

        model.addAttribute("user", usr);
        model.addAttribute("thread", th);
        model.addAttribute("module", null);
        model.addAttribute("creator",th.getCreator().getId() == usr.getId());
        return "home/thread";
    }

    @PostMapping("comment")
    public String comment(HttpServletRequest req,@RequestParam(name = "id",required = true) int id,@ModelAttribute CommentDAO body) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";
        User usr = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        Optional<Thread> oth = threadRepository.findById(id);
        if(!oth.isPresent())
            return "redirect:/";
        Thread th = oth.get();

        Comment cmd = new Comment();
        cmd.setText(body.getComment());
        cmd.setCommentor(usr);
        cmd.setThread(th);

        commentRepository.save(cmd);

        return "redirect:/thread?id=" + id;
    }

    @GetMapping("delete")
    public String delete(HttpServletRequest req,Model model,@RequestParam(name = "id",required = true) int id) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";

        User usr = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        Optional<Thread> oth = threadRepository.findById(id);
        if(!oth.isPresent())
            return "redirect:/?err=3";
        Thread th = oth.get();

        if(!Objects.equals(th.getCreator().getId(), usr.getId()))
            return "redirect:/?err=2";

        threadRepository.delete(th);

        return "redirect:/";
    }

    @GetMapping("edit")
    public String edit(HttpServletRequest req,Model model,@RequestParam(name = "id",required = true) int id) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";
        User usr = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        Optional<Thread> oth = threadRepository.findById(id);
        if(!oth.isPresent())
            return "redirect:/";
        Thread th = oth.get();

        if(!Objects.equals(th.getCreator().getId(), usr.getId()))
            return "redirect:/";

        model.addAttribute("user", usr);
        model.addAttribute("thread", th);
        model.addAttribute("module", null);
        model.addAttribute("creator",th.getCreator().getId() == usr.getId());
        return "home/edit";
    }

    @PostMapping("edit")
    public String edit(HttpServletRequest req,@ModelAttribute ThreadDAO body) {
        if(!(boolean)req.getAttribute("logedin"))
            return "redirect:/";
        User usr = userRepository.findUserByUser((String)req.getAttribute("username")).stream().findFirst().get();
        Optional<Thread> oth = threadRepository.findById(body.getId());
        if(!oth.isPresent())
            return "redirect:/";
        Thread th = oth.get();

        if(!Objects.equals(th.getCreator().getId(), usr.getId()))
            return "redirect:/";

        th.setHd(body.getHeader());
        th.setBody(body.getContent());

        threadRepository.save(th);
        return "redirect:/thread?id=" + th.getId();
    }
}
