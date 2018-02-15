package pl.wrryy.amelco.service;

import com.github.javafaker.Faker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Rate;
import pl.wrryy.amelco.entity.User;

import java.math.BigDecimal;
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
    private RateService rateService;
    private UserService userService;

    public DataFakerService(GameService gameService, BetCategoryService betCategoryService,
                            BetService betService, RateService rateService, UserService userService) {
        this.gameService = gameService;
        this.betCategoryService = betCategoryService;
        this.betService = betService;
        this.rateService = rateService;
        this.userService = userService;
    }

    @Scheduled(fixedRate =10000)
    public void generate() {
            Faker faker = new Faker();
            List<Game> games = gameService.findAllActiveGames();
            for (int i = 0; i < games.size(); i++) {
                Game game = games.get(i);
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
    /**
     * Checks if active bets in database won.
     */
    @Scheduled(fixedRate = 5000)
    public void changeWonBets() {
        List<Bet> activeBets = betService.findAllByActiveIsTrue();
        activeBets.stream()
                .filter(Bet::isGameFinished)
                .map(this::checkIfWon)
                .collect(Collectors.toList());
    }
    @Scheduled(fixedRate = 3000)
    public void giveMoneyifWon() {
        List<Bet> wonBets = betService.findAllBetsByWonIsTrue();
        for (Bet bet: wonBets) {
            User betUser = bet.getCoupon().getUser();
            BigDecimal prize = bet.getStake().multiply(BigDecimal.valueOf(bet.getRate().getRate()));
            userService.walletPay(betUser, prize);
        }
    }

    private Bet checkIfWon(Bet bet) {
        boolean iswon = bet.getGame().getOutcome() == bet.getOutcome();
        bet.setWon(iswon);
        bet.setActive(false);
        betService.saveBet(bet);
        return bet;
    }


}