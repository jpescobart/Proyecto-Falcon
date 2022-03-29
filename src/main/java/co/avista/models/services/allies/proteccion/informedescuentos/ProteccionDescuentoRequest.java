package co.avista.models.services.allies.proteccion.informedescuentos;

public class ProteccionDescuentoRequest {
    private int allowanceValue;

    private String discountPeriod;

    private String finalDiscountPeriod;

    private String caseID;

    private String observations;

    private Auditing auditing;

    private PensionaryDocument pensionaryDocument;

    private BeneficiaryDocument beneficiaryDocument;

    public BeneficiaryDocument getBeneficiaryDocument() {
        return beneficiaryDocument;
    }

    public void setBeneficiaryDocument(BeneficiaryDocument beneficiaryDocument) {
        this.beneficiaryDocument = beneficiaryDocument;
    }

    public PensionaryDocument getPensionaryDocument() {
        return pensionaryDocument;
    }

    public void setPensionaryDocument(PensionaryDocument pensionaryDocument) {
        this.pensionaryDocument = pensionaryDocument;
    }

    public String getFinalDiscountPeriod() {
        return finalDiscountPeriod;
    }

    public void setFinalDiscountPeriod(String finalDiscountPeriod) {
        this.finalDiscountPeriod = finalDiscountPeriod;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getAllowanceValue ()
    {
        return allowanceValue;
    }

    public void setAllowanceValue (int allowanceValue)
    {
        this.allowanceValue = allowanceValue;
    }

    public String getDiscountPeriod ()
    {
        return discountPeriod;
    }

    public void setDiscountPeriod (String discountPeriod)
    {
        this.discountPeriod = discountPeriod;
    }

    public Auditing getAuditing ()
    {
        return auditing;
    }

    public void setAuditing (Auditing auditing)
    {
        this.auditing = auditing;
    }
}
