package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.*;

public class BankingSystemImpl implements BankingSystem {
   Map<String, BigDecimal> accountBalanceMap = new HashMap<String, BigDecimal>();
   EnumMap<Banknote,Integer> atmCashMap = new EnumMap<>(Banknote.class);

    public BankingSystemImpl() {
        atmCashMap.put(Banknote.FIFTY_JOD,10);
        atmCashMap.put(Banknote.TWENTY_JOD,20);
        atmCashMap.put(Banknote.TEN_JOD,100);
        atmCashMap.put(Banknote.FIVE_JOD,100);

        accountBalanceMap.put("123456789", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("111111111", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("222222222", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("333333333", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("444444444", BigDecimal.valueOf(1000.0));
    }

    public List<Banknote> withdrawBanknotes(BigDecimal amount) {
        List<Banknote> withdrawnBanknotes = new ArrayList<>();

        for (Banknote banknote : Banknote.values()) {
            int availableBanknotes = atmCashMap.getOrDefault(banknote, 0);
            BigDecimal banknoteValue = banknote.getValue();

            while (availableBanknotes > 0 && amount.compareTo(banknoteValue) >= 0) {
                withdrawnBanknotes.add(banknote);
                atmCashMap.put(banknote, availableBanknotes - 1);
                amount = amount.subtract(banknoteValue);
                availableBanknotes--;
            }

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }

        return withdrawnBanknotes;
    }
    public BigDecimal sumOfMoneyInAtm(){
        // Your code
        BigDecimal total = BigDecimal.ZERO;
        for (Banknote banknote : atmCashMap.keySet()){
            total = total.add(banknote.getValue().multiply(BigDecimal.valueOf(atmCashMap.get(banknote))));
        }
        return total;
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber){
        //your code
        BigDecimal balance = accountBalanceMap.get(accountNumber);
        if (balance == null) {
            throw new AccountNotFoundException("Account Not Found");
        }
        return accountBalanceMap.getOrDefault(accountNumber,BigDecimal.ZERO);
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        //your code
        BigDecimal currentBalance = accountBalanceMap.getOrDefault(accountNumber, BigDecimal.ZERO);
        if (currentBalance.compareTo(amount) >= 0){
            accountBalanceMap.put(accountNumber, currentBalance.subtract(amount));
        }else {
            throw new InsufficientFundsException("Insufficient fund in the account.");
        }

    }
}
