package se.reky.hakan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.reky.hakan.model.Player;
import se.reky.hakan.service.PlayerService;

import java.util.Scanner;
/*
	Denna klass innehåller main-metoden som startar applikationen.
	Detta är en spring-boot-applikation och innehåller två delar:
	1) Ett command-line-spel i samma anda och med samma tekniker
		som det äventyrsspel ni tidigare har skapat.
		Detta spel startas i en egen tråd via metoden run(Game game) nedan.
		Det ser krångligt ut, men bry er inte om att det är flertrådat, det är
		inget ni ska testa eller sätta er in i. Spelet startas
		med Game-klassens start()-metod, där kan ni börja kika på kod.
		Spela några spel och kika runt och undersök vilka klasser som finns
		och vad de gör.
	2) En web-del som exponerar en endpoint som visar en high-score-
		lista från spelade spel. Spelaren med högst poäng (experience) visas
		högst upp i listan och sedan fallande.
 */
@SpringBootApplication
public class ProjektuppgiftApplication {

	public static void main(String[] args) {
		//Här startar själva Spring-applikationen
		SpringApplication.run(ProjektuppgiftApplication.class, args);
	}
	//Här startar tråden med själva spelet
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
	public GameStateManager gameStateManager(Scanner scanner, IOHandler ioHandler){
		return new SimpleGameStateManager(ioHandler);
	}
	@Bean
	public IOHandler ioHandler(Scanner scanner){
		return new IOHandler(scanner);

	}
	@Bean
	public PlayerInteraction playerInteraction(IOHandler ioHandler){
		return new SimplePlayerInteraction(ioHandler);
	}





}
