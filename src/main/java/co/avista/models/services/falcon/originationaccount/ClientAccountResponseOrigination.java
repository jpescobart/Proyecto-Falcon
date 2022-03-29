package co.avista.models.services.falcon.originationaccount;


import java.util.Date;

public class ClientAccountResponseOrigination {

    private int loanAmount;
    private String productType;
    private String accountNumber;
    private int gracePeriod;
    private int totalInstallments;
    private int fixedDaysOfMonth;
    private double interestRate;
    private String firstExpirationDate;
    private String branchCode;


    private CommercialAdvisor commercialAdvisor;
    private Customer customer;
    private Disbursement disbursement;
    private PayrollPayer payrollPayer;


    public String getFirstExpirationDate() {
        return firstExpirationDate;
    }

    public void setFirstExpirationDate(String firstExpirationDate) {
        this.firstExpirationDate = firstExpirationDate;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(int totalInstallments) {
        this.totalInstallments = totalInstallments;
    }

    public int getFixedDaysOfMonth() {
        return fixedDaysOfMonth;
    }

    public void setFixedDaysOfMonth(int fixedDaysOfMonth) {
        this.fixedDaysOfMonth = fixedDaysOfMonth;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }



    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public CommercialAdvisor getCommercialAdvisor() {
        return commercialAdvisor;
    }

    public void setCommercialAdvisor(CommercialAdvisor commercialAdvisor) {
        this.commercialAdvisor = commercialAdvisor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Disbursement getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
    }

    public PayrollPayer getPayrollPayer() {
        return payrollPayer;
    }

    public void setPayrollPayer(PayrollPayer payrollPayer) {
        this.payrollPayer = payrollPayer;
    }
}