package br.com.alura.spring.mvc.controllers;

import br.com.alura.spring.mvc.models.Request;
import br.com.alura.spring.mvc.models.StatusRequest;
import br.com.alura.spring.mvc.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("home/{statusRequest}")
    public String status(@PathVariable("statusRequest") String statusRequest, Model model) {
        List<Request> requests = rr.findByStatusRequest(StatusRequest.valueOf(statusRequest.toUpperCase()));
        model.addAttribute("requests", requests);
        model.addAttribute("statusRequest", statusRequest);
        return "home";
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/formCad", method = RequestMethod.POST)
    public String insert(@Valid Request request, BindingResult result){
        if(result.hasErrors()) {
            return "formCad";
        }
        request.setStatusRequest(StatusRequest.valueOf("WAITING"));
        rr.save(request);
        return "redirect:/home";
    }
}