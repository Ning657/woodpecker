package com.woodpecker.entity.loandb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈t_order_route表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_order_route")
public class OrderRouteEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "OrderId")
  private Integer orderId;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "BatchId")
  private Integer batchId;

  @Column(name = "RoutePlatform")
  private Integer routePlatform;

  @Column(name = "LastRoutePlatform")
  private Integer lastRoutePlatform;

  @Column(name = "Status")
  private String status;

  @Column(name = "IsBind")
  private Integer isBind;

  @Column(name = "IsBack")
  private Integer isBack;

  @Column(name = "PushDate")
  private Date pushDate;

  @Column(name = "BackDate")
  private Date backDate;

  @Column(name = "FailReason")
  private String failReason;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "SendDate")
  private Date sendDate;

  @Column(name = "penaltyId")
  private Integer penaltyId;

  @Column(name = "wxPenaltyId")
  private Integer wxPenaltyId;

  @Column(name = "accStartDate")
  private Date accStartDate;

  @Column(name = "accEndDate")
  private Date accEndDate;

  @Column(name = "EvidenceStatus")
  private Byte evidenceStatus;

  @Column(name = "EvidenceTime")
  private Date evidenceTime;

  @Column(name = "AutoSignStatus")
  private Byte autoSignStatus;

  @Column(name = "AutoSignTime")
  private Date autoSignTime;

  @Column(name = "WangXinType")
  private Byte wangXinType;

  @Column(name = "Version")
  private Byte version;

  @Column(name = "platformProCatId")
  private Integer platformProCatId;

  @Column(name = "UnionLoanFlag")
  private Byte unionLoanFlag;

  @Column(name = "PlatSignStatus")
  private Byte platSignStatus;

  @Column(name = "IsDelete")
  private Byte isDelete;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "ApplyAmount")
  private BigDecimal applyAmount;

  @Column(name = "repayConfigId")
  private Integer repayConfigId;

  @Column(name = "trustUnitId")
  private Long trustUnitId;

  @Column(name = "authStartDate")
  private Date authStartDate;

  @Column(name = "authEndDate")
  private Date authEndDate;

  @Column(name = "CompositeCompanyId")
  private Long compositeCompanyId;

  @Column(name = "queryStatus")
  private Byte queryStatus;

  @Column(name = "IsGuarantee")
  private Byte isGuarantee;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getBatchId() {
    return batchId;
  }

  public void setBatchId(Integer batchId) {
    this.batchId = batchId;
  }

  public Integer getRoutePlatform() {
    return routePlatform;
  }

  public void setRoutePlatform(Integer routePlatform) {
    this.routePlatform = routePlatform;
  }

  public Integer getLastRoutePlatform() {
    return lastRoutePlatform;
  }

  public void setLastRoutePlatform(Integer lastRoutePlatform) {
    this.lastRoutePlatform = lastRoutePlatform;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getIsBind() {
    return isBind;
  }

  public void setIsBind(Integer isBind) {
    this.isBind = isBind;
  }

  public Integer getIsBack() {
    return isBack;
  }

  public void setIsBack(Integer isBack) {
    this.isBack = isBack;
  }

  public Date getPushDate() {
    return pushDate;
  }

  public void setPushDate(Date pushDate) {
    this.pushDate = pushDate;
  }

  public Date getBackDate() {
    return backDate;
  }

  public void setBackDate(Date backDate) {
    this.backDate = backDate;
  }

  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
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

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  public Integer getPenaltyId() {
    return penaltyId;
  }

  public void setPenaltyId(Integer penaltyId) {
    this.penaltyId = penaltyId;
  }

  public Integer getWxPenaltyId() {
    return wxPenaltyId;
  }

  public void setWxPenaltyId(Integer wxPenaltyId) {
    this.wxPenaltyId = wxPenaltyId;
  }

  public Date getAccStartDate() {
    return accStartDate;
  }

  public void setAccStartDate(Date accStartDate) {
    this.accStartDate = accStartDate;
  }

  public Date getAccEndDate() {
    return accEndDate;
  }

  public void setAccEndDate(Date accEndDate) {
    this.accEndDate = accEndDate;
  }

  public Byte getEvidenceStatus() {
    return evidenceStatus;
  }

  public void setEvidenceStatus(Byte evidenceStatus) {
    this.evidenceStatus = evidenceStatus;
  }

  public Date getEvidenceTime() {
    return evidenceTime;
  }

  public void setEvidenceTime(Date evidenceTime) {
    this.evidenceTime = evidenceTime;
  }

  public Byte getAutoSignStatus() {
    return autoSignStatus;
  }

  public void setAutoSignStatus(Byte autoSignStatus) {
    this.autoSignStatus = autoSignStatus;
  }

  public Date getAutoSignTime() {
    return autoSignTime;
  }

  public void setAutoSignTime(Date autoSignTime) {
    this.autoSignTime = autoSignTime;
  }

  public Byte getWangXinType() {
    return wangXinType;
  }

  public void setWangXinType(Byte wangXinType) {
    this.wangXinType = wangXinType;
  }

  public Byte getVersion() {
    return version;
  }

  public void setVersion(Byte version) {
    this.version = version;
  }

  public Integer getPlatformProCatId() {
    return platformProCatId;
  }

  public void setPlatformProCatId(Integer platformProCatId) {
    this.platformProCatId = platformProCatId;
  }

  public Byte getUnionLoanFlag() {
    return unionLoanFlag;
  }

  public void setUnionLoanFlag(Byte unionLoanFlag) {
    this.unionLoanFlag = unionLoanFlag;
  }

  public Byte getPlatSignStatus() {
    return platSignStatus;
  }

  public void setPlatSignStatus(Byte platSignStatus) {
    this.platSignStatus = platSignStatus;
  }

  public Byte getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getApplyAmount() {
    return applyAmount;
  }

  public void setApplyAmount(BigDecimal applyAmount) {
    this.applyAmount = applyAmount;
  }

  public Integer getRepayConfigId() {
    return repayConfigId;
  }

  public void setRepayConfigId(Integer repayConfigId) {
    this.repayConfigId = repayConfigId;
  }

  public Long getTrustUnitId() {
    return trustUnitId;
  }

  public void setTrustUnitId(Long trustUnitId) {
    this.trustUnitId = trustUnitId;
  }

  public Date getAuthStartDate() {
    return authStartDate;
  }

  public void setAuthStartDate(Date authStartDate) {
    this.authStartDate = authStartDate;
  }

  public Date getAuthEndDate() {
    return authEndDate;
  }

  public void setAuthEndDate(Date authEndDate) {
    this.authEndDate = authEndDate;
  }

  public Long getCompositeCompanyId() {
    return compositeCompanyId;
  }

  public void setCompositeCompanyId(Long compositeCompanyId) {
    this.compositeCompanyId = compositeCompanyId;
  }

  public Byte getQueryStatus() {
    return queryStatus;
  }

  public void setQueryStatus(Byte queryStatus) {
    this.queryStatus = queryStatus;
  }

  public Byte getIsGuarantee() {
    return isGuarantee;
  }

  public void setIsGuarantee(Byte isGuarantee) {
    this.isGuarantee = isGuarantee;
  }

  @Override
  public String toString() {
    return "OrderRouteEntity{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", batchId=" + batchId +
        ", routePlatform=" + routePlatform +
        ", lastRoutePlatform=" + lastRoutePlatform +
        ", status='" + status + '\'' +
        ", isBind=" + isBind +
        ", isBack=" + isBack +
        ", pushDate=" + pushDate +
        ", backDate=" + backDate +
        ", failReason='" + failReason + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", sendDate=" + sendDate +
        ", penaltyId=" + penaltyId +
        ", wxPenaltyId=" + wxPenaltyId +
        ", accStartDate=" + accStartDate +
        ", accEndDate=" + accEndDate +
        ", evidenceStatus=" + evidenceStatus +
        ", evidenceTime=" + evidenceTime +
        ", autoSignStatus=" + autoSignStatus +
        ", autoSignTime=" + autoSignTime +
        ", wangXinType=" + wangXinType +
        ", version=" + version +
        ", platformProCatId=" + platformProCatId +
        ", unionLoanFlag=" + unionLoanFlag +
        ", platSignStatus=" + platSignStatus +
        ", isDelete=" + isDelete +
        ", amount=" + amount +
        ", applyAmount=" + applyAmount +
        ", repayConfigId=" + repayConfigId +
        ", trustUnitId=" + trustUnitId +
        ", authStartDate=" + authStartDate +
        ", authEndDate=" + authEndDate +
        ", compositeCompanyId=" + compositeCompanyId +
        ", queryStatus=" + queryStatus +
        ", isGuarantee=" + isGuarantee +
        '}';
  }
}
