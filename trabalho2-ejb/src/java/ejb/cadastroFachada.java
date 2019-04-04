/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pablo
 */
@Stateless
@LocalBean
public class cadastroFachada {
  @PersistenceContext (name = "trabalho2-ejbPU") 
    EntityManager em;  

    public static int id = 1;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public void updateCadastro(Integer codId,String nome, String senha, String empresa, String areaAtuacao, String textoCurriculo){
        Query query = em.createNativeQuery("update cadastros set nome = ?, senha = ?, empresa = ?, atuacao = ?, curriculo = ? where id = ? ");
        query.setParameter(1, nome);
        query.setParameter(2, senha);
        query.setParameter(3, empresa);
        query.setParameter(4, areaAtuacao);
        query.setParameter(5, textoCurriculo);
        query.setParameter(6, codId);
        query.executeUpdate();
    }
    /*
    public void updateCurriculo(Integer cod, String textoCurriculo){
        Query query = em.createNativeQuery("update cadastros set curriculo = ? where id = ? ");
        query.setParameter(1, textoCurriculo);
        query.setParameter(2, cod);
        query.executeUpdate();
    }*/
    
    public void persist(Object object) {
       em.persist(object);
       //id++;
    }
    
    public List<Cadastros> getCadastrosDaVaga(Integer idVaga) {
        Query query = em.createNativeQuery("SELECT loginusuario FROM vagascandidatadas WHERE idvagaemprego = ?");
        query.setParameter(1, idVaga);
        List<String> logins = query.getResultList();
        List<Cadastros> cadastros = new ArrayList<>();
        for (String login : logins) {
            Query query2 = em.createNamedQuery("Cadastros.findByLogin");
            query2.setParameter("login", login);
            cadastros.add((Cadastros)query2.getSingleResult());
        }
        return cadastros;
    }
    
    public List<Cadastros> getListaLogins() {
        Query query = em.createNamedQuery("Cadastros.findAll");
        return query.getResultList();
    }
    
    public Integer getMaiorID(){
        Integer maior = 0;
        for(Cadastros cad : getListaLogins()){
            if(cad.getId()>maior){
                maior = cad.getId();
            }
        }
        return maior+1;
    }
}



