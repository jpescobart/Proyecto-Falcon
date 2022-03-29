package co.avista.definitions;

import co.avista.exceptions.ServiceException;
import co.avista.models.services.allies.proteccion.auth.ProteccionTokenResponse;
import co.avista.models.services.allies.proteccion.consultapensionados.ProteccionConsultaResponse;
import co.avista.models.services.allies.proteccion.error.ErrorResponse;
import co.avista.models.services.allies.proteccion.informedescuentos.Auditing;
import co.avista.models.services.allies.proteccion.informedescuentos.BeneficiaryDocument;
import co.avista.models.services.allies.proteccion.informedescuentos.PensionaryDocument;
import co.avista.models.services.allies.proteccion.informedescuentos.ProteccionDescuentoRequest;
import co.avista.models.services.allies.proteccion.proteconsultaservice.ProteccionServiceResponse;
import co.avista.services.allies.proteccion.*;
import co.avista.utils.AssertionsManager;
import co.avista.utils.GeneralUtilities;
import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

import static co.avista.utils.AssertionsManager.assertAll;

public class AlliesProteccionDefinition {
    private static final String MSJ_ERROR_CONSUME ="se presento un problema consumiendo el servicio: %s";
    private static final String MSJ_RESPONSE_STATUS ="response status";
    private ProteccionServiceResponse proteccionPensioner=null;
    private ProteccionConsultaResponse falconProteccionPensioner=null;
    private ErrorResponse error=null;
    private int responseStatus=0;

    private int discount =0;


