/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces.DTOs;

import data.interfaces.models.ITrainingTeam;
import java.util.List;

/**
 *
 * @author uubu
 */
public interface ITrainingTeamDTO extends IDTO<ITrainingTeam>{

//    List<ICoachDTO> getCoaches();

    IDepartmentDTO getDepartment();

//    List<ISportsmanTrainingTeamDTO> getSportsmen();

//    void setCoaches(List<ICoachDTO> coaches);

    void setDepartment(IDepartmentDTO department);

//    void setSportsmen(List<ISportsmanTrainingTeamDTO> sportsmen);

    //    @Override
    //    public List<ICoachDTO> getCoaches() {
    //        return coaches;
    //    }
    //
    //    @Override
    //    public void setCoaches(List<ICoachDTO> coaches) {
    //        this.coaches = coaches;
    //    }
    ISportDTO getSport();

    void setSport(ISportDTO sport);

    ILeagueDTO getLeague();

    void setLeague(ILeagueDTO league);
    
}
