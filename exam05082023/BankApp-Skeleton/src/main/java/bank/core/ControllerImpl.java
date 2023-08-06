package bank.core;

import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static bank.common.ConstantMessages.*;
import static bank.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private LoanRepository loans;
    private Map<String, Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new LinkedHashMap<>();
    }

    @Override
    public String addBank(String type, String name) {
        Bank bank = type.equals(BranchBank.class.getSimpleName())
                ? new BranchBank(name)
                : new CentralBank(name);
        Bank bankPrev = this.banks.get(name);
        if (bankPrev == null) {
            this.banks.put(name, bank);
            return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
        }
        throw new IllegalArgumentException(INVALID_BANK_TYPE);
    }

    @Override
    public String addLoan(String type) {
        Loan loan = type.equals(StudentLoan.class.getSimpleName())
                ? new StudentLoan()
                : new MortgageLoan();
        Loan loanPrev = this.loans.findFirst(type);
        if (loanPrev == null) {
            this.loans.addLoan(loan);
            return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
        }
        throw new IllegalArgumentException(INVALID_LOAN_TYPE);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loan = this.loans.findFirst(loanType);
        if (loan == null) {
            throw new IllegalArgumentException(String.format(NO_LOAN_FOUND, loanType));
        }
        Bank bank = banks.get(bankName);
        bank.addLoan(loan);
        this.loans.removeLoan(loan);
        return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName,
                            String clientID, double income) {
        Client client;
        if (clientType.equals(Adult.class.getSimpleName())) {
            client = new Adult(clientName, clientID, income);
        } else if (clientType.equals(Student.class.getSimpleName())) {
            client = new Student(clientName, clientID, income);
        } else {
            throw new IllegalArgumentException(INVALID_CLIENT_TYPE);
        }
        Bank bank = banks.get(bankName);
        String bankType = bank.getClass().getSimpleName();
        boolean bankIsCentralAndClientAreAdult = bankType.equals(CentralBank.class.getSimpleName())
                && clientType.equals(Adult.class.getSimpleName());
        boolean bankIsBranchAndClientAreStudent = bankType.equals(BranchBank.class.getSimpleName())
                && clientType.equals(Student.class.getSimpleName());
        if (bankIsCentralAndClientAreAdult || bankIsBranchAndClientAreStudent) {
            bank.addClient(client);
        } else {
            return UNSUITABLE_BANK;
        }
        return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
    }

    @Override
    public String finalCalculation(String bankName) {
        Collection<Client> clients = banks.get(bankName).getClients();
        Bank bank = this.banks.get(bankName);
        double loansAmount = bank.getLoans().stream().mapToDouble(Loan::getAmount).sum();//(long) bank.getLoans().size();
        double clientIncome = clients.stream().mapToDouble(Client::getIncome).sum();
        double sum = loansAmount + clientIncome;
        return String.format(FUNDS_BANK, bankName, sum);
    }

    @Override
    public String getStatistics() {
        return banks.values().stream()
                .map(Bank::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