    public void getPensionerDataFromProteccion(String docType, String docNum) {
        try {
            String token = getProteccionToken();
            ProteccionConsultaPensionadoService proteccionConsultaPensionadoService = new ProteccionConsultaPensionadoService(docType,docNum,token);
            proteccionConsultaPensionadoService.buildService();
            proteccionConsultaPensionadoService.consumeService();
            proteccionPensioner=ProteccionServiceResponse
                    .generateProteccionServiceModelFromJson(proteccionConsultaPensionadoService.getResponse());
            AssertionsManager.hardAssertEqual(200,proteccionConsultaPensionadoService.getStatus(), MSJ_RESPONSE_STATUS);

        } catch (ServiceException | IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    public void getPensionerDataFromProteccionUsingFalcon(String docType, String docNum) throws InterruptedException {
        Thread.sleep(90000);
        try {
            PensionariesProteccionService pensionariesProteccionService = consumePensionariesService(docType,docNum);
            responseStatus =pensionariesProteccionService.getStatus();
            verifyResponseStatus(200);
            falconProteccionPensioner=ProteccionConsultaResponse
                    .generateProteccionConsultaResponseFromJson(pensionariesProteccionService.getResponse());
        } catch (IOException | ServiceException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    public void getRandomIDPensionerDataFromProteccionUsingFalcon() {
        try {
            PensionariesProteccionService pensionariesProteccionService = consumePensionariesService("CC", String.valueOf(GeneralUtilities.randomNumberGeneratorByLength(9)));
            AssertionsManager.hardAssertEqual(404,pensionariesProteccionService.getStatus(), MSJ_RESPONSE_STATUS);
            error=ErrorResponse.generateErrorResponseFromJson(pensionariesProteccionService.getResponse());
        } catch (IOException | ServiceException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    private PensionariesProteccionService consumePensionariesService(String docType, String docNum) throws ServiceException, IOException {
        PensionariesProteccionService pensionariesProteccionService = new PensionariesProteccionService(docType, docNum);
        pensionariesProteccionService.buildService();
        pensionariesProteccionService.consumeService();
        return pensionariesProteccionService;
    }

    private PensionariesDiscountService consumePensionariesDiscountService(String request) throws ServiceException, IOException {
        PensionariesDiscountService pensionariesDiscountService = new PensionariesDiscountService(request);
        pensionariesDiscountService.buildService();
        pensionariesDiscountService.consumeService();
        return pensionariesDiscountService;
    }

    private BeneficiariDiscountService consumeBeneficiariDiscountService(String request) throws ServiceException, IOException {
        BeneficiariDiscountService beneficiariDiscountService = new BeneficiariDiscountService(request);
        beneficiariDiscountService.buildService();
        beneficiariDiscountService.consumeService();
        return beneficiariDiscountService;
    }


    public void informNonExistentPensionerDiscount() {
        try {
            String numID=String.valueOf(GeneralUtilities.randomNumberGeneratorByLength(11));
            discount =GeneralUtilities.randomNumberGenerator(100000);
            String request=generatePensionarieDiscountRequestCurrentDate("CC",numID,discount);
            PensionariesDiscountService pensionariesDiscountService = consumePensionariesDiscountService(request);
            responseStatus=pensionariesDiscountService.getStatus();
            verifyResponseStatus(404);
            error=ErrorResponse.generateErrorResponseFromJson(pensionariesDiscountService.getResponse());
        } catch (IOException | ServiceException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    public void informNewPensionerDiscount(String docType, String docNum) {
        try {
            int rangoDescuento=Integer.parseInt(proteccionPensioner.getDatosSalida().getValorMesadaPensional())/2-Integer.parseInt(proteccionPensioner.getDatosSalida().getValorTotalDescuentos());
            if(rangoDescuento<0)
                throw new Exception("El cliente no tiene capacidad de descuento");
            discount =GeneralUtilities.randomNumberGenerator((rangoDescuento)/1000);
            String request=generatePensionarieDiscountRequestCurrentDate(docType,docNum,discount);
            PensionariesDiscountService pensionariesDiscountService = consumePensionariesDiscountService(request);
            responseStatus=pensionariesDiscountService.getStatus();
            verifyResponseStatus(201);
            Thread.sleep(90000);
        } catch ( InterruptedException e){
            Thread.currentThread().interrupt();
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        } catch (Exception  e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

//------------------------------------------------------------------------------------------------------------------------------------------

    public void informNewBeneficiariDiscount(String docType, String docNum,String docTypeBeneficiari, String docNumBeneficiario) {
        try {
            int rangoDescuento=Integer.parseInt(proteccionPensioner.getDatosSalida().getValorMesadaPensional())/2-Integer.parseInt(proteccionPensioner.getDatosSalida().getValorTotalDescuentos());
            if(rangoDescuento<0)
                throw new Exception("El cliente no tiene capacidad de descuento");
            discount =GeneralUtilities.randomNumberGenerator((rangoDescuento)/1000);
            String request=generateBeneficiariDiscountRequestCurrentDate(docType,docNum,discount,docTypeBeneficiari,docNumBeneficiario);
            BeneficiariDiscountService beneficiariDiscountService = consumeBeneficiariDiscountService(request);
            responseStatus=beneficiariDiscountService.getStatus();
            verifyResponseStatus(201);
            Thread.sleep(60000);
        } catch ( InterruptedException e){
            Thread.currentThread().interrupt();
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        } catch (Exception  e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }


    private String getProteccionToken() throws ServiceException, IOException {
        ProteccionAuthService proteccionAuthService = new ProteccionAuthService();
        proteccionAuthService.buildService();
        proteccionAuthService.consumeService();
        ProteccionTokenResponse proteccionTokenResponse = ProteccionTokenResponse
                .generateProteccionTokenResponseFromJson( proteccionAuthService.getResponse());
        AssertionsManager.hardAssertEqual(200,proteccionAuthService.getStatus(), MSJ_RESPONSE_STATUS);
        return proteccionTokenResponse.getAccess_token();
    }

    private String generatePensionarieDiscountRequestCurrentDate(String docType, String docNum, int discount) throws JsonProcessingException {
        ProteccionDescuentoRequest proteccionDescuentoRequest = new ProteccionDescuentoRequest();
        PensionaryDocument pensionaryDocument = new PensionaryDocument();
        pensionaryDocument.setDocumentType(docType);
        pensionaryDocument.setDocumentId(docNum);
        proteccionDescuentoRequest.setPensionaryDocument(pensionaryDocument);
        proteccionDescuentoRequest.setFinalDiscountPeriod("102022");
        proteccionDescuentoRequest.setCaseID("ZZZ999");
        proteccionDescuentoRequest.setObservations("Observacion");
        proteccionDescuentoRequest.setAllowanceValue(discount);
        proteccionDescuentoRequest.setDiscountPeriod(GeneralUtilities.generateCurrentDate("MMyyyy"));
        proteccionDescuentoRequest.setAuditing(Auditing.generateWithDefaultData());
        return JsonUtils.jsonSerializer(proteccionDescuentoRequest);
    }



    private String generateBeneficiariDiscountRequestCurrentDate(String docType, String docNum, int discount,String docTypeBeneficiari, String docNumBeneficiari ) throws JsonProcessingException {
        ProteccionDescuentoRequest proteccionDescuentoRequest = new ProteccionDescuentoRequest();
        PensionaryDocument pensionaryDocument = new PensionaryDocument();
        BeneficiaryDocument beneficiaryDocument= new BeneficiaryDocument();
        pensionaryDocument.setDocumentType(docType);
        pensionaryDocument.setDocumentId(docNum);
        proteccionDescuentoRequest.setPensionaryDocument(pensionaryDocument);
        beneficiaryDocument.setDocumentID(docNumBeneficiari);
        beneficiaryDocument.setDocumentType(docTypeBeneficiari);
        proteccionDescuentoRequest.setBeneficiaryDocument(beneficiaryDocument);
        proteccionDescuentoRequest.setFinalDiscountPeriod("102022");
        proteccionDescuentoRequest.setCaseID("ZZZ999");
        proteccionDescuentoRequest.setObservations("Observacion");
        proteccionDescuentoRequest.setAllowanceValue(discount);
        proteccionDescuentoRequest.setDiscountPeriod(GeneralUtilities.generateCurrentDate("MMyyyy"));
        proteccionDescuentoRequest.setAuditing(Auditing.generateWithDefaultData());
        return JsonUtils.jsonSerializer(proteccionDescuentoRequest);
    }

    public void comparePensionersFromProteccionAndFalcon() {
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getNombreCompleto(), falconProteccionPensioner.getData().getCustomerName(), "nombre completo");
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getTipoRiesgo(), falconProteccionPensioner.getData().getRiskType(), "tipo de riesgo");
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getFechaGeneracionPension(), falconProteccionPensioner.getData().getPensionGenerationDate(), "fecha de pension");
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getAnoPension(), falconProteccionPensioner.getData().getPensionYear(), "anio de pension");
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getValorMesadaPensional(), falconProteccionPensioner.getData().getAllowanceValue(), "valor mesada pensional");
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getValorTotalDescuentos(), falconProteccionPensioner.getData().getTotalDiscountValue(), "total descuentos");
        AssertionsManager.softAssertEqual(proteccionPensioner.getDatosSalida().getDescripcionDescuentos(), falconProteccionPensioner.getData().getDiscountDescription(), "descripcion descuentos");
        assertAll();
    }

    public void verifyPensionersWithoutDiscounts() {
        AssertionsManager.softAssertEqual("0", falconProteccionPensioner.getData().getTotalDiscountValue(), "descuentos en 0$");
        AssertionsManager.softAssertEqual("", falconProteccionPensioner.getData().getDiscountDescription(), "descripcion de descuentos en blanco");
        assertAll();
    }

    public void verifyPensionerType(String pensionType) {
        AssertionsManager.hardAssertEqual(pensionType, falconProteccionPensioner.getData().getRiskType(), "tipo de pension");
    }

    public void verifyDiscountAddedToPensioner() {
        discount=discount+Integer.parseInt(proteccionPensioner.getDatosSalida().getValorTotalDescuentos());
        AssertionsManager.softAssertEqual(discount, Integer.parseInt(falconProteccionPensioner.getData().getTotalDiscountValue()), "valor descuentos");
        AssertionsManager.softAssertContains(falconProteccionPensioner.getData().getDiscountDescription(),"Pago Prestamo", "concepto descuentos");
        assertAll();
    }

    public void verifyNonExistentPerson() {
        AssertionsManager.softAssertEqual(404, error.getError().getCode(), "codigo de error");
        AssertionsManager.softAssertContains("The information that was requested does not exist",error.getError().getDescription(), "descripcion de error");
        assertAll();
    }

    public void verifyDiscountHigherThanAllowance() {
        AssertionsManager.softAssertEqual(409, error.getError().getCode(), "codigo de error");
        AssertionsManager.softAssertContains("Communication error: El descuento excede la capacidad de endeudamiento",error.getError().getDescription(), "descripcion de error");
        assertAll();
    }


    public void informDiscountHigherThanPension(String docType, String docNum) {
        try {
            discount =GeneralUtilities.randomNumberGenerator(Integer.parseInt(proteccionPensioner.getDatosSalida().getValorMesadaPensional())*3);
            String request=generatePensionarieDiscountRequestCurrentDate(docType,docNum,discount);
            PensionariesDiscountService pensionariesDiscountService = consumePensionariesDiscountService(request);
            responseStatus=pensionariesDiscountService.getStatus();
            verifyResponseStatus(409);
            Thread.sleep(2000);
            error=ErrorResponse.generateErrorResponseFromJson(pensionariesDiscountService.getResponse());
        } catch (IOException | ServiceException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        } catch ( InterruptedException e){
            Thread.currentThread().interrupt();
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    public void verifyResponseStatus(int expectedStatus) {
        AssertionsManager.hardAssertEqual(expectedStatus,responseStatus, MSJ_RESPONSE_STATUS);
    }
}
