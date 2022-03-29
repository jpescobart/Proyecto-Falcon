package co.avista.models.services.falcon.RemainingMassiveDisbursement;

import co.avista.models.services.falcon.originationaccount.Disbursement;

public class ClientAccountResponseMasiveDisburment {
    private String accountNumber;
    private Disbursement disbursement;

    public Disbursement getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
