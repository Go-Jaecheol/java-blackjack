package domain.participant;

import domain.card.Deck;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_SIZE_EXCLUSIVE = 1;
    private static final int MAX_SIZE_EXCLUSIVE = 7;
    public static final String SIZE_ERROR_MESSAGE = "[ERROR] 전체 플레이어의 수는 1명 이상 7명 이하여야 합니다!";

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(List<String> names, Deck deck) {
        validateSize(names);
        this.players = names.stream()
                .map(Name::new)
                .map(name -> new Player(name, deck.getInitialDeck()))
                .collect(Collectors.toList());
        this.dealer = new Dealer(deck.getInitialDeck());
    }

    private void validateSize(final List<String> names) {
        if (names.size() < MIN_SIZE_EXCLUSIVE || names.size() > MAX_SIZE_EXCLUSIVE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
