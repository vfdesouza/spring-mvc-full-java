package br.com.alura.spring.mvc.controllers;

import br.com.alura.spring.mvc.models.Request;
import br.com.alura.spring.mvc.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestController {

    @Autowired
    private RequestRepository rr;

    @RequestMapping(value ="/formCad", method = RequestMethod.GET)
    public String form() {
        return "formCad";
    }

    @RequestMapping("/home")
    public ModelAndView findAllOrfindByProductName(@RequestParam(value = "productName", required = false) String productName) {
        if(productName == null) {
            ModelAndView mv = new ModelAndView("home");
            Iterable<Request> requests = rr.findAll();
            mv.addObject("requests", requests);
            return mv;
        }
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("requests", rr.findByProductNameIgnoreCaseContaining(productName));
        return mv;
    }

    @RequestMapping(value = "/formCad", method = RequestMethod.POST)
    public String insert(Request request){
        rr.save(request);
        return "redirect:/home";
    }
}
