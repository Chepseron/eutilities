/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amon.sabul
 */
@Entity
@Table(name = "payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p")
    , @NamedQuery(name = "Payments.findByIdpayments", query = "SELECT p FROM Payments p WHERE p.idpayments = :idpayments")
    , @NamedQuery(name = "Payments.findByPrn", query = "SELECT p FROM Payments p WHERE p.prn = :prn")
    , @NamedQuery(name = "Payments.findByCustomerReference", query = "SELECT p FROM Payments p WHERE p.customerReference = :customerReference")
    , @NamedQuery(name = "Payments.findByCustomerName", query = "SELECT p FROM Payments p WHERE p.customerName = :customerName")
    , @NamedQuery(name = "Payments.findByPhone", query = "SELECT p FROM Payments p WHERE p.phone = :phone")
    , @NamedQuery(name = "Payments.findByPrdate", query = "SELECT p FROM Payments p WHERE p.prdate = :prdate")
    , @NamedQuery(name = "Payments.findByBranchID", query = "SELECT p FROM Payments p WHERE p.branchID = :branchID")
    , @NamedQuery(name = "Payments.findByExpireDate", query = "SELECT p FROM Payments p WHERE p.expireDate = :expireDate")
    , @NamedQuery(name = "Payments.findByAmountDue", query = "SELECT p FROM Payments p WHERE p.amountDue = :amountDue")
    , @NamedQuery(name = "Payments.findByAmountPaid", query = "SELECT p FROM Payments p WHERE p.amountPaid = :amountPaid")
    , @NamedQuery(name = "Payments.findByPayDate", query = "SELECT p FROM Payments p WHERE p.payDate = :payDate")
    , @NamedQuery(name = "Payments.findByValueDate", query = "SELECT p FROM Payments p WHERE p.valueDate = :valueDate")
    , @NamedQuery(name = "Payments.findByCreditBr", query = "SELECT p FROM Payments p WHERE p.creditBr = :creditBr")
    , @NamedQuery(name = "Payments.findByCreditAccount", query = "SELECT p FROM Payments p WHERE p.creditAccount = :creditAccount")
    , @NamedQuery(name = "Payments.findByCreditCurr", query = "SELECT p FROM Payments p WHERE p.creditCurr = :creditCurr")
    , @NamedQuery(name = "Payments.findByCreditRt", query = "SELECT p FROM Payments p WHERE p.creditRt = :creditRt")
    , @NamedQuery(name = "Payments.findByCreditAmount", query = "SELECT p FROM Payments p WHERE p.creditAmount = :creditAmount")
    , @NamedQuery(name = "Payments.findByDebitBr", query = "SELECT p FROM Payments p WHERE p.debitBr = :debitBr")
    , @NamedQuery(name = "Payments.findByDebitAcc", query = "SELECT p FROM Payments p WHERE p.debitAcc = :debitAcc")
    , @NamedQuery(name = "Payments.findByDebitCurr", query = "SELECT p FROM Payments p WHERE p.debitCurr = :debitCurr")
    , @NamedQuery(name = "Payments.findByDebitRt", query = "SELECT p FROM Payments p WHERE p.debitRt = :debitRt")
    , @NamedQuery(name = "Payments.findByDebitAmount", query = "SELECT p FROM Payments p WHERE p.debitAmount = :debitAmount")
    , @NamedQuery(name = "Payments.findByCommAccount", query = "SELECT p FROM Payments p WHERE p.commAccount = :commAccount")
    , @NamedQuery(name = "Payments.findByCommBr", query = "SELECT p FROM Payments p WHERE p.commBr = :commBr")
    , @NamedQuery(name = "Payments.findByCommAmount", query = "SELECT p FROM Payments p WHERE p.commAmount = :commAmount")
    , @NamedQuery(name = "Payments.findByCharged", query = "SELECT p FROM Payments p WHERE p.charged = :charged")
    , @NamedQuery(name = "Payments.findByTaxAmount", query = "SELECT p FROM Payments p WHERE p.taxAmount = :taxAmount")
    , @NamedQuery(name = "Payments.findByTaxAccount", query = "SELECT p FROM Payments p WHERE p.taxAccount = :taxAccount")
    , @NamedQuery(name = "Payments.findByMaker", query = "SELECT p FROM Payments p WHERE p.maker = :maker")
    , @NamedQuery(name = "Payments.findByVerified", query = "SELECT p FROM Payments p WHERE p.verified = :verified")
    , @NamedQuery(name = "Payments.findByVerifyDate", query = "SELECT p FROM Payments p WHERE p.verifyDate = :verifyDate")
    , @NamedQuery(name = "Payments.findByChecker", query = "SELECT p FROM Payments p WHERE p.checker = :checker")
    , @NamedQuery(name = "Payments.findByAuthorised", query = "SELECT p FROM Payments p WHERE p.authorised = :authorised")
    , @NamedQuery(name = "Payments.findByAuthoriseDate", query = "SELECT p FROM Payments p WHERE p.authoriseDate = :authoriseDate")
    , @NamedQuery(name = "Payments.findByBankStatus", query = "SELECT p FROM Payments p WHERE p.bankStatus = :bankStatus")
    , @NamedQuery(name = "Payments.findByUtilityStatus", query = "SELECT p FROM Payments p WHERE p.utilityStatus = :utilityStatus")
    , @NamedQuery(name = "Payments.findByIsCredited", query = "SELECT p FROM Payments p WHERE p.isCredited = :isCredited")
    , @NamedQuery(name = "Payments.findByCreditNotified", query = "SELECT p FROM Payments p WHERE p.creditNotified = :creditNotified")
    , @NamedQuery(name = "Payments.findByIsDebited", query = "SELECT p FROM Payments p WHERE p.isDebited = :isDebited")
    , @NamedQuery(name = "Payments.findByDebitNotified", query = "SELECT p FROM Payments p WHERE p.debitNotified = :debitNotified")
    , @NamedQuery(name = "Payments.findByChequeNo", query = "SELECT p FROM Payments p WHERE p.chequeNo = :chequeNo")
    , @NamedQuery(name = "Payments.findByChqbank", query = "SELECT p FROM Payments p WHERE p.chqbank = :chqbank")
    , @NamedQuery(name = "Payments.findByChqbranch", query = "SELECT p FROM Payments p WHERE p.chqbranch = :chqbranch")
    , @NamedQuery(name = "Payments.findByFlagDate", query = "SELECT p FROM Payments p WHERE p.flagDate = :flagDate")
    , @NamedQuery(name = "Payments.findByFlaged", query = "SELECT p FROM Payments p WHERE p.flaged = :flaged")
    , @NamedQuery(name = "Payments.findByDishonoured", query = "SELECT p FROM Payments p WHERE p.dishonoured = :dishonoured")
    , @NamedQuery(name = "Payments.findByRejectcode", query = "SELECT p FROM Payments p WHERE p.rejectcode = :rejectcode")
    , @NamedQuery(name = "Payments.findByRejectReason", query = "SELECT p FROM Payments p WHERE p.rejectReason = :rejectReason")
    , @NamedQuery(name = "Payments.findByProccode", query = "SELECT p FROM Payments p WHERE p.proccode = :proccode")
    , @NamedQuery(name = "Payments.findByBankreference", query = "SELECT p FROM Payments p WHERE p.bankreference = :bankreference")
    , @NamedQuery(name = "Payments.findByUtilReference", query = "SELECT p FROM Payments p WHERE p.utilReference = :utilReference")
    , @NamedQuery(name = "Payments.findByPayOption", query = "SELECT p FROM Payments p WHERE p.payOption = :payOption")
    , @NamedQuery(name = "Payments.findByCustomerType", query = "SELECT p FROM Payments p WHERE p.customerType = :customerType")
    , @NamedQuery(name = "Payments.findByPaymentType", query = "SELECT p FROM Payments p WHERE p.paymentType = :paymentType")
    , @NamedQuery(name = "Payments.findByPaymentCode", query = "SELECT p FROM Payments p WHERE p.paymentCode = :paymentCode")
    , @NamedQuery(name = "Payments.findByArea", query = "SELECT p FROM Payments p WHERE p.area = :area")
    , @NamedQuery(name = "Payments.findByStatus", query = "SELECT p FROM Payments p WHERE p.status = :status")
    , @NamedQuery(name = "Payments.findByStatusDesc", query = "SELECT p FROM Payments p WHERE p.statusDesc = :statusDesc")
    , @NamedQuery(name = "Payments.findByBackupDate", query = "SELECT p FROM Payments p WHERE p.backupDate = :backupDate")
    , @NamedQuery(name = "Payments.findByRouteNumber", query = "SELECT p FROM Payments p WHERE p.routeNumber = :routeNumber")})
