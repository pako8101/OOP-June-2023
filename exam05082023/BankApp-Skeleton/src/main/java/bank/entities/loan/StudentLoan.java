package bank.entities.loan;

public class StudentLoan extends BaseLoan{

    public StudentLoan() {
        super(1, 10000);
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
