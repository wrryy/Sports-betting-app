package pl.wrryy.amelco.service;

import com.github.javafaker.Faker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Rate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataFakerService {
    private static int c = 0;
    private GameService gameService;
    private BetCategoryService betCategoryService;
    private BetService betService;
    private CouponService couponService;
    private RateService rateService;

    public DataFakerService(GameService gameService, BetCategoryService betCategoryService,
                            BetService betService, CouponService couponService, RateService rateService) {
        this.gameService = gameService;
        this.betCategoryService = betCategoryService;
        this.betService = betService;
        this.couponService = couponService;
        this.rateService = rateService;
    }

    public Rate getRate() {
        Rate rate = new Rate();
        rate.setGame(game);
        rate.setBetCategory(betCategoryService.findOne(j));
        rate.setCreated(LocalDateTime.now());
        rate.setRate(faker.number().randomDouble(2, 1, 5));

        return rate;
    }

    @Scheduled(fixedRate = 5000)
    public void regenerate() {
        if (c != 0) {
            c+=1;
            Faker faker = new Faker();
            for (long i = 0; i < 10; i++) {
                Game game = gameService.findOne(i + 1);
                for (int j = 1; j < 4; j++) {
                    Rate rate = new Rate();
                    rate.setGame(game);
                    rate.setBetCategory(betCategoryService.findOne(j));
                    rate.setCreated(LocalDateTime.now());
                    rate.setRate(faker.number().randomDouble(2, 1, 5));
                    rateService.saveRate(rate);
                }
            }
        }
    }

    //    @Scheduled(cron = "0 0/1 * * * ?")
    public void checkActiveMatches() {
        for (int i = 0; i < 5; i++) {
            Game game = gameService.findOne(i + 1);
            game.setEnded(false);
            gameService.saveGame(game);
        }
    }

    /**
     * Checks if active bets in database won.
     */
    @Scheduled(cron = "0 2 * * * ?")
    public void changeWonBets() {
        List<Bet> activeBets = betService.findAllByActiveIsTrue();
        activeBets.stream()
                .filter(Bet::isGameFinished)
                .map(this::checkIfWon)
                .collect(Collectors.toList());

    }

    public Bet checkIfWon(Bet bet) {
        boolean iswon = bet.getGame().getOutcome() == bet.getOutcome();
        bet.setWon(iswon);
        bet.setActive(false);
        betService.saveBet(bet);
        return bet;
    }

}