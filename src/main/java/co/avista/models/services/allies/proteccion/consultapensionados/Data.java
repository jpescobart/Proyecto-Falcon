package co.avista.models.services.allies.proteccion.consultapensionados;

public class Data {
    private String allowanceValue;

    private String riskType;

    private String totalDiscountValue;

    private String pensionGenerationDate;

    private String pensionYear;

    private String discountDescription;

    private String customerName;

    public String getAllowanceValue ()
    {
        return allowanceValue;
    }

    public void setAllowanceValue (String allowanceValue)
    {
        this.allowanceValue = allowanceValue;
    }

    public String getRiskType ()
    {
        return riskType;
    }

    public void setRiskType (String riskType)
    {
        this.riskType = riskType;
    }

    public String getTotalDiscountValue ()
    {
        return totalDiscountValue;
    }

    public void setTotalDiscountValue (String totalDiscountValue)
    {
        this.totalDiscountValue = totalDiscountValue;
    }

    public String getPensionGenerationDate ()
    {
        return pensionGenerationDate;
    }

    public void setPensionGenerationDate (String pensionGenerationDate)
    {
        this.pensionGenerationDate = pensionGenerationDate;
    }

    public String getPensionYear ()
    {
        return pensionYear;
    }

    public void setPensionYear (String pensionYear)
    {
        this.pensionYear = pensionYear;
    }

    public String getDiscountDescription ()
    {
        return discountDescription;
    }

    public void setDiscountDescription (String discountDescription)
    {
        this.discountDescription = discountDescription;
    }

    public String getCustomerName ()
    {
        return customerName;
    }

    public void setCustomerName (String customerName)
    {
        this.customerName = customerName;
    }
}
