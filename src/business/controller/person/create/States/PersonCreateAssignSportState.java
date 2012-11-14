/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.create.States;

import business.controller.RMI.AController;
import business.controller.person.PersonController;
import business.controller.person.create.PersonCreation;
import data.DAOs.PersonDAO;
import data.DAOs.RoleDAO;
import data.DAOs.RoleRightsDAO;
import data.DAOs.SportsmanDAO;
import data.DTOs.PersonDTO;
import data.hibernate.HibernateUtil;
import data.interfaces.DTOs.IContributionDTO;
import data.interfaces.DTOs.ICountryDTO;
import data.interfaces.DTOs.IPersonDTO;
import data.interfaces.DTOs.ISportDTO;
import data.interfaces.models.IPerson;
import data.interfaces.models.IRoleRights;
import data.interfaces.models.ISport;
import data.interfaces.models.ISportsman;
import data.models.Role;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import net.sf.ehcache.distribution.RemoteCacheException;
import org.hibernate.Transaction;

/**
 *
 * @author phil
 */
public class PersonCreateAssignSportState extends AController implements IPersonCreateState {

    PersonCreation _creator;

    public PersonCreateAssignSportState(PersonCreation creator) throws RemoteException {
        super();
        _creator = creator;
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PersonDTO CreatePerson(String firstname, String lastname,
            String sex, String phone, String mail,
            String username, String password, Date birthday, int right,
            String street, String postcode, String city, String country, int contributionID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException {

        Transaction tx = HibernateUtil.getCurrentSession().beginTransaction();
        for (String sportname : sport) {
            //assigning values
            IPerson p = PersonController.getInstance().loadPersonWithIDNonDTO(personID);
            ISport s = PersonController.getInstance().loadSport(sportname);
            IRoleRights r = RoleRightsDAO.getInstance().getById(HibernateUtil.getCurrentSession(), 2);

            ISportsman sportsman = SportsmanDAO.getInstance().create();
            sportsman.setPerson(p);
            sportsman.setDepartment(s.getDepartment());
            sportsman.setSport(s);
            sportsman.setRoleRight(r);
            SportsmanDAO.getInstance().add(HibernateUtil.getCurrentSession(), sportsman);
        }

        tx.commit();
    }

    @Override
    public IPersonDTO CreatePersonDTO() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO SaveDTO(IPersonDTO dto) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
