/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.MatchDTO;
import data.interfaces.DAOs.IMatchDAO;
import data.interfaces.DTOs.IMatchDTO;
import data.interfaces.models.IMatch;
import data.models.Match;

/**
 *
 * @author uubu
 */
public class MatchDAO extends AbstractDAO<IMatch, IMatchDTO> implements IMatchDAO{

    private static IMatchDAO instance;
    
    private MatchDAO(){
        super("data.models.Match");
    }
    
    public static IMatchDAO getInstance(){
        if(instance == null){
            instance = new MatchDAO();
        }
        return instance;
    }
    
    @Override
    public IMatch create() {
        return new Match();
    }

    @Override
    public IMatchDTO extractDTO(IMatch model) {
        return new MatchDTO(model);
    }
    
}
