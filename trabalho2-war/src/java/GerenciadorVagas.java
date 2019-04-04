/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.Vagascandidatadas;
import ejb.Vagasemprego;
import ejb.cadastroFachada;
import ejb.vagasCandidatadasFachada;
import ejb.vagasFachada;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pablo
 */
@Named(value = "GerenciadorVagas")
@RequestScoped
public class GerenciadorVagas {
    
    String tipo = null;
    String vaga = null;
    
    @EJB
    private cadastroFachada cadastroFachada;
    @EJB
    private vagasCandidatadasFachada vagasCandidatadasFachada;
    @EJB
    private vagasFachada vagasFachada;
    private int codigoVaga;
    private int tipoVaga;
    private String areaAtuacao;
    private String empresa;
    private String descricao;
    private int remuneracao;
    
    private int idEmpregador;
    /**
     * Creates a new instance of GerenciadorJFS
     */
    public GerenciadorVagas() {
    }

    public int getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(int tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(int remuneracao) {
        this.remuneracao = remuneracao;
    }

    public int getCodigoVaga() {
        return codigoVaga;
    }

    public void setCodigoVaga(int codigoVaga) {
        this.codigoVaga = codigoVaga;
    }

    public int getIdEmpregador() {
        return idEmpregador;
    }

    public void setIdEmpregador(int idEmpregador) {
        this.idEmpregador = idEmpregador;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }
    
    public List<Vagascandidatadas> getListaIdVagasCandidatadas (){
        return vagasCandidatadasFachada.getListadeIdVagas();
    }
    
    public List<Vagascandidatadas> getListaIdLogin (){
        return vagasCandidatadasFachada.getListadeIdLogins();
    }
    
    public List<Vagascandidatadas> getVagasCamdidatadas() {
        return vagasCandidatadasFachada.getListaVagaCandidatadas();
    }    
    
    public List<Vagasemprego> getListaVagas() {
        return vagasFachada.getListaVagas();
    }

    public vagasFachada getVagasFachada() {
        return vagasFachada;
    }

    public List<Vagasemprego> getVagasDisponiveisPelaArea() {
        return vagasFachada.getListVagasDisponiveis();
    }
    
     public List<Vagasemprego> getVagasDisponiveisPorEmpregador() {
        return vagasFachada.getListVagasDisponiveisEmpregador();
    }
    
    public void enviarCurriculo(){
        int cod = 0;
        String login = cadastroFachada.getLogin();
        
        if(buscarCodigoRegistro(login, codigoVaga)==0){
            for(Vagasemprego vaga : getVagasDisponiveisPelaArea()){
                if(vaga.getIdvaga()==codigoVaga){
                    cod = 1;
                    vagasCandidatadasFachada.persist(new Vagascandidatadas(vagasCandidatadasFachada.getMaiorID(), login, codigoVaga));
                    break;
                }
            }
        }else{
            cod =2;
        }
        switch(cod){
            case 1:
                curriculoEnviado();
                break;
            case 0:
                codigoVagaNaoEncontrado();
                break;
            case 2:
                candidaturaDuplicada();
                break;
        }
    }
    
    public int buscarCodigoRegistro(String login, int idVaga){
       int codRegistro =0;
        for(Vagascandidatadas vaga:vagasCandidatadasFachada.getListaVagaCandidatadas()){
            if(vaga.getLoginusuario().equals(login) && vaga.getIdvagaemprego()==idVaga){
                codRegistro = vaga.getIdregistro();
            }
        }
        return codRegistro;
    }
    
    public void cadastrarVaga(){
        
        if(tipoVaga==0 || areaAtuacao.equals("") || empresa.equals("") || descricao.equals("") || remuneracao==0){
            camposObrigatoriosEMbranco();
        }else {
            switch(tipoVaga){
                case 1:
                    tipo = "Estágio";
                    break;
                case 2:
                    tipo = "Emprego";
                    break;
            }   
            vagasFachada.persist(new Vagasemprego(vagasFachada.getMaiorID(),tipo,areaAtuacao.toUpperCase(),empresa,descricao, remuneracao,cadastroFachada.getLogin()));
            vagaCadastrada();
            limpar();
        }
    }
    
    public void fecharVaga(){
       
        if(vaga.equals("")){
            camposObrigatoriosEMbranco();
        }else { 
            //vagasFachada.persist(new Vagasemprego(vagasFachada.getMaiorID(),tipo,areaAtuacao.toUpperCase(),empresa,descricao, remuneracao,cadastroFachada.getLogin()));
            vagasFachada.fecharVaga(vaga);
            vagaFechada();
            limpar();
        }
    }
    
    
    public void limpar(){
        tipoVaga = 0;
        areaAtuacao= "";
        empresa = "";
        descricao ="";
        remuneracao = 0;
    }
    
    public void vagaCadastrada() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrada", "Vaga cadastrada com sucesso."));
    }
    
    public void vagaFechada() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fechada", "Vaga fechada com sucesso."));
    }
    
    
    public void curriculoEnviado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enivado", "Curriculo enviado com sucesso aguarde o contato do empregador."));
    }
    
    public void codigoVagaNaoEncontrado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Indisponivel", "Código de vaga incorreto ou vaga está indisponível."));
    }
    
    public void candidaturaDuplicada() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já candidatado", "Você já candidatou-se para está vaga.."));
    }
    
    public void camposObrigatoriosEMbranco() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Branco", "Verifique os campos com preenchimentório obrigatório(*)."));
    }
}