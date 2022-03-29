package co.avista.models.services.falcon.clientaccount;

public class Payer {
    private String payerCode;
    private String payerName;
    private String customerPayrollId;

    public String getPayerCode() {
        return payerCode;
    }

    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getCustomerPayrollId() {
        return customerPayrollId;
    }

    public void setCustomerPayrollId(String customerPayrollId) {
        this.customerPayrollId = customerPayrollId;
    }
}
