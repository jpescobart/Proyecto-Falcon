package co.avista.models.services.falcon.clientaccount;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<LoanAccounts> accounts;

    public List<LoanAccounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<LoanAccounts> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(LoanAccounts loanAccounts){
        if(this.accounts ==null)
            this.accounts =new ArrayList<>();
        this.accounts.add(loanAccounts);
    }
}
