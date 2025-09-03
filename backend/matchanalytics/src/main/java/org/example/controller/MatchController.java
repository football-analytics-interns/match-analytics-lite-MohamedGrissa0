package org.example.controller;

import org.example.model.Match;
import org.example.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // READ all
    @GetMapping
    public List<Match> getMatches() {
        return matchService.getAllMatches();
    }

    // READ one
    @GetMapping("/{id}")
    public Match getMatch(@PathVariable Long id) {
        return matchService.getMatch(id);
    }

    // CREATE
    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchService.saveMatch(match);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable Long id, @RequestBody Match match) {
        return matchService.updateMatch(id, match);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }
}
