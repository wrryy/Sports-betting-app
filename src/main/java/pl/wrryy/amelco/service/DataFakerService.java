//package pl.wrryy.amelco.service;
//
//import com.github.javafaker.Faker;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import pl.wrryy.amelco.entity.Game;
//import pl.wrryy.amelco.entity.Rate;
//
//import java.time.LocalDateTime;
//
//@Service
//public class DataFakerService {
//    private GameService gameService;
//    private BetCategoryService betCategoryService;
//
//    public DataFakerService(GameService gameService, BetCategoryService betCategoryService) {
//        this.gameService = gameService;
//        this.betCategoryService = betCategoryService;
//    this.regenerate();
//    }
//    @Scheduled(fixedRate = 1)
//    public void regenerate() {
//        Faker faker = new Faker();
//        for (int i = 0; i < 10; i++) {
//            Game game = gameService.findOne(i + 1);
//            for (int j = 1; j < 4; j++) {
//                Rate rate = new Rate();
//                rate.setGame(game);
//                rate.setBetCategory(betCategoryService.findOne(j));
//                rate.setCreated(LocalDateTime.now());
//                rate.setRate(faker.number().randomDouble(2, 1, 5));
//            }
//        }
//    }
//}