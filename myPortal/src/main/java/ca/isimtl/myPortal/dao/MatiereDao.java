/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.isimtl.myPortal.dao;

import ca.isimtl.myPortal.model.Matiere;
import java.util.List;

/**
 *
 * @author Francois YOUALEU
 */
public interface MatiereDao {
    
    Matiere findById(int id);
    
    void saveMatiere(Matiere uneMatiere);
    
    void deleteMatiere(Matiere uneMat);
    
    List<Matiere> getAll();    
     
}
