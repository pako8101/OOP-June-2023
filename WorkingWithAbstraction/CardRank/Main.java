package WorkingWithAbstraction.CardRank;


public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        CardRank[] cardRank = CardRank.values();
        for (CardRank cardRank1 : cardRank) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", cardRank1.ordinal(), cardRank1.name());
        }
    }
}
