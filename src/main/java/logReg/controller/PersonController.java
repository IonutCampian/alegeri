package logReg.controller;

import logReg.Repository.CandidatesRepository;
import logReg.Repository.PersonRepository;
import logReg.model.Candidates;
import logReg.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CandidatesRepository candidatesRepository;


    @GetMapping("")
    public String showHomePage() {
        return "home_page";
    }

    @RequestMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("Person", new Person());
        return "registration_page";
    }

    @RequestMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("Person", new Person());
        return "login_page";
    }

    @PostMapping("/savePerson")
    public String saveUser(@ModelAttribute Person person) {
        Person newPerson = new Person();
        newPerson.setUsername(person.getUsername());
        newPerson.setPassword(person.getPassword());
        newPerson.setEmail(person.getEmail());
        personRepository.save(newPerson);
        return "redirect:/login";
    }

    @RequestMapping("/loginPerson")
    public String checkForUser(@ModelAttribute Person person, Model model) {
        if ((personRepository.findByUsernameAndPassword(person.getUsername(), person.getPassword())) != null) {
            model.addAttribute("listCandidates", candidatesRepository.findAll());
            return "wellcome";
        } else {
            return "redirect:/login_page";
        }
    }

}
