/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces.DTOs;

import data.interfaces.models.ITeam;

/**
 *
 * @author uubu
 */
public interface ITeamDTO extends IDTO<ITeam> {

    void extract(ITeam model);

    String getName();

    //ISportDTO getSport();
//
//    List<ITournamentDTO> getTournaments();

    void setName(String name);

    //void setSport(ISportDTO sport);

//    void setTournaments(List<ITournamentDTO> tournaments);

    ILeagueDTO getLeague();

    void setLeague(ILeagueDTO league);
    
}
