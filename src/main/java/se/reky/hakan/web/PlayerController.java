package se.reky.hakan.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.reky.hakan.model.Player;
import se.reky.hakan.service.PlayerService;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", playerService.findAllPlayersSortedByExperience());
        return "players"; // Name of the Thymeleaf template
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){

        return String.format("Hello %s!", name + "<br>" +
                "Hello kalle<br>" +
                "Hello next");// Om namn skickats med, skriv ut det
    }
    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name) {
        return "Hello, " + name + "!";
    }
    @GetMapping("/player")
    public String getPlayer(@RequestParam("id") Long id, Model model) {
        Player player = playerService.findPlayerById(id);
        model.addAttribute("player", player);
        return "player";  // Ensure 'player.html' exists under 'src/main/resources/templates'
    }

}
