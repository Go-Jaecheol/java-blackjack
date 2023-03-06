package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import domain.card.Deck;
import domain.card.shuffler.FixedDeckShuffler;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("전체 참가자의 수는 2명 이상 8명 이하여야 한다.")
    @Test
    void validateSizeTest() {
        Deck deck = new Deck(new FixedDeckShuffler());
        assertDoesNotThrow(() -> new Participants(List.of("깃짱", "망고"), deck));
        assertThatThrownBy(() -> new Participants(List.of(), deck))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Participants(List.of("깃짱", "망고", "저문", "이리내", "디노", "오잉", "체인저", "토리"), deck))
                .isInstanceOf(IllegalArgumentException.class);
    }
}