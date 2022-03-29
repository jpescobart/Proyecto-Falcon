package co.avista.models.services.falcon.ClientAccountCarteraVendida;

public class SaleDetails {

    private String accountNumber;
    private int numberOfInstallmentsSold;
    private int balanceSold;
    private int currentInstallment;
    private int firstSalesInstallment;
    private int numberOfAccountInstallments;
    private String saleSettlementDate;
    private String dateOfFirstPaymentInstallment;
    private double salesRate;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getNumberOfInstallmentsSold() {
        return numberOfInstallmentsSold;
    }

    public void setNumberOfInstallmentsSold(int numberOfInstallmentsSold) {
        this.numberOfInstallmentsSold = numberOfInstallmentsSold;
    }

    public int getBalanceSold() {
        return balanceSold;
    }

    public void setBalanceSold(int balanceSold) {
        this.balanceSold = balanceSold;
    }

    public int getCurrentInstallment() {
        return currentInstallment;
    }

    public void setCurrentInstallment(int currentInstallment) {
        this.currentInstallment = currentInstallment;
    }

    public int getFirstSalesInstallment() {
        return firstSalesInstallment;
    }

    public void setFirstSalesInstallment(int firstSalesInstallment) {
        this.firstSalesInstallment = firstSalesInstallment;
    }

    public int getNumberOfAccountInstallments() {
        return numberOfAccountInstallments;
    }

    public void setNumberOfAccountInstallments(int numberOfAccountInstallments) {
        this.numberOfAccountInstallments = numberOfAccountInstallments;
    }

    public String getSaleSettlementDate() {
        return saleSettlementDate;
    }

    public void setSaleSettlementDate(String saleSettlementDate) {
        this.saleSettlementDate = saleSettlementDate;
    }

    public String getDateOfFirstPaymentInstallment() {
        return dateOfFirstPaymentInstallment;
    }

    public void setDateOfFirstPaymentInstallment(String dateOfFirstPaymentInstallment) {
        this.dateOfFirstPaymentInstallment = dateOfFirstPaymentInstallment;
    }

    public double getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(double salesRate) {
        this.salesRate = salesRate;
    }
}