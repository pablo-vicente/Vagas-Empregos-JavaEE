/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "CADASTROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cadastros.findAll", query = "SELECT c FROM Cadastros c")
    , @NamedQuery(name = "Cadastros.findById", query = "SELECT c FROM Cadastros c WHERE c.id = :id")
    , @NamedQuery(name = "Cadastros.findByLogin", query = "SELECT c FROM Cadastros c WHERE c.login = :login")
    , @NamedQuery(name = "Cadastros.findByNome", query = "SELECT c FROM Cadastros c WHERE c.nome = :nome")
    , @NamedQuery(name = "Cadastros.findBySenha", query = "SELECT c FROM Cadastros c WHERE c.senha = :senha")
    , @NamedQuery(name = "Cadastros.findByEmpresa", query = "SELECT c FROM Cadastros c WHERE c.empresa = :empresa")
    , @NamedQuery(name = "Cadastros.findByAtuacao", query = "SELECT c FROM Cadastros c WHERE c.atuacao = :atuacao")
    , @NamedQuery(name = "Cadastros.findByCurriculo", query = "SELECT c FROM Cadastros c WHERE c.curriculo = :curriculo")
    , @NamedQuery(name = "Cadastros.findByTipousuario", query = "SELECT c FROM Cadastros c WHERE c.tipousuario = :tipousuario")})
public class Cadastros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 30)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 30)
    @Column(name = "SENHA")
    private String senha;
    @Size(max = 30)
    @Column(name = "EMPRESA")
    private String empresa;
    @Size(max = 30)
    @Column(name = "ATUACAO")
    private String atuacao;
    @Size(max = 2000)
    @Column(name = "CURRICULO")
    private String curriculo;
    @Column(name = "TIPOUSUARIO")
    private Integer tipousuario;

    public Cadastros() {
    }

    public Cadastros(Integer id, String login, String nome, String senha, String empresa, String atuacao, String curriculo, Integer tipousuario) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.empresa = empresa;
        this.atuacao = atuacao;
        this.curriculo = curriculo;
        this.tipousuario = tipousuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getAtuacao() {
        return atuacao;
    }

    public void setAtuacao(String atuacao) {
        this.atuacao = atuacao;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public Integer getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(Integer tipousuario) {
        this.tipousuario = tipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cadastros)) {
            return false;
        }
        Cadastros other = (Cadastros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Cadastros[ id=" + id + " ]";
    }
    
}
