package domain;

import domain.card.Deck;
import domain.participant.BettingMoney;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int DEALER_REPEAT_NUMBER = 16;

    private final Deck deck;
    private Participants participants;

    public BlackJackGame(Map<String, BettingMoney> playerBettingMoneys) {
        this.deck = new Deck();
        deck.shuffleDeck();
        initializeParticipants(playerBettingMoneys, deck);
    }

    private void initializeParticipants(Map<String, BettingMoney> playerBettingMoneys, Deck deck) {
        try {
            this.participants = new Participants(playerBettingMoneys, deck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Player, Integer> calculatePlayersResult() {
        Map<Player, Integer> playersResult = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            int playerFinalResult = decideFinalResult(player, participants.getDealer().calculateScore());
            playersResult.put(player, playerFinalResult);
        }
        return playersResult;
    }

    private List<Integer> calculateWinningCount(Map<WinningStatus, Integer> dealerResult) {
        List<Integer> winningCount = new ArrayList<>();
        winningCount.add(calculateWinCount(dealerResult));
        winningCount.add(calculateTieCount(dealerResult));
        winningCount.add(calculateLoseCount(dealerResult));
        return winningCount;
    }

    public List<Integer> calculateDealerResult(Map<Player, WinningStatus> playersResult) {
        Map<WinningStatus, Integer> dealerResult = new HashMap<>();
        for (WinningStatus playerWinningStatus : playersResult.values()) {
            dealerResult.put(playerWinningStatus.reverse(),
                    dealerResult.getOrDefault(playerWinningStatus.reverse(), 0) + 1);
        }
        return calculateWinningCount(dealerResult);
    }

    private int calculateWinCount(Map<WinningStatus, Integer> dealerResult) {
        return dealerResult.getOrDefault(WinningStatus.WIN, 0);
    }

    private int calculateTieCount(Map<WinningStatus, Integer> dealerResult) {
        return dealerResult.getOrDefault(WinningStatus.TIE, 0);
    }

    private int calculateLoseCount(Map<WinningStatus, Integer> dealerResult) {
        return dealerResult.getOrDefault(WinningStatus.LOSE, 0);
    }

    private int decideFinalResult(final Player player, int dealerScore) {
        int playerScore = player.calculateScore();
        int bettingMoney = player.getBettingMoney();
        if (dealerScore > BLACKJACK_NUMBER) {
            return decideFinalResultDealerBust(playerScore, bettingMoney);
        }
        return decideFinalResultDealerNotBust(dealerScore, playerScore, bettingMoney);
    }

    private int decideFinalResultDealerNotBust(int dealerScore, int score, int bettingMoney) {
        if (score <= BLACKJACK_NUMBER && score > dealerScore) {
            return Result.WIN.calculateResult(bettingMoney);
        }
        if (score == dealerScore) {
            return Result.TIE.calculateResult(bettingMoney);
        }
        return Result.LOSE.calculateResult(bettingMoney);
    }

    private int decideFinalResultDealerBust(int score, int bettingMoney) {
        if (score > BLACKJACK_NUMBER) {
            return Result.TIE.calculateResult(bettingMoney);
        }
        return Result.WIN.calculateResult(bettingMoney);
    }

    public Participants getParticipants() {
        return participants;
    }

    public Deck getDeck() {
        return deck;
    }
}
