/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.role.EditPersonRole.state;

import business.controller.RMI.AController;
import business.controller.role.EditPersonRole.IEditPersonRole;
import business.controller.role.RoleController;
import data.interfaces.DTOs.IDepartmentDTO;
import data.interfaces.DTOs.IPersonDTO;
import data.interfaces.DTOs.IRoleRightsDTO;
import data.interfaces.DTOs.ISportDTO;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author phil
 */
public class EditPersonRoleState extends AController implements IEditPersonRoleState {

    IEditPersonRole _editor;

    public EditPersonRoleState(IEditPersonRole editor) throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public List<IRoleRightsDTO> loadRoleRightsOfPerson(IPersonDTO person) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void EditPersonRole(IPersonDTO person, List<IRoleRightsDTO> roles, IDepartmentDTO department, ISportDTO sport) throws  RemoteException{
        RoleController.getInstance().EditPersonRole(person, roles, department, sport);
    }

    @Override
    public List<IRoleRightsDTO> loadRoleRights() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
