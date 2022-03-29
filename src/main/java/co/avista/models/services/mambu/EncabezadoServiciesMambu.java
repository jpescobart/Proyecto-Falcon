package co.avista.models.services.mambu;

import co.avista.models.services.allies.proteccion.proteconsultaservice.ProteccionServiceResponse;
import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class EncabezadoServiciesMambu {

    private String encodedKey;
    private String accountHolderType;
    private String accountHolderKey;
    private String creationDate;
    private String approvedDate;
    private String lastModifiedDate;
    private String activationTransactionKey;
    private String lastAccountAppraisalDate;
    private String accountState;

    public static EncabezadoServiciesMambu generateMambuServiceModelFromJson(String json) throws JsonProcessingException {
        return JsonUtils.getObjectMapper().readValue(json, EncabezadoServiciesMambu.class);
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }

    public String getAccountHolderType() {
        return accountHolderType;
    }

    public void setAccountHolderType(String accountHolderType) {
        this.accountHolderType = accountHolderType;
    }

    public String getAccountHolderKey() {
        return accountHolderKey;
    }

    public void setAccountHolderKey(String accountHolderKey) {
        this.accountHolderKey = accountHolderKey;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getActivationTransactionKey() {
        return activationTransactionKey;
    }

    public void setActivationTransactionKey(String activationTransactionKey) {
        this.activationTransactionKey = activationTransactionKey;
    }

    public String getLastAccountAppraisalDate() {
        return lastAccountAppraisalDate;
    }

    public void setLastAccountAppraisalDate(String lastAccountAppraisalDate) {
        this.lastAccountAppraisalDate = lastAccountAppraisalDate;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }
}
