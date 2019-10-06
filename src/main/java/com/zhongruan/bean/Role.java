package com.zhongruan.bean;

public class Role {
    private int id;
    private  String rolename;
    private  String roleDesc;

    public Role(){

    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + rolename + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return rolename;
    }

    public void setRoleName(String roleName) {
        this.rolename = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
