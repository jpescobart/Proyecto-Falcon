package co.avista.models.services.falcon.ClientAccountCarteraVendida;

import co.avista.models.services.falcon.originationaccount.Fees;

import java.util.List;

public class ClientAccountCarteraVendidaDia {

    private String salesType;
    private String company;
    private String saleDate;
    private List<SaleDetails> saleDetails;

    public List<SaleDetails> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails = saleDetails;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }


}
