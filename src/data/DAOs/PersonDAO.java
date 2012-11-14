/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.PersonDTO;
import data.interfaces.DAOs.IPersonDAO;
import data.interfaces.DTOs.IPersonDTO;
import data.interfaces.DTOs.ISportDTO;
import data.interfaces.models.IAddress;
import data.interfaces.models.IContributionHistory;
import data.interfaces.models.ICountry;
import data.interfaces.models.IPerson;
import data.interfaces.models.IRole;
import data.interfaces.models.ISport;
import data.interfaces.models.ISportsman;
import data.interfaces.models.ISportsmanTrainingTeam;
import data.interfaces.models.ITournamentInvite;
import data.models.Person;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

/**
 *
 * @author Michael
 */
public class PersonDAO extends AbstractDAO<IPerson, IPersonDTO> implements IPersonDAO {

    private static IPersonDAO instance;

    public static IPersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }

    private PersonDAO() {
        super("data.models.Person");
    }

    @Override
    public IPerson create() {
        return new Person();
    }
    public IPerson getUserByUserName(String Username, Session s){
            Query query = s.createQuery("FROM Person WHERE username = :username");
        query.setString("username", Username);
        List<IPerson> persons= query.list();
        if (persons.size()>=1) {
        return persons.get(0);
        } 
        else return null;
   
    }
    @Override
    public List<IRole> getAllRoles(Session s, IPerson model) {

        Query query = s.createQuery("From " + "data.models.Role" + " WHERE person = :model");
        query.setParameter("model", model);
        return query.list();
    }

    @Override
    public List<IPerson> getLikeName(Session s, String name) {

        Query query = s.createQuery("FROM " + getTable() + " WHERE firstname LIKE '%" + name + "%' "
                + "OR lastname LIKE '%" + name + "%'");
        return query.list();
    }

    @Override
    public List<IPerson> getByLastName(Session s, String name) {

        Query query = s.createQuery("FROM " + getTable() + " WHERE lastname = :lname");
        query.setString("lname", name);
        return query.list();
    }

    @Override
    public List<IPerson> getByFirstName(Session s, String name) {

        Query query = s.createQuery("FROM " + getTable() + " WHERE firstname = :fname");
        query.setString("fname", name);
        return query.list();
    }
    
    @Override
    public IPerson getByUsername(Session s, String name) {

        if(name == null || name.equals("")) return null;
        
        Query query = s.createQuery("FROM " + getTable() + " WHERE username = :fname");
        query.setString("fname", name);
        return (IPerson)query.uniqueResult();
    }

    @Override
    public IPersonDTO extractDTO(IPerson model) {
        return new PersonDTO(model);
    }

    @Override
    public IPerson getById(Session s, int id) {

        Query query = s.createQuery("FROM " + getTable() + " where personID =:id");
        query.setInteger("id", id);
        return (IPerson) query.uniqueResult();
    }

    @Override
    public IPersonDTO saveDTO(Session s, IPersonDTO personDTO) {

        if (personDTO == null) {
            return null;
        }
        return new PersonDTO(saveDTOgetModel(s, personDTO));
    }

    @Override
    public IPerson saveDTOgetModel(Session s, IPersonDTO personDTO) {

        if (personDTO == null) {
            return null;
        }

        IPerson person = (personDTO.getId() == 0) ? null : getById(s, personDTO.getId());
        //IAddress address = 
        if (person == null) {
            person = create();
        }

        person.setFirstname(personDTO.getFirstname());
        person.setLastname(personDTO.getLastname());
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setPhone(personDTO.getPhone());
        //person.setRight(personDTO.getRight());
        person.setMail(personDTO.getMail());
        person.setSex(personDTO.getSex());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
        Date birthdate = null;
        try {
            birthdate = new Date(sdf.parse(personDTO.getBirthdate()).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        person.setBirthdate(birthdate);
        person.setMainAddress(AddressDAO.getInstance().saveDTOgetModel(s, personDTO.getMainAddress()));

        s.saveOrUpdate(person);

        IContributionHistory ch = ContributionHistoryDAO.getInstance().create();
        ch.setPerson(person);
        ch.setContribution(ContributionDAO.getInstance().getById(s, personDTO.getContribution().getId()));
        ch.setMonth(DateTime.now().getMonthOfYear());
        ch.setYear(DateTime.now().getYear());
        ch.setStatus("0");

        s.saveOrUpdate(ch);

        for (ISportDTO sport : personDTO.getSports()) {

            ISport sportModel = SportDAO.getInstance().getById(s, sport.getId());
            List<IRole> roleModels = RoleDAO.getInstance().getBySportAndPerson(s, sportModel, person);

            if (roleModels == null) {
                IRole role = RoleDAO.getInstance().create();
                role.setPerson(person);
                role.setSport(sportModel);
                RoleDAO.getInstance().add(s, role);
            } else {
                for (IRole r : roleModels) {
                    r.setPerson(person);
                    r.setSport(sportModel);
                    RoleDAO.getInstance().add(s, r);
                }
            }
        }
        return person;
    }

    @Override
    public IPersonDTO createPersonDTO() {

        ICountry country = CountryDAO.getInstance().create();
        IAddress address = AddressDAO.getInstance().create();
        address.setCountry(country);
        IPerson person = create();
        person.setMainAddress(address);

        return new PersonDTO(person);
    }

    @Override
    public void removeDTO(Session s, IPersonDTO dto) {

        IPerson person = getById(s, dto.getId());
        List<IRole> roles = RoleDAO.getInstance().getByPerson(s, person);
        List<ISportsmanTrainingTeam> stt = new LinkedList<>();
        List<ITournamentInvite> ti = new LinkedList<>();

        Transaction tx;

        if (roles != null && roles.size() > 0) {
            for (IRole role : roles) {
                if (role instanceof ISportsman) {
                    for (ISportsmanTrainingTeam stttemp : SportsmanTrainingTeamDAO.getInstance().getBySportsman(s, (ISportsman) role)) {
                        stt.add(stttemp);
                    }
                    for (ITournamentInvite titemp : TournamentInviteDAO.getInstance().getBySportsman(s, (ISportsman) role)) {
                        ti.add(titemp);
                    }
                }
            }


            tx = s.beginTransaction();



            SportsmanTrainingTeamDAO.getInstance().remove(s, stt);
            TournamentInviteDAO.getInstance().remove(s, ti);
            tx.commit();
//            RoleDAO.getInstance().remove(s, roles);

        }
        tx = s.beginTransaction();
        remove(s, person);
        tx.commit();

    }
}