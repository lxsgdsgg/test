package com.bashiju.base.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysAdminEmailList implements Serializable {
    private Integer id;


    private String adminname;

    private String adminphone;

    private String adminemail;

    private String adminweixin;

    private String adminaddress;

    /**
     * 是否发送系统异常消息
     */
    private Byte issendmsg;

    /**
     * 负责模块id
     */
 
    private Integer headmoduleid;

    /**
     * 负责模块
     */
    private String headmodule;

    private String permissionarea;


    private Integer operatorid;

    private String operator;

    private Date addtime;

    private Date updatetime;

    private Byte isvalid;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return adminName
     */
    public String getAdminname() {
        return adminname;
    }

    /**
     * @param adminname
     */
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    /**
     * @return adminPhone
     */
    public String getAdminphone() {
        return adminphone;
    }

    /**
     * @param adminphone
     */
    public void setAdminphone(String adminphone) {
        this.adminphone = adminphone;
    }

    /**
     * @return adminEmail
     */
    public String getAdminemail() {
        return adminemail;
    }

    /**
     * @param adminemail
     */
    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    /**
     * @return adminWeiXin
     */
    public String getAdminweixin() {
        return adminweixin;
    }

    /**
     * @param adminweixin
     */
    public void setAdminweixin(String adminweixin) {
        this.adminweixin = adminweixin;
    }

    /**
     * @return adminAddress
     */
    public String getAdminaddress() {
        return adminaddress;
    }

    /**
     * @param adminaddress
     */
    public void setAdminaddress(String adminaddress) {
        this.adminaddress = adminaddress;
    }

    /**
     * 获取是否发送系统异常消息
     *
     * @return isSendMsg - 是否发送系统异常消息
     */
    public Byte getIssendmsg() {
        return issendmsg;
    }

    /**
     * 设置是否发送系统异常消息
     *
     * @param issendmsg 是否发送系统异常消息
     */
    public void setIssendmsg(Byte issendmsg) {
        this.issendmsg = issendmsg;
    }

    /**
     * 获取负责模块
     *
     * @return headModuleId - 负责模块
     */
    public Integer getHeadmoduleid() {
        return headmoduleid;
    }

    /**
     * 设置负责模块
     *
     * @param headmoduleid 负责模块
     */
    public void setHeadmoduleid(Integer headmoduleid) {
        this.headmoduleid = headmoduleid;
    }

    /**
     * 获取负责模块
     *
     * @return headModule - 负责模块
     */
    public String getHeadmodule() {
        return headmodule;
    }

    /**
     * 设置负责模块
     *
     * @param headmodule 负责模块
     */
    public void setHeadmodule(String headmodule) {
        this.headmodule = headmodule;
    }

    /**
     * @return permissionArea
     */
    public String getPermissionarea() {
        return permissionarea;
    }

    /**
     * @param permissionarea
     */
    public void setPermissionarea(String permissionarea) {
        this.permissionarea = permissionarea;
    }

    /**
     * @return operatorId
     */
    public Integer getOperatorid() {
        return operatorid;
    }

    /**
     * @param operatorid
     */
    public void setOperatorid(Integer operatorid) {
        this.operatorid = operatorid;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return addTime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return isvalid
     */
    public Byte getIsvalid() {
        return isvalid;
    }

    /**
     * @param isvalid
     */
    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adminname=").append(adminname);
        sb.append(", adminphone=").append(adminphone);
        sb.append(", adminemail=").append(adminemail);
        sb.append(", adminweixin=").append(adminweixin);
        sb.append(", adminaddress=").append(adminaddress);
        sb.append(", issendmsg=").append(issendmsg);
        sb.append(", headmoduleid=").append(headmoduleid);
        sb.append(", headmodule=").append(headmodule);
        sb.append(", permissionarea=").append(permissionarea);
        sb.append(", operatorid=").append(operatorid);
        sb.append(", operator=").append(operator);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", isvalid=").append(isvalid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}