/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces.models;

/**
 *
 * @author uubu
 */
public interface IRoleRights extends IModel {

    String getName();

    Long getRight();

    int getRoleRightsID();

    void setName(String name);

    void setRight(Long right);

    void setRoleRightsID(int roleRightsID);
    
}
