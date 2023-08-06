package bank.entities.loan;

public class MortgageLoan extends BaseLoan{
    public MortgageLoan() {
        super(3, 50000);
    }

    @Override
    public int getInterestRate() {
        return super.getInterestRate();
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }
}
