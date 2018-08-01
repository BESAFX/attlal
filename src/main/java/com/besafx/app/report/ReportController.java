package com.besafx.app.report;

import com.besafx.app.component.ReportExporter;
import com.besafx.app.entity.BankTransaction;
import com.besafx.app.enums.ExportType;
import com.besafx.app.init.Initializer;
import com.besafx.app.service.BankTransactionService;
import com.besafx.app.service.ContractPaymentService;
import com.besafx.app.service.ContractService;
import com.besafx.app.util.CompanyOptions;
import com.besafx.app.util.JSONConverter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {

    private final static Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractPaymentService contractPaymentService;

    @Autowired
    private BankTransactionService bankTransactionService;

    @Autowired
    private ReportExporter reportExporter;

    @RequestMapping(value = "/report/contract/{contractId}", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public void printContract(
            @PathVariable(value = "contractId") Long contractId,
            HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("CONTRACT", contractService.findOne(contractId));

        CompanyOptions options = JSONConverter.toObject(Initializer.company.getOptions(), CompanyOptions.class);
        map.put("LOGO", options.getLogo());
        map.put("BACKGROUND", options.getBackground());

        ClassPathResource jrxmlFile = new ClassPathResource("/report/contract/Contract.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getInputStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
        reportExporter.export(ExportType.PDF, response, jasperPrint);
    }

    @RequestMapping(value = "/report/contractPayment/{contractPaymentId}", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public void printContractPayment(
            @PathVariable(value = "contractPaymentId") Long contractPaymentId,
            HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("CONTRACT_PAYMENT", contractPaymentService.findOne(contractPaymentId));

        CompanyOptions options = JSONConverter.toObject(Initializer.company.getOptions(), CompanyOptions.class);
        map.put("LOGO", options.getLogo());
        map.put("BACKGROUND", options.getBackground());

        ClassPathResource jrxmlFile = new ClassPathResource("/report/contract/ContractPayment.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getInputStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
        reportExporter.export(ExportType.PDF, response, jasperPrint);
    }

    @RequestMapping(value = "/report/receiptDeposit/{bankTransactionId}", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public void printReceiptDeposit(
            @PathVariable(value = "bankTransactionId") Long bankTransactionId,
            HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("BANK_TRANSACTION", bankTransactionService.findOne(bankTransactionId));

        CompanyOptions options = JSONConverter.toObject(Initializer.company.getOptions(), CompanyOptions.class);
        map.put("LOGO", options.getLogo());
        map.put("BACKGROUND", options.getBackground());

        ClassPathResource jrxmlFile = new ClassPathResource("/report/contract/ReceiptDeposit.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getInputStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
        reportExporter.export(ExportType.PDF, response, jasperPrint);
    }

    @RequestMapping(value = "/report/receiptWithdraw/{bankTransactionId}", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public void printReceiptWithdraw(
            @PathVariable(value = "bankTransactionId") Long bankTransactionId,
            HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("BANK_TRANSACTION", bankTransactionService.findOne(bankTransactionId));

        CompanyOptions options = JSONConverter.toObject(Initializer.company.getOptions(), CompanyOptions.class);
        map.put("LOGO", options.getLogo());
        map.put("BACKGROUND", options.getBackground());

        ClassPathResource jrxmlFile = new ClassPathResource("/report/contract/ReceiptWithdraw.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getInputStream());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
        reportExporter.export(ExportType.PDF, response, jasperPrint);
    }


}
