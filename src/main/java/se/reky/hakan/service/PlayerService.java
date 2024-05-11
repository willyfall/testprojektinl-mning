package se.reky.hakan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.reky.hakan.model.Player;
import se.reky.hakan.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void savePlayerStats(Player player) {
        playerRepository.save(player);
    }

    public Player findPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    public List<Player> findAllPlayersSortedByExperience(){
        return playerRepository.findAll(
                Sort.by(Sort.Direction.DESC, "experience"));
    }

}

