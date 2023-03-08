package controller;

import domain.BlackJackGame;
import domain.PlayerCommand;
import domain.WinningStatus;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import view.InputView;
import view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(inputView.readPlayerNames());
        Participants participants = blackJackGame.getParticipants();

        outputView.printInitialState(blackJackGame.getParticipants());
        receiveAdditionalCard(blackJackGame.getDeck(), participants, participants.getDealer());

        outputView.printFinalState(participants);
        printFinalResult(blackJackGame);
    }

    private void receiveAdditionalCard(Deck deck, Participants participants, Dealer dealer) {
        receiveAdditionalPlayerCard(deck, participants);
        receiveAdditionalDealerCard(deck, dealer);
    }

    private void printFinalResult(BlackJackGame blackJackGame) {
        Map<Player, WinningStatus> playersResult = blackJackGame.calculatePlayersResult();
        List<Integer> dealerResult = blackJackGame.calculateDealerResult(playersResult);
        outputView.printFinalResultMessage();
        outputView.printDealerResult(dealerResult);
        outputView.printPlayerResult(playersResult);
    }

    private void receiveAdditionalDealerCard(Deck deck, Dealer dealer) {
        while (dealer.calculateScore() <= BlackJackGame.DEALER_REPEAT_NUMBER) {
            outputView.printFillDealerCards();
            dealer.receiveCard(deck.getCard());
        }
    }

    private void receiveAdditionalPlayerCard(Deck deck, Participants participants) {
        for (Player player : participants.getPlayers()) {
            repeatReceiveCard(deck, player);
        }
    }

    private void repeatReceiveCard(Deck deck, Player player) {
        boolean repeat = true;
        while (repeat) {
            PlayerCommand command = initializeCommand(player.getName());
            player.receiveAdditionalCard(command, deck);
            repeat = player.calculateScore() < BlackJackGame.BLACKJACK_NUMBER && command.isHit();
            outputView.printSingleState(player);
        }
    }

    private PlayerCommand initializeCommand(String playerName) {
        try {
            return PlayerCommand.from(inputView.readHit(playerName));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return initializeCommand(playerName);
        }
    }
}
