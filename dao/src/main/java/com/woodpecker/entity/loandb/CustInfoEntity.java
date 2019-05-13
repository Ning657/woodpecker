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
 * 类描述:〈t_cust_info表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_cust_info")
public class CustInfoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "UserName")
  private String userName;

  @Column(name = "UserPwd")
  private String userPwd;

  @Column(name = "UserIcon")
  private String userIcon;

  @Column(name = "IdCardNo")
  private String idCardNo;

  @Column(name = "IdExpDate")
  private String idExpDate;

  @Column(name = "IdCardImg")
  private String idCardImg;

  @Column(name = "Mobile")
  private String mobile;

  @Column(name = "MobileMD5")
  private String mobileMD5;

  @Column(name = "Sex")
  private Byte sex;

  @Column(name = "Age")
  private Byte age;

  @Column(name = "ShengXiao")
  private String shengXiao;

  @Column(name = "Education")
  private String education;

  @Column(name = "IsDaily")
  private Byte isDaily;

  @Column(name = "GraduateDate")
  private Date graduateDate;

  @Column(name = "Profession")
  private String profession;

  @Column(name = "School")
  private String school;

  @Column(name = "City")
  private String city;

  @Column(name = "Province")
  private String province;

  @Column(name = "Ext")
  private String ext;

  @Column(name = "ICETel1")
  private String iCETel1;

  @Column(name = "ICEName1")
  private String iCEName1;

  @Column(name = "ICERelation1")
  private Byte iCERelation1;

  @Column(name = "ICETel2")
  private String iCETel2;

  @Column(name = "ICEName2")
  private String iCEName2;

  @Column(name = "ICERelation2")
  private Byte iCERelation2;

  @Column(name = "ICETel3")
  private String iCETel3;

  @Column(name = "ICEName3")
  private String iCEName3;

  @Column(name = "ICERelation3")
  private Byte iCERelation3;

  @Column(name = "LastApplyTime")
  private Date lastApplyTime;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "Remind")
  private Byte remind;

  @Column(name = "IdentityFlag")
  private Byte identityFlag;

  @Column(name = "PengYuanResult")
  private Byte pengYuanResult;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }

  public String getUserIcon() {
    return userIcon;
  }

  public void setUserIcon(String userIcon) {
    this.userIcon = userIcon;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getIdExpDate() {
    return idExpDate;
  }

  public void setIdExpDate(String idExpDate) {
    this.idExpDate = idExpDate;
  }

  public String getIdCardImg() {
    return idCardImg;
  }

  public void setIdCardImg(String idCardImg) {
    this.idCardImg = idCardImg;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getMobileMD5() {
    return mobileMD5;
  }

  public void setMobileMD5(String mobileMD5) {
    this.mobileMD5 = mobileMD5;
  }

  public Byte getSex() {
    return sex;
  }

  public void setSex(Byte sex) {
    this.sex = sex;
  }

  public Byte getAge() {
    return age;
  }

  public void setAge(Byte age) {
    this.age = age;
  }

  public String getShengXiao() {
    return shengXiao;
  }

  public void setShengXiao(String shengXiao) {
    this.shengXiao = shengXiao;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public Byte getIsDaily() {
    return isDaily;
  }

  public void setIsDaily(Byte isDaily) {
    this.isDaily = isDaily;
  }

  public Date getGraduateDate() {
    return graduateDate;
  }

  public void setGraduateDate(Date graduateDate) {
    this.graduateDate = graduateDate;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getExt() {
    return ext;
  }

  public void setExt(String ext) {
    this.ext = ext;
  }

  public String getiCETel1() {
    return iCETel1;
  }

  public void setiCETel1(String iCETel1) {
    this.iCETel1 = iCETel1;
  }

  public String getiCEName1() {
    return iCEName1;
  }

  public void setiCEName1(String iCEName1) {
    this.iCEName1 = iCEName1;
  }

  public Byte getiCERelation1() {
    return iCERelation1;
  }

  public void setiCERelation1(Byte iCERelation1) {
    this.iCERelation1 = iCERelation1;
  }

  public String getiCETel2() {
    return iCETel2;
  }

  public void setiCETel2(String iCETel2) {
    this.iCETel2 = iCETel2;
  }

  public String getiCEName2() {
    return iCEName2;
  }

  public void setiCEName2(String iCEName2) {
    this.iCEName2 = iCEName2;
  }

  public Byte getiCERelation2() {
    return iCERelation2;
  }

  public void setiCERelation2(Byte iCERelation2) {
    this.iCERelation2 = iCERelation2;
  }

  public String getiCETel3() {
    return iCETel3;
  }

  public void setiCETel3(String iCETel3) {
    this.iCETel3 = iCETel3;
  }

  public String getiCEName3() {
    return iCEName3;
  }

  public void setiCEName3(String iCEName3) {
    this.iCEName3 = iCEName3;
  }

  public Byte getiCERelation3() {
    return iCERelation3;
  }

  public void setiCERelation3(Byte iCERelation3) {
    this.iCERelation3 = iCERelation3;
  }

  public Date getLastApplyTime() {
    return lastApplyTime;
  }

  public void setLastApplyTime(Date lastApplyTime) {
    this.lastApplyTime = lastApplyTime;
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

  public Byte getRemind() {
    return remind;
  }

  public void setRemind(Byte remind) {
    this.remind = remind;
  }

  public Byte getIdentityFlag() {
    return identityFlag;
  }

  public void setIdentityFlag(Byte identityFlag) {
    this.identityFlag = identityFlag;
  }

  public Byte getPengYuanResult() {
    return pengYuanResult;
  }

  public void setPengYuanResult(Byte pengYuanResult) {
    this.pengYuanResult = pengYuanResult;
  }

  @Override
  public String toString() {
    return "CustInfoEntity{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", userPwd='" + userPwd + '\'' +
        ", userIcon='" + userIcon + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", idExpDate='" + idExpDate + '\'' +
        ", idCardImg='" + idCardImg + '\'' +
        ", mobile='" + mobile + '\'' +
        ", mobileMD5='" + mobileMD5 + '\'' +
        ", sex=" + sex +
        ", age=" + age +
        ", shengXiao='" + shengXiao + '\'' +
        ", education='" + education + '\'' +
        ", isDaily=" + isDaily +
        ", graduateDate=" + graduateDate +
        ", profession='" + profession + '\'' +
        ", school='" + school + '\'' +
        ", city='" + city + '\'' +
        ", province='" + province + '\'' +
        ", ext='" + ext + '\'' +
        ", iCETel1='" + iCETel1 + '\'' +
        ", iCEName1='" + iCEName1 + '\'' +
        ", iCERelation1=" + iCERelation1 +
        ", iCETel2='" + iCETel2 + '\'' +
        ", iCEName2='" + iCEName2 + '\'' +
        ", iCERelation2=" + iCERelation2 +
        ", iCETel3='" + iCETel3 + '\'' +
        ", iCEName3='" + iCEName3 + '\'' +
        ", iCERelation3=" + iCERelation3 +
        ", lastApplyTime=" + lastApplyTime +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", remind=" + remind +
        ", identityFlag=" + identityFlag +
        ", pengYuanResult=" + pengYuanResult +
        '}';
  }
}
