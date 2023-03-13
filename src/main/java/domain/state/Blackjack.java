package domain.state;

import domain.Result;

public class Blackjack extends Finished {

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }

    @Override
    public Result calculateResult(final State dealerState) {
        if (dealerState.isBlackJack()) {
            return Result.TIE;
        }
        return Result.BLACKJACK;
    }
}
