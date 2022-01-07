package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

import static ru.job4j.accident.WebInit.APPLICATION_CONTEXT;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = APPLICATION_CONTEXT.getBean(AccidentMem.class).getAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
