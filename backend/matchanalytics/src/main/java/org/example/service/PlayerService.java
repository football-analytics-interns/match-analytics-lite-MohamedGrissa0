package org.example.service;

import org.example.model.Player;
import org.example.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.List; // make sure it's java.util.List

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player player) {
        Player existing = playerRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(player.getName());
        existing.setTeam(player.getTeam());
        existing.setPosition(player.getPosition());

        return playerRepository.save(existing);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
