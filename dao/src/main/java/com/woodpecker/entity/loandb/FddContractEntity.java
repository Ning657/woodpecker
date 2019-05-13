package com.woodpecker.entity.loandb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈t_fdd_contract表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_fdd_contract")
public class FddContractEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "CustomerId")
  private String customerId;

  @Column(name = "OrderId")
  private Integer orderId;

  @Column(name = "RoutePlatform")
  private Integer routePlatform;

  @Column(name = "ContractType")
  private String contractType;

  @Column(name = "TransactionId")
  private String transactionId;

  @Column(name = "pushDocTransactionId")
  private String pushDocTransactionId;

  @Column(name = "batchId")
  private String batchId;

  @Column(name = "ContractId")
  private String contractId;

  @Column(name = "ContractAddr")
  private String contractAddr;

  @Column(name = "ContractAddrFinal")
  private String contractAddrFinal;

  @Column(name = "SignUrl")
  private String signUrl;

  @Column(name = "CustSignTime")
  private Date custSignTime;

  @Column(name = "ArchiveTime")
  private Date archiveTime;

  @Column(name = "DownloadUrl")
  private String downloadUrl;

  @Column(name = "ViewpdfUrl")
  private String viewpdfUrl;

  @Column(name = "Status")
  private String status;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "EvId")
  private String evId;

  @Column(name = "Type")
  private Byte type;

  @Column(name = "version")
  private Integer version;

  @Column(name = "SelfSignCompany")
  private String selfSignCompany;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getRoutePlatform() {
    return routePlatform;
  }

  public void setRoutePlatform(Integer routePlatform) {
    this.routePlatform = routePlatform;
  }

  public String getContractType() {
    return contractType;
  }

  public void setContractType(String contractType) {
    this.contractType = contractType;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getPushDocTransactionId() {
    return pushDocTransactionId;
  }

  public void setPushDocTransactionId(String pushDocTransactionId) {
    this.pushDocTransactionId = pushDocTransactionId;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getContractAddr() {
    return contractAddr;
  }

  public void setContractAddr(String contractAddr) {
    this.contractAddr = contractAddr;
  }

  public String getContractAddrFinal() {
    return contractAddrFinal;
  }

  public void setContractAddrFinal(String contractAddrFinal) {
    this.contractAddrFinal = contractAddrFinal;
  }

  public String getSignUrl() {
    return signUrl;
  }

  public void setSignUrl(String signUrl) {
    this.signUrl = signUrl;
  }

  public Date getCustSignTime() {
    return custSignTime;
  }

  public void setCustSignTime(Date custSignTime) {
    this.custSignTime = custSignTime;
  }

  public Date getArchiveTime() {
    return archiveTime;
  }

  public void setArchiveTime(Date archiveTime) {
    this.archiveTime = archiveTime;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public String getViewpdfUrl() {
    return viewpdfUrl;
  }

  public void setViewpdfUrl(String viewpdfUrl) {
    this.viewpdfUrl = viewpdfUrl;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public String getEvId() {
    return evId;
  }

  public void setEvId(String evId) {
    this.evId = evId;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getSelfSignCompany() {
    return selfSignCompany;
  }

  public void setSelfSignCompany(String selfSignCompany) {
    this.selfSignCompany = selfSignCompany;
  }


  @Override
  public String toString() {
    return "FddContractEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", customerId='" + customerId + '\'' +
        ", orderId=" + orderId +
        ", routePlatform=" + routePlatform +
        ", contractType='" + contractType + '\'' +
        ", transactionId='" + transactionId + '\'' +
        ", pushDocTransactionId='" + pushDocTransactionId + '\'' +
        ", batchId='" + batchId + '\'' +
        ", contractId='" + contractId + '\'' +
        ", contractAddr='" + contractAddr + '\'' +
        ", contractAddrFinal='" + contractAddrFinal + '\'' +
        ", signUrl='" + signUrl + '\'' +
        ", custSignTime=" + custSignTime +
        ", archiveTime=" + archiveTime +
        ", downloadUrl='" + downloadUrl + '\'' +
        ", viewpdfUrl='" + viewpdfUrl + '\'' +
        ", status='" + status + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", evId='" + evId + '\'' +
        ", type=" + type +
        ", version=" + version +
        ", selfSignCompany='" + selfSignCompany + '\'' +
        '}';
  }
}
