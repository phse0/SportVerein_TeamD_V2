/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces.DTOs;

import data.interfaces.models.ICountry;

/**
 *
 * @author uubu
 */
public interface ICountryDTO extends IDTO<ICountry> {
    
    String getName();
    void setName(String name);
    
    String getCode();
    void setCode(String code);
}
