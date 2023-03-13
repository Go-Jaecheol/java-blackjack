package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("카드 추가 테스트")
    @Test
    void addCardTest() {
        Card initialCard = new Card(Value.ACE, Shape.HEART);
        Hand hand = new Hand(new ArrayList<>(List.of(initialCard)));

        hand.addCard(new Card(Value.KING, Shape.SPADE));

        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @DisplayName("Hand 점수 계산 테스트")
    @Test
    void calculateScoreTest() {
        List<Card> initialCards = List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.KING, Shape.HEART));
        Hand hand = new Hand(new ArrayList<>(initialCards));

        assertThat(hand.calculateScore().getValue()).isEqualTo(21);

        hand.addCard(new Card(Value.ACE, Shape.HEART));
        assertThat(hand.calculateScore().getValue()).isEqualTo(12);
    }
}
