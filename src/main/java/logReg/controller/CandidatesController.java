package logReg.controller;

import logReg.Repository.CandidatesRepository;
import logReg.model.Candidates;
import logReg.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;

@Controller
public class CandidatesController {

    @Autowired
    private CandidatesRepository candidatesRepository;

    @PostMapping("/createCandidate")
    public String saveCandidate(@ModelAttribute Candidates candidate) {
        Candidates newCandidate = new Candidates();
        newCandidate.setUsername(candidate.getUsername());
        newCandidate.setVotes(0);
        candidatesRepository.save(newCandidate);
        return "redirect:/vote";
    }

    @RequestMapping("/addCand")
    public String AddCandidate(Model model) {
        model.addAttribute("Candidates", new Candidates());
        return "candidate_registration_form";
    }
    @RequestMapping("/vote")
    public String showCandidates(Model model) {
        model.addAttribute("listCandidates", candidatesRepository.findAll());
        return "candidates";
    }
    @GetMapping("/Addvote/{id}")
    public String AddVotes(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("Candidates", new Candidates());
        Candidates addVoteToCandidate = candidatesRepository.getById(id);
        addVoteToCandidate.setVotes(addVoteToCandidate.getVotes()+1);
        candidatesRepository.saveAndFlush(addVoteToCandidate);
        return "redirect:/vote";
    }
}
