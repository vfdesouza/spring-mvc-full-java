package br.com.alura.spring.mvc.controllers;

import br.com.alura.spring.mvc.models.Request;
import br.com.alura.spring.mvc.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RequestController {

    @Autowired
    private RequestRepository rr;

    @RequestMapping(value ="/formCad", method = RequestMethod.GET)
    public String form(Request request) {
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
    public String insert(@Valid Request request, BindingResult result){
        if(result.hasErrors()) {
            return "formCad";
        }
        rr.save(request);
        return "redirect:/home";
    }
}
