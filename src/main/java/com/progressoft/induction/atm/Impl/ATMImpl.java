package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.List;

public class ATMImpl implements ATM {
    private final BankingSystemImpl bankingSystem=new BankingSystemImpl();
    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        // Your code here
        BigDecimal balance = checkBalance(accountNumber);
        BigDecimal atmTotalCash = bankingSystem.sumOfMoneyInAtm();

        if (balance.compareTo(amount) < 0 || balance.compareTo(BigDecimal.ZERO)==0){
            throw new InsufficientFundsException("Insufficient fund in the account.");
        }

        if (atmTotalCash.compareTo(amount) < 0 ) {
            throw  new NotEnoughMoneyInATMException("Not enough money in the ATM");
        }

            List<Banknote> banknotes = bankingSystem.withdrawBanknotes(amount);
            if (!banknotes.isEmpty()) {
                bankingSystem.debitAccount(accountNumber, amount);
            }
        return banknotes;
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        return bankingSystem.getAccountBalance(accountNumber);
    }
}
