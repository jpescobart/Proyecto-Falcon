package co.avista.models.services.falcon.originationaccount;

import java.util.List;

public class Disbursement {

    private String type;
    private int amount;
    private int number;
    private String expectedDate;
    private boolean lastPortfolioPurchase;
    private int remainderAmount;
    private int accountingAccount;
    private List<Fees> Fees;
    private String channel;

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public boolean isLastPortfolioPurchase() {
        return lastPortfolioPurchase;
    }

    public void setLastPortfolioPurchase(boolean lastPortfolioPurchase) {
        this.lastPortfolioPurchase = lastPortfolioPurchase;
    }

    public int getRemainderAmount() {
        return remainderAmount;
    }

    public void setRemainderAmount(int remainderAmount) {
        this.remainderAmount = remainderAmount;
    }

    public int getAccountingAccount() {
        return accountingAccount;
    }

    public void setAccountingAccount(int accountingAccount) {
        this.accountingAccount = accountingAccount;
    }

    public List<Fees> getFees() {
        return Fees;
    }

    public void setFees(List<Fees> fees) {
        this.Fees = fees;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}