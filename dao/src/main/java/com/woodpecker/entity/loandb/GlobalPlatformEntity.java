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
 * 类描述:〈t_global_platform表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_global_platform")
public class GlobalPlatformEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Status")
  private Byte status;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "IsSupervise")
  private Byte isSupervise;

  @Column(name = "SupportTrustPlan")
  private Byte supportTrustPlan;

  @Column(name = "NeedSinglePremiums")
  private Byte needSinglePremiums;

  @Column(name = "HasOwnPage")
  private Byte hasOwnPage;

  @Column(name = "SendSms")
  private Byte sendSms;

  @Column(name = "SendMail")
  private Byte sendMail;

  @Column(name = "OrderRouteQuery")
  private String orderRouteQuery;

  @Column(name = "NotarizationType")
  private Byte notarizationType;

  @Column(name = "OrderRepaymentMethod")
  private Byte orderRepaymentMethod;

  @Column(name = "ContractTypes")
  private String contractTypes;

  @Column(name = "RebindBack")
  private Byte rebindBack;

  @Column(name = "RoutePlatform")
  private Integer routePlatform;

  @Column(name = "priority")
  private Integer priority;

  @Column(name = "UnionLoanFlag")
  private Byte unionLoanFlag;

  @Column(name = "CreditPriority")
  private Integer creditPriority;

  @Column(name = "IsCreditPlatform")
  private Byte isCreditPlatform;

  @Column(name = "UnionLoanIds")
  private String unionLoanIds;

  @Column(name = "CompositeCompanyId")
  private Long compositeCompanyId;

  @Column(name = "ReserveRate")
  private BigDecimal reserveRate;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
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

  public Byte getIsSupervise() {
    return isSupervise;
  }

  public void setIsSupervise(Byte isSupervise) {
    this.isSupervise = isSupervise;
  }

  public Byte getSupportTrustPlan() {
    return supportTrustPlan;
  }

  public void setSupportTrustPlan(Byte supportTrustPlan) {
    this.supportTrustPlan = supportTrustPlan;
  }

  public Byte getNeedSinglePremiums() {
    return needSinglePremiums;
  }

  public void setNeedSinglePremiums(Byte needSinglePremiums) {
    this.needSinglePremiums = needSinglePremiums;
  }

  public Byte getHasOwnPage() {
    return hasOwnPage;
  }

  public void setHasOwnPage(Byte hasOwnPage) {
    this.hasOwnPage = hasOwnPage;
  }

  public Byte getSendSms() {
    return sendSms;
  }

  public void setSendSms(Byte sendSms) {
    this.sendSms = sendSms;
  }

  public Byte getSendMail() {
    return sendMail;
  }

  public void setSendMail(Byte sendMail) {
    this.sendMail = sendMail;
  }

  public String getOrderRouteQuery() {
    return orderRouteQuery;
  }

  public void setOrderRouteQuery(String orderRouteQuery) {
    this.orderRouteQuery = orderRouteQuery;
  }

  public Byte getNotarizationType() {
    return notarizationType;
  }

  public void setNotarizationType(Byte notarizationType) {
    this.notarizationType = notarizationType;
  }

  public Byte getOrderRepaymentMethod() {
    return orderRepaymentMethod;
  }

  public void setOrderRepaymentMethod(Byte orderRepaymentMethod) {
    this.orderRepaymentMethod = orderRepaymentMethod;
  }

  public String getContractTypes() {
    return contractTypes;
  }

  public void setContractTypes(String contractTypes) {
    this.contractTypes = contractTypes;
  }

  public Byte getRebindBack() {
    return rebindBack;
  }

  public void setRebindBack(Byte rebindBack) {
    this.rebindBack = rebindBack;
  }

  public Integer getRoutePlatform() {
    return routePlatform;
  }

  public void setRoutePlatform(Integer routePlatform) {
    this.routePlatform = routePlatform;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Byte getUnionLoanFlag() {
    return unionLoanFlag;
  }

  public void setUnionLoanFlag(Byte unionLoanFlag) {
    this.unionLoanFlag = unionLoanFlag;
  }

  public Integer getCreditPriority() {
    return creditPriority;
  }

  public void setCreditPriority(Integer creditPriority) {
    this.creditPriority = creditPriority;
  }

  public Byte getIsCreditPlatform() {
    return isCreditPlatform;
  }

  public void setIsCreditPlatform(Byte isCreditPlatform) {
    this.isCreditPlatform = isCreditPlatform;
  }

  public String getUnionLoanIds() {
    return unionLoanIds;
  }

  public void setUnionLoanIds(String unionLoanIds) {
    this.unionLoanIds = unionLoanIds;
  }

  public Long getCompositeCompanyId() {
    return compositeCompanyId;
  }

  public void setCompositeCompanyId(Long compositeCompanyId) {
    this.compositeCompanyId = compositeCompanyId;
  }

  public BigDecimal getReserveRate() {
    return reserveRate;
  }

  public void setReserveRate(BigDecimal reserveRate) {
    this.reserveRate = reserveRate;
  }

  @Override
  public String toString() {
    return "GlobalPlatformEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", status=" + status +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isSupervise=" + isSupervise +
        ", supportTrustPlan=" + supportTrustPlan +
        ", needSinglePremiums=" + needSinglePremiums +
        ", hasOwnPage=" + hasOwnPage +
        ", sendSms=" + sendSms +
        ", sendMail=" + sendMail +
        ", orderRouteQuery='" + orderRouteQuery + '\'' +
        ", notarizationType=" + notarizationType +
        ", orderRepaymentMethod=" + orderRepaymentMethod +
        ", contractTypes='" + contractTypes + '\'' +
        ", rebindBack=" + rebindBack +
        ", routePlatform=" + routePlatform +
        ", priority=" + priority +
        ", unionLoanFlag=" + unionLoanFlag +
        ", creditPriority=" + creditPriority +
        ", isCreditPlatform=" + isCreditPlatform +
        ", unionLoanIds='" + unionLoanIds + '\'' +
        ", compositeCompanyId=" + compositeCompanyId +
        ", reserveRate=" + reserveRate +
        '}';
  }
}
