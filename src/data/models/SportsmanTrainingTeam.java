/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.models;

import data.interfaces.models.ISportsman;
import data.interfaces.models.ISportsmanTrainingTeam;
import data.interfaces.models.ITrainingTeam;

/**
 *
 * @author Michael
 */
public class SportsmanTrainingTeam implements ISportsmanTrainingTeam {
    
    private int sportsmanTrainingTeamID;
    private ISportsman sportsman;
    private ITrainingTeam team;
    private String position;

    public SportsmanTrainingTeam() {
    }

    public SportsmanTrainingTeam(ISportsman sportsman, ITrainingTeam team, String position) {
        this.sportsman = sportsman;
        this.team = team;
        this.position = position;
    }

    @Override
    public int getSportsmanTrainingTeamID() {
        return sportsmanTrainingTeamID;
    }

    @Override
    public void setSportsmanTrainingTeamID(int sportsmanTrainingTeamID) {
        this.sportsmanTrainingTeamID = sportsmanTrainingTeamID;
    }
    
    @Override
    public ISportsman getSportsman() {
        return sportsman;
    }

    @Override
    public void setSportsman(ISportsman sportsman) {
        this.sportsman = sportsman;
    }

    @Override
    public ITrainingTeam getTeam() {
        return team;
    }

    @Override
    public void setTeam(ITrainingTeam team) {
        this.team = team;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }
    
    @Override
    public String getTeamName() {
        return this.team.getName();
    }
    
    @Override
    public String getSportsmanName() {
        return this.sportsman.getPersonName();
    }
    
    
}
