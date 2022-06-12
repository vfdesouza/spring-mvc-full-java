package br.com.alura.spring.mvc.controllers;

import br.com.alura.spring.mvc.models.Request;
import br.com.alura.spring.mvc.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    RequestRepository rr;

    @GetMapping("/home")
    public ModelAndView home(ModelAndView mv) {
        Iterable<Request> requests = rr.findAll();
        mv.addObject("requests", requests);
        return mv;
    }
}
