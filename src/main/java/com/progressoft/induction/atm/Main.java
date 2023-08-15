package com.progressoft.induction.atm;

import com.progressoft.induction.atm.Impl.ATMImpl;
import com.progressoft.induction.atm.Impl.BankingSystemImpl;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String args[]){
        ATM atm = new ATMImpl();

        try {
            String accountNumber = "123456789";
            BigDecimal withdrawalAmount = new BigDecimal("150.0");

            List<Banknote> withdrawnBanknotes = atm.withdraw(accountNumber, withdrawalAmount);
            System.out.println("Withdrawn banknotes: " + withdrawnBanknotes);

            BigDecimal remainingBalance = atm.checkBalance(accountNumber);
            System.out.println("Remaining balance: " + remainingBalance);

        } catch (AccountNotFoundException e) {
            System.out.println("Account not found.");
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds.");
        } catch (NotEnoughMoneyInATMException e) {
            System.out.println("Not enough money in the ATM.");
        }
    }
}
