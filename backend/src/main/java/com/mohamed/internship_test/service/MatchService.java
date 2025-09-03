package com.yourname.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourname.project.model.Match;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MatchService {

    public Match getMatch() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/match.json");
        return mapper.readValue(is, Match.class);
    }
}
