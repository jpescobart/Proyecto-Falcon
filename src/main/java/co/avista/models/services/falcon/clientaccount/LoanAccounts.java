package co.avista.models.services.falcon.clientaccount;

public class LoanAccounts {
    private String creditNumber;
    private int installment;
    private int accountBalance;
    private int totalInstallments;
    private int pendingInstallments;
    private Payer payer;

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(int totalInstallments) {
        this.totalInstallments = totalInstallments;
    }

    public int getPendingInstallments() {
        return pendingInstallments;
    }

    public void setPendingInstallments(int pendingInstallments) {
        this.pendingInstallments = pendingInstallments;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}
