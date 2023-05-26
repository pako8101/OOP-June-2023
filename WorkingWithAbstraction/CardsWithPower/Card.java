package WorkingWithAbstraction.CardsWithPower;



public class Card {
    private CardSuits cardSuits;
    private CardRanks cardRanks;
    private  int power;

    public Card(CardSuits cardSuits, CardRanks cardRanks) {
        this.cardSuits = cardSuits;
        this.cardRanks = cardRanks;
    }

    public CardSuits getCardSuits() {
        return cardSuits;
    }

    public void setCardSuits(CardSuits cardSuits) {
        this.cardSuits = cardSuits;
    }

    public CardRanks getCardRank() {
        return cardRanks;
    }

    public void setCardRank(CardRanks cardRanks) {
        this.cardRanks = cardRanks;
    }

    public int getPower() {
        return this.cardSuits.getSuitPower() + this.cardRanks.getPowerRank();
    }

    public void setPower(int power) {
        this.power = power;
    }
}
