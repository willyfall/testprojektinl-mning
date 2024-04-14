package se.reky.hakan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.reky.hakan.model.Player;
import se.reky.hakan.service.PlayerService;

import java.util.Scanner;

@SpringBootApplication
public class ProjektuppgiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektuppgiftApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(Game game) {
		return args -> new Thread(game::start).start();
	}
	@Bean
	public Game game(Scanner scanner, PlayerService playerService,
					 GameStateManager gameStateManager,
					 IOHandler ioHandler, PlayerInteraction playerInteraction) {
		return new Game(ioHandler, gameStateManager,playerInteraction,
				playerService);
	}

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

	@Bean
	public GameStateManager gameStateManager(Scanner scanner, Player player, IOHandler ioHandler){
		return new SimpleGameStateManager(player, ioHandler);
	}
	@Bean
	public IOHandler ioHandler(Scanner scanner){
		return new IOHandler(scanner);

	}
	@Bean
	public PlayerInteraction playerInteraction(Player player, IOHandler ioHandler){
		return new SimplePlayerInteraction(player, ioHandler);
	}
	@Bean
	public Player player(){
		return new Player();
	}




}
