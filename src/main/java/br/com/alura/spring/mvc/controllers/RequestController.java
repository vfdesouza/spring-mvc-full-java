package br.com.alura.spring.mvc.controllers;

import br.com.alura.spring.mvc.models.Request;
import br.com.alura.spring.mvc.models.StatusRequest;
import br.com.alura.spring.mvc.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("formCad")
public class RequestController {
    @Autowired
    private RequestRepository rr;

    @GetMapping
    public String form(Request request) {
        return "formCad";
    }

    @PostMapping
    public String insert(@Valid Request request, BindingResult result){
        if(result.hasErrors()) {
            return "formCad";
        }
        request.setStatusRequest(StatusRequest.valueOf("WAITING"));
        rr.save(request);
        return "redirect:/home";
    }
}