public class Payments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpayments")
    private Integer idpayments;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "prn")
    private String prn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "customerReference")
    private String customerReference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "customerName")
    private String customerName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branchID")
    private int branchID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expireDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountDue")
    private int amountDue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountPaid")
    private int amountPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "creditBr")
    private String creditBr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "creditAccount")
    private String creditAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "creditCurr")
    private String creditCurr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "creditRt")
    private String creditRt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creditAmount")
    private int creditAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "debitBr")
    private String debitBr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "debitAcc")
    private String debitAcc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "debitCurr")
    private String debitCurr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "debitRt")
    private String debitRt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "debitAmount")
    private int debitAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "commAccount")
    private String commAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "commBr")
    private String commBr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "commAmount")
    private int commAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "charged")
    private int charged;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxAmount")
    private int taxAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "taxAccount")
    private String taxAccount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maker")
    private int maker;
    @Basic(optional = false)
    @NotNull
    @Column(name = "verified")
    private int verified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "verifyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifyDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "checker")
    private int checker;
    @Column(name = "authorised")
    private Integer authorised;
    @Column(name = "authoriseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authoriseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bankStatus")
    private int bankStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "utilityStatus")
    private int utilityStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isCredited")
    private int isCredited;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creditNotified")
    private int creditNotified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isDebited")
    private int isDebited;
    @Basic(optional = false)
    @NotNull
    @Column(name = "debitNotified")
    private int debitNotified;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "chequeNo")
    private String chequeNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "chqbank")
    private String chqbank;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "chqbranch")
    private String chqbranch;
    @Column(name = "flagDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date flagDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "flaged")
    private int flaged;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dishonoured")
    private int dishonoured;
    @Size(max = 45)
    @Column(name = "rejectcode")
    private String rejectcode;
    @Size(max = 200)
    @Column(name = "rejectReason")
    private String rejectReason;
    @Size(max = 45)
    @Column(name = "proccode")
    private String proccode;
    @Size(max = 200)
    @Column(name = "bankreference")
    private String bankreference;
    @Size(max = 200)
    @Column(name = "utilReference")
    private String utilReference;
    @Size(max = 45)
    @Column(name = "payOption")
    private String payOption;
    @Size(max = 45)
    @Column(name = "customerType")
    private String customerType;
    @Size(max = 45)
    @Column(name = "paymentType")
    private String paymentType;
    @Size(max = 45)
    @Column(name = "paymentCode")
    private String paymentCode;
    @Size(max = 45)
    @Column(name = "area")
    private String area;
    @Column(name = "status")
    private Integer status;
    @Size(max = 300)
    @Column(name = "statusDesc")
    private String statusDesc;
    @Column(name = "backupDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date backupDate;
    @Size(max = 45)
    @Column(name = "routeNumber")
    private String routeNumber;

    public Payments() {
    }

    public Payments(Integer idpayments) {
        this.idpayments = idpayments;
    }

    public Payments(Integer idpayments, String prn, String customerReference, String customerName, String phone, Date prdate, int branchID, Date expireDate, int amountDue, int amountPaid, Date payDate, Date valueDate, String creditBr, String creditAccount, String creditCurr, String creditRt, int creditAmount, String debitBr, String debitAcc, String debitCurr, String debitRt, int debitAmount, String commAccount, String commBr, int commAmount, int charged, int taxAmount, String taxAccount, int maker, int verified, Date verifyDate, int checker, int bankStatus, int utilityStatus, int isCredited, int creditNotified, int isDebited, int debitNotified, String chequeNo, String chqbank, String chqbranch, int flaged, int dishonoured) {
        this.idpayments = idpayments;
        this.prn = prn;
        this.customerReference = customerReference;
        this.customerName = customerName;
        this.phone = phone;
        this.prdate = prdate;
        this.branchID = branchID;
        this.expireDate = expireDate;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.payDate = payDate;
        this.valueDate = valueDate;
        this.creditBr = creditBr;
        this.creditAccount = creditAccount;
        this.creditCurr = creditCurr;
        this.creditRt = creditRt;
        this.creditAmount = creditAmount;
        this.debitBr = debitBr;
        this.debitAcc = debitAcc;
        this.debitCurr = debitCurr;
        this.debitRt = debitRt;
        this.debitAmount = debitAmount;
        this.commAccount = commAccount;
        this.commBr = commBr;
        this.commAmount = commAmount;
        this.charged = charged;
        this.taxAmount = taxAmount;
        this.taxAccount = taxAccount;
        this.maker = maker;
        this.verified = verified;
        this.verifyDate = verifyDate;
        this.checker = checker;
        this.bankStatus = bankStatus;
        this.utilityStatus = utilityStatus;
        this.isCredited = isCredited;
        this.creditNotified = creditNotified;
        this.isDebited = isDebited;
        this.debitNotified = debitNotified;
        this.chequeNo = chequeNo;
        this.chqbank = chqbank;
        this.chqbranch = chqbranch;
        this.flaged = flaged;
        this.dishonoured = dishonoured;
    }

    public Integer getIdpayments() {
        return idpayments;
    }

    public void setIdpayments(Integer idpayments) {
        this.idpayments = idpayments;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getPrdate() {
        return prdate;
    }

    public void setPrdate(Date prdate) {
        this.prdate = prdate;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getCreditBr() {
        return creditBr;
    }

    public void setCreditBr(String creditBr) {
        this.creditBr = creditBr;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getCreditCurr() {
        return creditCurr;
    }

    public void setCreditCurr(String creditCurr) {
        this.creditCurr = creditCurr;
    }

    public String getCreditRt() {
        return creditRt;
    }

    public void setCreditRt(String creditRt) {
        this.creditRt = creditRt;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDebitBr() {
        return debitBr;
    }

    public void setDebitBr(String debitBr) {
        this.debitBr = debitBr;
    }

    public String getDebitAcc() {
        return debitAcc;
    }

    public void setDebitAcc(String debitAcc) {
        this.debitAcc = debitAcc;
    }

    public String getDebitCurr() {
        return debitCurr;
    }

    public void setDebitCurr(String debitCurr) {
        this.debitCurr = debitCurr;
    }

    public String getDebitRt() {
        return debitRt;
    }

    public void setDebitRt(String debitRt) {
        this.debitRt = debitRt;
    }

    public int getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(int debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCommAccount() {
        return commAccount;
    }

    public void setCommAccount(String commAccount) {
        this.commAccount = commAccount;
    }

    public String getCommBr() {
        return commBr;
    }

    public void setCommBr(String commBr) {
        this.commBr = commBr;
    }

    public int getCommAmount() {
        return commAmount;
    }

    public void setCommAmount(int commAmount) {
        this.commAmount = commAmount;
    }

    public int getCharged() {
        return charged;
    }

    public void setCharged(int charged) {
        this.charged = charged;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxAccount() {
        return taxAccount;
    }

    public void setTaxAccount(String taxAccount) {
        this.taxAccount = taxAccount;
    }

    public int getMaker() {
        return maker;
    }

    public void setMaker(int maker) {
        this.maker = maker;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public int getChecker() {
        return checker;
    }

    public void setChecker(int checker) {
        this.checker = checker;
    }

    public Integer getAuthorised() {
        return authorised;
    }

    public void setAuthorised(Integer authorised) {
        this.authorised = authorised;
    }

    public Date getAuthoriseDate() {
        return authoriseDate;
    }

    public void setAuthoriseDate(Date authoriseDate) {
        this.authoriseDate = authoriseDate;
    }

    public int getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(int bankStatus) {
        this.bankStatus = bankStatus;
    }

    public int getUtilityStatus() {
        return utilityStatus;
    }

    public void setUtilityStatus(int utilityStatus) {
        this.utilityStatus = utilityStatus;
    }

    public int getIsCredited() {
        return isCredited;
    }

    public void setIsCredited(int isCredited) {
        this.isCredited = isCredited;
    }

    public int getCreditNotified() {
        return creditNotified;
    }

    public void setCreditNotified(int creditNotified) {
        this.creditNotified = creditNotified;
    }

    public int getIsDebited() {
        return isDebited;
    }

    public void setIsDebited(int isDebited) {
        this.isDebited = isDebited;
    }

    public int getDebitNotified() {
        return debitNotified;
    }

    public void setDebitNotified(int debitNotified) {
        this.debitNotified = debitNotified;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getChqbank() {
        return chqbank;
    }

    public void setChqbank(String chqbank) {
        this.chqbank = chqbank;
    }

    public String getChqbranch() {
        return chqbranch;
    }

    public void setChqbranch(String chqbranch) {
        this.chqbranch = chqbranch;
    }

    public Date getFlagDate() {
        return flagDate;
    }

    public void setFlagDate(Date flagDate) {
        this.flagDate = flagDate;
    }

    public int getFlaged() {
        return flaged;
    }

    public void setFlaged(int flaged) {
        this.flaged = flaged;
    }

    public int getDishonoured() {
        return dishonoured;
    }

    public void setDishonoured(int dishonoured) {
        this.dishonoured = dishonoured;
    }

    public String getRejectcode() {
        return rejectcode;
    }

    public void setRejectcode(String rejectcode) {
        this.rejectcode = rejectcode;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getProccode() {
        return proccode;
    }

    public void setProccode(String proccode) {
        this.proccode = proccode;
    }

    public String getBankreference() {
        return bankreference;
    }

    public void setBankreference(String bankreference) {
        this.bankreference = bankreference;
    }

    public String getUtilReference() {
        return utilReference;
    }

    public void setUtilReference(String utilReference) {
        this.utilReference = utilReference;
    }

    public String getPayOption() {
        return payOption;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getBackupDate() {
        return backupDate;
    }

    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpayments != null ? idpayments.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.idpayments == null && other.idpayments != null) || (this.idpayments != null && !this.idpayments.equals(other.idpayments))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Payments[ idpayments=" + idpayments + " ]";
    }
    
}
