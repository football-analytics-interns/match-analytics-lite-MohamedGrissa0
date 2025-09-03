package org.example.service;

import org.example.model.Match;
import org.example.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatch(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Long id, Match match) {
        Match existing = matchRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setDate(match.getDate());
        existing.setHomeTeam(match.getHomeTeam());
        existing.setAwayTeam(match.getAwayTeam());
        existing.setHomeScore(match.getHomeScore());
        existing.setAwayScore(match.getAwayScore());

        return matchRepository.save(existing);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
