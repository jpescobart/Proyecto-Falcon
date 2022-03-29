package co.avista.definitions;

import co.avista.asser.AsserFalcon;
import co.avista.basededatos.ConexcionDdLegado;
import co.avista.basededatos.ConexionBdFalcon;
import co.avista.exceptions.ServiceException;
import co.avista.models.services.couchBase.AutenticacionCouchBAse;
import co.avista.models.services.couchBase.GenerarCreditoCarteraVendida;
import co.avista.models.services.falcon.ClientAccountCarteraVendida.ClientAccountCarteraVendidaDia;
import co.avista.models.services.falcon.ClientAccountCarteraVendida.SaleDetails;
import co.avista.models.services.falcon.RemainingMassiveDisbursement.ClientAccountResponseMasiveDisburment;
import co.avista.models.services.falcon.clientaccount.ClientAccountResponse;
import co.avista.models.services.falcon.clientaccount.Data;
import co.avista.models.services.falcon.clientaccount.LoanAccounts;
import co.avista.models.services.falcon.clientaccount.Payer;
import co.avista.models.services.falcon.originationaccount.*;
import co.avista.models.services.legado.ObtenerRemanenteDesembolsoMasivoLegado;
import co.avista.models.services.mambu.EncabezadoServiciesMambu;
import co.avista.services.CouchBase.ServiceCouchBase;
import co.avista.services.CouchBase.ServiceToken;
import co.avista.services.falcon.ServiceDesembolsoMasivoRemanente;
import co.avista.services.falcon.ServiceOriginationFalcon;
import co.avista.services.falcon.ServicesCarteraVendida;
import co.avista.services.mambu.ServicesAccountClients;
import co.avista.services.mambu.ServicesCalendarAccount;
import co.avista.services.mambu.ServicesConsultClientsMambu;
import co.avista.services.origination.ClientAccountsService;
import co.avista.utils.AssertionsManager;
import co.avista.utils.GeneralUtilities;
import co.avista.utils.JsonUtils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.*;
import cucumber.runtime.junit.Assertions;
import org.junit.Assert;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OriginationDefinition {
    private ClientAccountsService clientAccountsService;
    private ClientAccountResponse clientAccountResponse;
    private ClientAccountResponse clientAccountModel = new ClientAccountResponse();
    private String clientDocType, clientDocNum;
    private int responseStatus = 0;
    String idAccountclient;
    String numCanal;
    String resultHoldenKey = null;
    String tokeCouchBase = null;
    String estatus;
    String request;
    private EncabezadoServiciesMambu mambuClient = null;
    String obtenerCadenaBase64;
    List<String> listAccountTxt;
    List<String> listAccountPagoCoutaTxt;
    List<String> listAccountInteresesDiarosTxt;
    List<String> listAccountInteresesCartetaVendidaTxt;
    String numAleatoriaccount = GeneralUtilities.GenerateNumAletori();
    List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerRemanenteDesembolsoMasivoLegadoListfinal= new ArrayList<>();
    List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerRCortoyLargoPlazoLegadosList= new ArrayList<>();
    List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerInteresesdiariosLegadosList= new ArrayList<>();
    List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerInteresesdiariosVendidosLegadosList= new ArrayList<>();
    List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerEntradasContablesList= new ArrayList<>();
    String nombreArchivoPgoCouta;
    String nombreArchivoAccount;
    String carteraPropia;
    String carteraVendida;
    String carteraPropiaMora;
    String interesdiariosPropios;
    String interesDiariosVendidos;
    private ServiceToken serviceTokenLoguin;



    private static final String MSJ_ERROR_CONSUME = "se presento un problema consumiendo el servicio: %s";
    private static final String MSJ_RESPONSE_STATUS = "response status";


    public void setClientID(String clientDocType, String clientDocNum) {
        this.clientDocType = clientDocType;
        this.clientDocNum = clientDocNum;
    }

    public void consumeClientAccountService() {
        try {
            clientAccountsService = new ClientAccountsService(clientDocType, clientDocNum);
            clientAccountsService.buildService();
            clientAccountsService.consumeService();
            clientAccountResponse = ClientAccountResponse.generateClientCreditResponseFromJson(clientAccountsService.getResponse());
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void setClientData(String codigoPagaduria, String nombrePagaduria, String payrollID,
                              String numeroCredito) {
        try {
            Payer payer = new Payer();
            payer.setPayerCode(codigoPagaduria);
            payer.setPayerName(nombrePagaduria);
            payer.setCustomerPayrollId(payrollID);

            LoanAccounts loanAccounts = new LoanAccounts();
            loanAccounts.setCreditNumber(numeroCredito);
            loanAccounts.setPayer(payer);

            Data data = new Data();
            data.addAccount(loanAccounts);

            clientAccountModel.setData(data);

        } catch (Exception e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void addCredit(String numeroCredito, String codigoPagaduria, String nombrePagaduria, String payrollID) {
        try {
            Payer payer = new Payer();
            payer.setPayerCode(codigoPagaduria);
            payer.setPayerName(nombrePagaduria);
            payer.setCustomerPayrollId(payrollID);

            LoanAccounts loanAccounts = new LoanAccounts();
            loanAccounts.setCreditNumber(numeroCredito);
            loanAccounts.setPayer(payer);

            clientAccountModel.getData().addAccount(loanAccounts);

        } catch (Exception e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void setUnfindedClientData() {
        Data data = new Data();
        clientAccountModel.setData(data);
    }


    public void verifySucessClientAccountResponse() {
        try {
            AssertionsManager.hardAssertEqual(200, clientAccountsService.getStatus(), "response status");
            clientAccountResponse = ClientAccountResponse.generateClientCreditResponseFromJson(clientAccountsService.getResponse());
            compareClientAccountModels(clientAccountModel, clientAccountResponse);
            AssertionsManager.assertAll();
        } catch (IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }


    public void verifyNotFoundClientResponse() {
        try {
            AssertionsManager.hardAssertEqual(200, clientAccountsService.getStatus(), "response status");
            clientAccountResponse = ClientAccountResponse.generateClientCreditResponseFromJson(clientAccountsService.getResponse());
            AssertionsManager.softAssertIsNull(clientAccountResponse.getData().getAccounts(), "accounts");
            AssertionsManager.assertAll();
        } catch (IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    private void compareClientAccountModels(ClientAccountResponse expectedClient, ClientAccountResponse obtainedClient) {
        compareLoanAccountModels(expectedClient.getData().getAccounts(), obtainedClient.getData().getAccounts());
    }

    private void compareLoanAccountModels(List<LoanAccounts> expectedLoan, List<LoanAccounts> obtainedLoan) {
        AssertionsManager.softAssertEqual(expectedLoan.size(), obtainedLoan.size(), "Loan size");
        for (int j = 0; j < expectedLoan.size(); j++) {
            for (int i = 0; i < obtainedLoan.size(); i++) {
                if (expectedLoan.get(j).getCreditNumber().equals(obtainedLoan.get(i).getCreditNumber())) {
                    comparePayer(expectedLoan.get(j).getPayer(), obtainedLoan.get(i).getPayer());
                    obtainedLoan.remove(i);
                    expectedLoan.remove(j);
                }
            }

        }
        AssertionsManager.softAssertEqual(expectedLoan.size(), obtainedLoan.size(), "All loans compared");
    }

    private void comparePayer(Payer expectedPayer, Payer obtainedPayer) {
        AssertionsManager.softAssertEqual(expectedPayer.getCustomerPayrollId(), obtainedPayer.getCustomerPayrollId(), "customerPayrollId");
        AssertionsManager.softAssertEqual(expectedPayer.getPayerCode(), obtainedPayer.getPayerCode(), "payerCode");
        AssertionsManager.softAssertEqual(expectedPayer.getPayerName(), obtainedPayer.getPayerName(), "payerName");


    }


    public void verifyServiceStatus(int status) {
        AssertionsManager.hardAssertEqual(status, clientAccountsService.getStatus(), "response status");
    }

//----------------------------------------

    private String generateRequestOriginationServices(String tipoDoc, String typeDesembolso,Boolean lastPortfolioPurchase,int remainderAmount) throws JsonProcessingException {
        ClientAccountResponseOrigination clientAccountResponseOrigination = new ClientAccountResponseOrigination();
        Customer customer = new Customer();
        CommercialAdvisor commercialAdvisor = new CommercialAdvisor();
        Disbursement disbursement = new Disbursement();
        PayrollPayer payrollPayer = new PayrollPayer();

        customer.setDocumentId(numAleatoriaccount);
        customer.setDocumentType(tipoDoc);
        customer.setFirstName(GeneralUtilities.GenerateNameRandom());
        customer.setMiddleName(GeneralUtilities.GenerateLastNameRandom());
        customer.setFirstSurname(GeneralUtilities.GenerateSurnameRandom());
        customer.setSecondSurname(GeneralUtilities.GenerateSurnameRandom());
        customer.setMobilePhone("3148518037");
        customer.setEmailAddress("juan.escobar@avista.co");
        customer.setBirthDate("1990-05-27");
        customer.setBankCode("7564758");
        customer.setAccountType("Ahorro");
        customer.setAccountNumber("986947757");
        clientAccountResponseOrigination.setCustomer(customer);

        clientAccountResponseOrigination.setLoanAmount(1000000);
        clientAccountResponseOrigination.setProductType("123");
        clientAccountResponseOrigination.setAccountNumber(numAleatoriaccount);

        payrollPayer.setCode("001");
        payrollPayer.setName("Colpensiones");
        clientAccountResponseOrigination.setPayrollPayer(payrollPayer);

        commercialAdvisor.setCode("001");
        commercialAdvisor.setName("jhonny sanchez");
        commercialAdvisor.setEmail("jhony.sanchez@hotmail.com");
        clientAccountResponseOrigination.setCommercialAdvisor(commercialAdvisor);

        clientAccountResponseOrigination.setGracePeriod(0);
        clientAccountResponseOrigination.setTotalInstallments(120);
        clientAccountResponseOrigination.setFixedDaysOfMonth(10);
        clientAccountResponseOrigination.setInterestRate(0.017);
        clientAccountResponseOrigination.setFirstExpirationDate("2021-06-10T08:37:50-05:00");
        clientAccountResponseOrigination.setBranchCode("456");


        disbursement.setType(typeDesembolso);
        disbursement.setAmount(3000000);
        disbursement.setNumber(1);
        disbursement.setExpectedDate("2021-05-10T08:37:50-05:00");
        disbursement.setLastPortfolioPurchase(lastPortfolioPurchase);
        disbursement.setRemainderAmount(remainderAmount);
        disbursement.setAccountingAccount(65748);


        List<Fees> Fees = new ArrayList<>();
        Fees fees = new Fees();

        fees.setCode("SEGINI");
        fees.setAmount(0);
        fees.setPercentage(0.04);
        Fees.add(0, fees);

        Fees fees1 = new Fees();
        fees1.setCode("FONDOG");
        fees1.setAmount(0);
        fees1.setPercentage(0.025);
        Fees.add(1, fees1);

        disbursement.setFees(Fees);

        disbursement.setChannel("1");
        clientAccountResponseOrigination.setDisbursement(disbursement);

        return JsonUtils.jsonSerializer(clientAccountResponseOrigination);
    }

    private String generateRequestOriginationServicesEnMora(String tipoDoc, String typeDesembolso) throws JsonProcessingException {
        GeneralUtilities generalUtilities= new GeneralUtilities();
        String fechaMesAnteriorActualdos = generalUtilities.previousMonthsTwo();
        String fechaMesAnteriorActualuno = generalUtilities.previousMonthsOne();
        ClientAccountResponseOrigination clientAccountResponseOrigination = new ClientAccountResponseOrigination();
        Customer customer = new Customer();
        CommercialAdvisor commercialAdvisor = new CommercialAdvisor();
        Disbursement disbursement = new Disbursement();
        PayrollPayer payrollPayer = new PayrollPayer();

        customer.setDocumentId(numAleatoriaccount);
        customer.setDocumentType(tipoDoc);
        customer.setFirstName(GeneralUtilities.GenerateNameRandom());
        customer.setMiddleName(GeneralUtilities.GenerateLastNameRandom());
        customer.setFirstSurname(GeneralUtilities.GenerateSurnameRandom());
        customer.setSecondSurname(GeneralUtilities.GenerateSurnameRandom());
        customer.setMobilePhone("3148518037");
        customer.setEmailAddress("juan.escobar@avista.co");
        customer.setBirthDate("1990-05-27");
        customer.setBankCode("7564758");
        customer.setAccountType("Ahorro");
        customer.setAccountNumber("986947757");
        clientAccountResponseOrigination.setCustomer(customer);

        clientAccountResponseOrigination.setLoanAmount(1000000);
        clientAccountResponseOrigination.setProductType("123");
        clientAccountResponseOrigination.setAccountNumber(numAleatoriaccount);

        payrollPayer.setCode("001");
        payrollPayer.setName("Colpensiones");
        clientAccountResponseOrigination.setPayrollPayer(payrollPayer);

        commercialAdvisor.setCode("001");
        commercialAdvisor.setName("jhonny sanchez");
        commercialAdvisor.setEmail("jhony.sanchez@hotmail.com");
        clientAccountResponseOrigination.setCommercialAdvisor(commercialAdvisor);

        clientAccountResponseOrigination.setGracePeriod(0);
        clientAccountResponseOrigination.setTotalInstallments(120);
        clientAccountResponseOrigination.setFixedDaysOfMonth(10);
        clientAccountResponseOrigination.setInterestRate(0.017);
        clientAccountResponseOrigination.setFirstExpirationDate(fechaMesAnteriorActualuno+"-10T08:00:50-05:00");
        clientAccountResponseOrigination.setBranchCode("456");


        disbursement.setType(typeDesembolso);
        disbursement.setAmount(3000000);
        disbursement.setNumber(1);
        disbursement.setExpectedDate(fechaMesAnteriorActualdos+"-10T08:00:50-05:00");
        disbursement.setLastPortfolioPurchase(false);
        disbursement.setRemainderAmount(0);
        disbursement.setAccountingAccount(65748);


        List<Fees> Fees = new ArrayList<>();
        Fees fees = new Fees();

        fees.setCode("SEGINI");
        fees.setAmount(0);
        fees.setPercentage(0.04);
        Fees.add(0, fees);

        Fees fees1 = new Fees();
        fees1.setCode("FONDOG");
        fees1.setAmount(0);
        fees1.setPercentage(0.025);
        Fees.add(1, fees1);

        disbursement.setFees(Fees);

        disbursement.setChannel("1");
        clientAccountResponseOrigination.setDisbursement(disbursement);

        return JsonUtils.jsonSerializer(clientAccountResponseOrigination);
    }


    public  String  generateRequestDesembolsoMasivoServices(int remainderAmount) throws JsonProcessingException {
        ClientAccountResponseMasiveDisburment clientAccountResponseMasiveDisburment= new ClientAccountResponseMasiveDisburment();
        Disbursement disbursement = new Disbursement();

        clientAccountResponseMasiveDisburment.setAccountNumber(numAleatoriaccount);
        disbursement.setType("R");
        disbursement.setAmount(remainderAmount);
        disbursement.setNumber(2);
        disbursement.setExpectedDate("2021-05-10T13:37:50-05:00");
        disbursement.setLastPortfolioPurchase(false);
        disbursement.setRemainderAmount(0);
        disbursement.setAccountingAccount(65748);


        List<Fees> Fees = new ArrayList<>();
        Fees fees = new Fees();

        fees.setCode("SEGINI");
        fees.setAmount(0);
        fees.setPercentage(0.04);
        Fees.add(0, fees);

        Fees fees1 = new Fees();
        fees1.setCode("FONDOG");
        fees1.setAmount(0);
        fees1.setPercentage(0.025);
        Fees.add(1, fees1);

        disbursement.setFees(Fees);

        disbursement.setChannel("2");
        clientAccountResponseMasiveDisburment.setDisbursement(disbursement);

        List<ClientAccountResponseMasiveDisburment> clientAccountResponseMasiveDisburmentList = new ArrayList<>();
        clientAccountResponseMasiveDisburmentList.add(clientAccountResponseMasiveDisburment);
        return JsonUtils.jsonSerializer(clientAccountResponseMasiveDisburmentList);
    }


    private String generateRequestOriginationServicesPagoSobreLaCouta(String tipoDoc) throws JsonProcessingException {
        ClientAccountResponseOrigination clientAccountResponseOrigination = new ClientAccountResponseOrigination();
        Customer customer = new Customer();
        CommercialAdvisor commercialAdvisor = new CommercialAdvisor();
        Disbursement disbursement = new Disbursement();
        PayrollPayer payrollPayer = new PayrollPayer();

        customer.setDocumentId(numAleatoriaccount);
        customer.setDocumentType(tipoDoc);
        customer.setFirstName("QA-" + GeneralUtilities.GenerateNameRandom());
        customer.setMiddleName(GeneralUtilities.GenerateLastNameRandom());
        customer.setFirstSurname(GeneralUtilities.GenerateSurnameRandom());
        customer.setSecondSurname(GeneralUtilities.GenerateSurnameRandom());
        customer.setMobilePhone("3148518037");
        customer.setEmailAddress("juan.escobar@avista.co");
        customer.setBirthDate("1990-05-27");
        customer.setBankCode("7564758");
        customer.setAccountType("Ahorro");
        customer.setAccountNumber("986947757");
        clientAccountResponseOrigination.setCustomer(customer);

        clientAccountResponseOrigination.setLoanAmount(1000000);
        clientAccountResponseOrigination.setProductType("123");
        clientAccountResponseOrigination.setAccountNumber(numAleatoriaccount);

        payrollPayer.setCode("001");
        payrollPayer.setName("Colpensiones");
        clientAccountResponseOrigination.setPayrollPayer(payrollPayer);

        commercialAdvisor.setCode("001");
        commercialAdvisor.setName("jhonny sanchez");
        commercialAdvisor.setEmail("jhony.sanchez@hotmail.com");
        clientAccountResponseOrigination.setCommercialAdvisor(commercialAdvisor);

        clientAccountResponseOrigination.setGracePeriod(0);
        clientAccountResponseOrigination.setTotalInstallments(120);
        clientAccountResponseOrigination.setFixedDaysOfMonth(10);
        clientAccountResponseOrigination.setInterestRate(0.017);
        clientAccountResponseOrigination.setFirstExpirationDate("2021-06-10T13:37:50-05:00");
        clientAccountResponseOrigination.setBranchCode("456");


        disbursement.setType("CC");
        disbursement.setAmount(4000000);
        disbursement.setNumber(1);
        disbursement.setExpectedDate("2021-05-10T13:37:50-05:00");
        disbursement.setLastPortfolioPurchase(false);
        disbursement.setRemainderAmount(0);
        disbursement.setAccountingAccount(65748);


        List<Fees> Fees = new ArrayList<>();
        Fees fees = new Fees();

        fees.setCode("SEGINI");
        fees.setAmount(0);
        fees.setPercentage(0.04);
        Fees.add(0, fees);

        Fees fees1 = new Fees();
        fees1.setCode("FONDOG");
        fees1.setAmount(0);
        fees1.setPercentage(0.025);
        Fees.add(1, fees1);

        Fees fees2 = new Fees();
        fees2.setCode("INS");
        fees2.setAmount(100000);
        fees2.setPercentage(0);
        Fees.add(1, fees2);

        disbursement.setFees(Fees);

        disbursement.setChannel("1");
        clientAccountResponseOrigination.setDisbursement(disbursement);

        return JsonUtils.jsonSerializer(clientAccountResponseOrigination);
    }

    private String generateRequestMarcarCarteraVendidaCuenta() throws JsonProcessingException{
        ClientAccountCarteraVendidaDia clientAccountCarteraVendidaDia = new ClientAccountCarteraVendidaDia();
        SaleDetails saleDetails = new SaleDetails();

        clientAccountCarteraVendidaDia.setSalesType("VENTA EN FIRME");
        clientAccountCarteraVendidaDia.setCompany("Pruebas QA");
        clientAccountCarteraVendidaDia.setSaleDate("2021-06-10T13:37:50-05:00");

        List<SaleDetails> listSaleDetails = new ArrayList<>();
        SaleDetails saleDetailsf = new SaleDetails();

        saleDetailsf.setAccountNumber(numAleatoriaccount);
        saleDetailsf.setNumberOfInstallmentsSold(120);
        saleDetailsf.setBalanceSold(12749850);
        saleDetailsf.setCurrentInstallment(241556);
        saleDetailsf.setFirstSalesInstallment(2);
        saleDetailsf.setNumberOfAccountInstallments(120);
        saleDetailsf.setSaleSettlementDate("2021-06-10T13:37:50-05:00");
        saleDetailsf.setDateOfFirstPaymentInstallment("2021-06-10T13:37:50-05:00");
        saleDetailsf.setSalesRate(0.017);
        listSaleDetails.add(0,saleDetailsf);

        clientAccountCarteraVendidaDia.setSaleDetails(listSaleDetails);

        return JsonUtils.jsonSerializer(clientAccountCarteraVendidaDia);
    }


    private String generateRequestCouchBaseLoguin() throws IOException {

        AutenticacionCouchBAse autenticacionCouchBAse = new AutenticacionCouchBAse();
        autenticacionCouchBAse.setUsername("juan.escobar");
        autenticacionCouchBAse.setPassword("MDOX^39USfzy");
        return JsonUtils.jsonSerializer(autenticacionCouchBAse);
    }

    private String actualizarCreditoCarteraVendida() throws IOException {
        GenerarCreditoCarteraVendida generarCreditoCarteraVendida = new GenerarCreditoCarteraVendida();
        generarCreditoCarteraVendida.setId_connection(8);
        generarCreditoCarteraVendida.setQuery("UPDATE `voyager-falcon` set  account.salesType ='Venta en firme' where account.accountNumber in ['"+numAleatoriaccount+"']");
        return JsonUtils.jsonSerializer(generarCreditoCarteraVendida);
    }


    public void postCreateOriginationDesembolso(String tipoDoc, String typeDesembolso,boolean lastPortfolioPurchase,int remainderAmount) {
        try {
            request = generateRequestOriginationServices(tipoDoc, typeDesembolso,lastPortfolioPurchase,remainderAmount);
            ServiceOriginationFalcon serviceOriginationFalcon = new ServiceOriginationFalcon(request);
            serviceOriginationFalcon.buildService();
            serviceOriginationFalcon.consumeService();
            responseStatus = serviceOriginationFalcon.getStatus();
            AssertionsManager.hardAssertEqual(201, serviceOriginationFalcon.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void postCreateOriginationDesembolsoCuentaEnMora(String tipoDoc, String typeDesembolso) {
        try {
            request = generateRequestOriginationServicesEnMora(tipoDoc, typeDesembolso);
            ServiceOriginationFalcon serviceOriginationFalcon = new ServiceOriginationFalcon(request);
            serviceOriginationFalcon.buildService();
            serviceOriginationFalcon.consumeService();
            responseStatus = serviceOriginationFalcon.getStatus();
            AssertionsManager.hardAssertEqual(201, serviceOriginationFalcon.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }




    public void postDesembolsoMasivoRemanenteCliente(int remainderAmount) throws IOException, ServiceException {
        try{

        request=generateRequestDesembolsoMasivoServices(remainderAmount);
        ServiceDesembolsoMasivoRemanente serviceDesembolsoMasivoRemanente= new ServiceDesembolsoMasivoRemanente(request);
        serviceDesembolsoMasivoRemanente.buildService();
        serviceDesembolsoMasivoRemanente.consumeService();
        responseStatus= serviceDesembolsoMasivoRemanente.getStatus();
        AssertionsManager.hardAssertEqual(201,serviceDesembolsoMasivoRemanente.getStatus(),MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void postCreateOriginationPagoSobreCouta(String tipoDoc) {
        try {
            request = generateRequestOriginationServicesPagoSobreLaCouta(tipoDoc);
            ServiceOriginationFalcon serviceOriginationFalcon = new ServiceOriginationFalcon(request);
            serviceOriginationFalcon.buildService();
            serviceOriginationFalcon.consumeService();
            responseStatus = serviceOriginationFalcon.getStatus();
            AssertionsManager.hardAssertEqual(201, serviceOriginationFalcon.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }


    public void postCreateAccountCarteraVendida() {
        try {
            request = generateRequestMarcarCarteraVendidaCuenta();
            ServicesCarteraVendida servicesCarteraVendida= new ServicesCarteraVendida(request);
            servicesCarteraVendida.buildService();
            servicesCarteraVendida.consumeService();
            responseStatus = servicesCarteraVendida.getStatus();
            AssertionsManager.hardAssertEqual(201, servicesCarteraVendida.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void obtenerTokenCouchBase() throws IOException {
        try{
            request= generateRequestCouchBaseLoguin();
            ServiceToken serviceToken = new ServiceToken(request);
            serviceToken.buildService();
            serviceToken.consumeService();
            responseStatus= serviceToken.getStatus();
            JsonElement jelement = new JsonParser().parse(serviceToken.getResponse());
            JsonObject jobject = jelement.getAsJsonObject();
            tokeCouchBase = jobject.get("token").getAsString();
            System.out.println("El resultado es" + tokeCouchBase);
            AssertionsManager.hardAssertEqual(200,serviceToken.getStatus(),MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }

    public void actualizarCarteraVendida() throws IOException {
        try{
            request= actualizarCreditoCarteraVendida();
            ServiceCouchBase serviceCouchBase = new ServiceCouchBase(request,tokeCouchBase);
            serviceCouchBase.buildService();
            serviceCouchBase.consumeService();
            responseStatus= serviceCouchBase.getStatus();
            AssertionsManager.hardAssertEqual(200,serviceCouchBase.getStatus(),MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }



    public  void consultarRemanenteBdLegado(){
        ConexcionDdLegado conexcionDdLegado= new ConexcionDdLegado();
        int numeroIdlegado=  conexcionDdLegado.consultarIdDesemboloMasivoLegado();
        obtenerRemanenteDesembolsoMasivoLegadoListfinal =conexcionDdLegado.consultarMovimientosDesembolsoMasivoLegado(numeroIdlegado);
        conexcionDdLegado.getConnection();
        conexcionDdLegado.desconectar();
    }

    public  void asserDesembolsoMasivoRemanente(){
        AsserFalcon asserFalcon = new AsserFalcon();
        asserFalcon.validarDesembolsoMasivoRemanenteLegado(obtenerRemanenteDesembolsoMasivoLegadoListfinal,numAleatoriaccount);
    }

    public void conexionBaseDeDatosFalconcIdCuenta() {
        ConexionBdFalcon conexionBdFalcon = new ConexionBdFalcon();
        idAccountclient = conexionBdFalcon.conectionAndQueryNumId(numAleatoriaccount);
        System.out.println(idAccountclient);

    }


    public void conexionBaseDeDatosFalconCanal() {
        ConexionBdFalcon conexionBdFalcon = new ConexionBdFalcon();
        conexionBdFalcon.conectionAndQueryCanal(numAleatoriaccount);
        System.out.println(numCanal);

    }

    public void conexionBaseDeDatoscouchBaseCortoyLargoPlazo() {
        ConexionBdFalcon conexionBdFalcon = new ConexionBdFalcon();
        conexionBdFalcon.conectionAndQueryCPyLG(numAleatoriaccount);

    }

    public void generateTxtAccount(){
        nombreArchivoAccount= "fileAccount_";
        GeneralUtilities.generarTxt(numAleatoriaccount,nombreArchivoAccount);
    }

    public void generateTxtAccountPagoSobreLacouta(){
        nombreArchivoPgoCouta="filePagoCouta_";
        GeneralUtilities.generarTxt(numAleatoriaccount,nombreArchivoPgoCouta);
    }

    public void generateTxtCausacionDeIntereses(){
        carteraPropia="fileCausacionInteresesCA_";
        GeneralUtilities.generarTxtCausacionDeIntereses(numAleatoriaccount,carteraPropia);
    }

    public void generateTxtCausacionDeInteresesCarteraVendida(){
        carteraVendida="fileCausacionInteresesCI_";
        GeneralUtilities.generarTxtCausacionDeIntereses(numAleatoriaccount,carteraVendida);
    }

    public void generateTxtCausacionDeInteresesEnMora(){
        carteraPropiaMora="fileCausacionInteresesMoraCA_";
        GeneralUtilities.generarTxtCausacionDeIntereses(numAleatoriaccount,carteraPropiaMora);
    }

    public void generateTxtCausacionDeInteresesCarteraVendidaEnMora(){
        carteraPropiaMora="fileCausacionInteresesMoraCI_";
        GeneralUtilities.generarTxtCausacionDeIntereses(numAleatoriaccount,carteraPropiaMora);
    }

    public void leerTxtAccounts(){
      nombreArchivoAccount= "fileAccount_";
        System.out.println(nombreArchivoAccount);
      listAccountTxt = Arrays.asList(GeneralUtilities.leerDatosAccountTxt(nombreArchivoAccount).split(","));
    }

    public void leerTxtAccountsPagoCouta(){
        nombreArchivoPgoCouta="filePagoCouta_";
        listAccountPagoCoutaTxt = Arrays.asList(GeneralUtilities.leerDatosAccountTxt(nombreArchivoPgoCouta).split(","));
    }

    public void leerTxtAccountInteresesDiarios(){
        String carteraPropiaLecturaTxt="fileCausacionInteresesCA_";
        listAccountInteresesDiarosTxt = Arrays.asList(GeneralUtilities.leerDatosAccountInteresesDiariosTxt(carteraPropiaLecturaTxt).split(","));
    }

    public void leerTxtAccountInteresesDiariosCarteraVendida(){
        String carteraVendidaLecturaTxt="fileCausacionInteresesCI_";
        listAccountInteresesDiarosTxt = Arrays.asList(GeneralUtilities.leerDatosAccountInteresesDiariosTxt(carteraVendidaLecturaTxt).split(","));
    }

    public void leerTxtAccountInteresesDiariosEnMora(){
        String carteraEnMoraLecturaTxt="fileCausacionInteresesMoraCA_";
        listAccountInteresesDiarosTxt = Arrays.asList(GeneralUtilities.leerDatosAccountInteresesDiariosTxt(carteraEnMoraLecturaTxt).split(","));
    }

    public void leerTxtAccountInteresesCarteraVendidaEnMora(){
        String carteraVendidaEnMoraLecturaTxt="fileCausacionInteresesMoraCI_";
        listAccountInteresesCartetaVendidaTxt = Arrays.asList(GeneralUtilities.leerDatosAccountInteresesDiariosTxt(carteraVendidaEnMoraLecturaTxt).split(","));
    }

    public void consultarCalculoCortoyLargoPlazoFalcon(){
        ConexionBdFalcon conexionBdFalcon = new ConexionBdFalcon();
        conexionBdFalcon.obtenerCortoyLargoPlazoCalculado(listAccountTxt);
    }

    public void consultarCortoyLargoPlazoLegado(){
        ConexcionDdLegado conexcionDdLegado= new ConexcionDdLegado();
        System.out.println(listAccountTxt);
        obtenerRCortoyLargoPlazoLegadosList = conexcionDdLegado.consultarMovimientosCortoyLargoPlazoLegado(listAccountTxt);
    }

    public  void asserCortoyLargoPlazoLegadoFalcon(){
        AsserFalcon asserFalcon = new AsserFalcon();
        System.out.println(obtenerRCortoyLargoPlazoLegadosList);
        asserFalcon.validarCortoyLargoPlazoCalculadoLegado(obtenerRCortoyLargoPlazoLegadosList,listAccountTxt);
    }

    public  void asserInteresesDiariosFalcon(){
        AsserFalcon asserFalcon = new AsserFalcon();
        System.out.println(obtenerInteresesdiariosLegadosList);
        asserFalcon.validarInteresDiariosCalculadosLegado(obtenerInteresesdiariosLegadosList,listAccountInteresesDiarosTxt,interesdiariosPropios);
    }
    public  void asserInteresesDiariosEnMoraFalcon(){
        AsserFalcon asserFalcon = new AsserFalcon();
        System.out.println(obtenerInteresesdiariosLegadosList);
        asserFalcon.validarInteresDiariosEnMoraCalculadosLegado(obtenerInteresesdiariosLegadosList,listAccountInteresesDiarosTxt,interesdiariosPropios);
    }

    public  void asserInteresesDiariosEnMoraCarteraVendidaFalcon(){

        AsserFalcon asserFalcon = new AsserFalcon();
        System.out.println(obtenerInteresesdiariosLegadosList);
        System.out.println(listAccountInteresesDiarosTxt);
        System.out.println(interesdiariosPropios);
        asserFalcon.validarInteresDiariosEnMoraCalculadosLegado(obtenerInteresesdiariosVendidosLegadosList,listAccountInteresesCartetaVendidaTxt,interesDiariosVendidos);
    }

    public  void asserInteresesDiariosCarteraVendidaFalcon(){
        AsserFalcon asserFalcon = new AsserFalcon();
        asserFalcon.validarInteresDiariosCalculadosLegado(obtenerInteresesdiariosVendidosLegadosList,listAccountInteresesDiarosTxt,interesDiariosVendidos);
    }

    public  void asserEntradascontablesLegadoFalcon(){
        AsserFalcon asserFalcon = new AsserFalcon();
        asserFalcon.validarEntradasContablesLegado(obtenerEntradasContablesList,numAleatoriaccount);
    }

    public  void conexionBaseDeDatoscouchBasenumeroCuentaEntradasContables(){
        ConexionBdFalcon conexionBdFalcon = new ConexionBdFalcon();
        conexionBdFalcon.conectionAndQueryCreditoCreadoEntradasContables(numAleatoriaccount);

    }

    public  void conexionBaseDeDatoscouchCarteraVendida(){
        ConexionBdFalcon conexionBdFalcon = new ConexionBdFalcon();
        conexionBdFalcon.conectionAndQueryCarteraVendida(numAleatoriaccount);

    }



    public void consultarEntradasContablesLegado() throws InterruptedException {
        ConexcionDdLegado conexcionDdLegado= new ConexcionDdLegado();
        obtenerEntradasContablesList = conexcionDdLegado.consultarMovimientosEntradasContables(numAleatoriaccount);
    }

    public void consultarIntereDiarioLegado(){
        ConexcionDdLegado conexcionDdLegado = new ConexcionDdLegado();
        interesdiariosPropios="CA";
        System.out.println(listAccountInteresesDiarosTxt);
        obtenerInteresesdiariosLegadosList = conexcionDdLegado.consultarCausacionInteresesLegado(listAccountInteresesDiarosTxt,interesdiariosPropios);

    }

    public void consultarIntereDiarioLegadoCarteraVendida(){
        ConexcionDdLegado conexcionDdLegado= new ConexcionDdLegado();
        interesDiariosVendidos="CI";
        System.out.println(listAccountInteresesDiarosTxt);
        obtenerInteresesdiariosVendidosLegadosList = conexcionDdLegado.consultarCausacionInteresesLegado(listAccountInteresesDiarosTxt, interesDiariosVendidos);
    }

    public void consultarIntereDiarioLegadoCarteraVendidaEnMora(){
        ConexcionDdLegado conexcionDdLegado= new ConexcionDdLegado();
        interesDiariosVendidos="CI";
        System.out.println(listAccountInteresesCartetaVendidaTxt);
        obtenerInteresesdiariosVendidosLegadosList = conexcionDdLegado.consultarCausacionInteresesLegado(listAccountInteresesCartetaVendidaTxt, interesDiariosVendidos);
    }



    public String getAccountholderKeyFalcon() {

        try {
            obtenerCadenaBase64 = GeneralUtilities.generateBase64();
            ServicesAccountClients serviceAccountClients = new ServicesAccountClients(idAccountclient,obtenerCadenaBase64);
            serviceAccountClients.buildService();
            serviceAccountClients.consumeService();
            JsonElement jelement = new JsonParser().parse(serviceAccountClients.getResponse());
            JsonObject jobject = jelement.getAsJsonObject();
            resultHoldenKey = jobject.get("accountHolderKey").getAsString();
            System.out.println("El resultado es" + resultHoldenKey);
            AssertionsManager.hardAssertEqual(200, serviceAccountClients.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
        return resultHoldenKey;

    }

    public void getConsultClientsMambu() {

        try {
            ServicesConsultClientsMambu servicesConsultClientsMambu = new ServicesConsultClientsMambu(resultHoldenKey,obtenerCadenaBase64);
            servicesConsultClientsMambu.buildService();
            servicesConsultClientsMambu.consumeService();
            JsonElement jelement = new JsonParser().parse(servicesConsultClientsMambu.getResponse());
            JsonObject jobject = jelement.getAsJsonObject();
            estatus = jobject.get("state").getAsString();
            AssertionsManager.hardAssertEqual(estatus, "ACTIVE", MSJ_ERROR_CONSUME);
            AssertionsManager.hardAssertEqual(200, servicesConsultClientsMambu.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }


    }


    public void getConsultCalendarioMambu() {

        try {
            obtenerCadenaBase64 = GeneralUtilities.generateBase64();
            ServicesCalendarAccount ServicesCalendarAccount = new ServicesCalendarAccount(idAccountclient, obtenerCadenaBase64);
            ServicesCalendarAccount.buildService();
            ServicesCalendarAccount.consumeService();

            JsonElement jelement = new JsonParser().parse(ServicesCalendarAccount.getResponse());
            JsonArray json = ((JsonObject) jelement).get("installments").getAsJsonArray();
                for (int i = 0; i < json.size(); i++) {
                    if (json.get(i).getAsJsonObject().get("state").getAsString().equals("PENDING")) {
                        JsonObject fee = json.get(i).getAsJsonObject().get("fee").getAsJsonObject();
                        JsonObject amount = fee.get("amount").getAsJsonObject();
                        if (amount.get("expected").getAsInt() == 0 && amount.get("due").getAsInt() == 0) {
                            System.out.println("El pago de la primer couta   aun no se registra correctamente");
                            break;
                        }

                    }
                }
            AssertionsManager.hardAssertEqual(200, ServicesCalendarAccount.getStatus(), MSJ_RESPONSE_STATUS);
        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
        }
    }


        public void getConsultCalendarioMambuPagoPrimeraCuota () {

            try {
                obtenerCadenaBase64 = GeneralUtilities.generateBase64();
                ServicesCalendarAccount ServicesCalendarAccount = new ServicesCalendarAccount(idAccountclient, obtenerCadenaBase64);
                ServicesCalendarAccount.buildService();
                ServicesCalendarAccount.consumeService();
                JsonElement jelement = new JsonParser().parse(ServicesCalendarAccount.getResponse());
                JsonArray json = ((JsonObject) jelement).get("installments").getAsJsonArray();
                System.out.println(listAccountPagoCoutaTxt);
                listAccountPagoCoutaTxt  = listAccountPagoCoutaTxt.stream().sorted().collect(Collectors.toList());
                for (String item : listAccountPagoCoutaTxt) {
                    for (int i = 0; i < json.size(); i++) {
                        if (json.get(i).getAsJsonObject().get("state").getAsString().equals("PENDING")) {
                            JsonObject fee = json.get(i).getAsJsonObject().get("fee").getAsJsonObject();
                            JsonObject amount = fee.get("amount").getAsJsonObject();
                            if (amount.get("expected").getAsInt() != 0 && amount.get("due").getAsInt() != 0) {
                                System.out.println("El pago de la primer couta  se genero correctamente");
                                break;
                            }

                        }
                    }
                }
                AssertionsManager.hardAssertEqual(200, ServicesCalendarAccount.getStatus(), MSJ_RESPONSE_STATUS);
            } catch (ServiceException | IOException e) {
                AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME, e.getMessage()));
            }

        }


    }






