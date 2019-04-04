/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ejb.Cadastros;
import ejb.cadastroFachada;
import ejb.vagasFachada;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author pablo
 */
@Named(value = "gerenciadorCadastros")
@RequestScoped
@ManagedBean
public class GerenciadorCadastros {

    @EJB
    private vagasFachada vagasFachada1;

    @EJB
    private cadastroFachada cadastroFachada;
    
    private int id;
    private String login;
    private String nome;
    private String senha;
    private String empresa;
    private String areaAtuacao;
    private String textoCurriculo;
    private String tipoUsuario;
    /**
     * Creates a new instance of GerenciadorCadastros
     */
    
    
    public GerenciadorCadastros() {
        this.login ="";
        this.senha = "";
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public cadastroFachada getCadastroFachada() {
        return cadastroFachada;
    }

    public void setCadastroFachada(cadastroFachada cadastroFachada) {
        this.cadastroFachada = cadastroFachada;
    }

    public String getTextoCurriculo() {
        return textoCurriculo;
    }
    
    public void setTextoCurriculo(String textoCurriculo) {
        this.textoCurriculo = textoCurriculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String curriculoBranco(){
        String curriculoBranco = "[Nome Completo] <br/>"+
        "Brasileiro, [Estado Civil], [Idade] anos <br/>"+
        "[Endereço – Rua/Av. + Número + Complemento] <br/>" +
        "[Bairro] – [Cidade] – [Estado] + <br/>" +
        "Telefone: [Telefone com DDD] / E-mail] <br/>" + 
        " <br/>" +
        "OBJETIVO <br/>" +
        "[Cargo Pretendido] <br/>" +
        " <br/>" +
        "FORMÃÇÃO <br/>" +
        "• 	<br/>" +
        "•	<br/>" +
        " <br/>" +
        "EXPERIÊNCIA PROFISSIONAL <br/>" +
        "•	[Período] – Empresa <br/>" +
        "Cargo: <br/>" +
        "Principais Atividades: <br/>" +
        "•	[Período] – Empresa <br/>" +
        "Cargo: <br/>" +
        "Principais Atividades: <br/>" +
        "•	[Período] – Empresa <br/>" +
        "Cargo: <br/>" +
        "Principais Atividades: <br/>" +
        " <br/>" +
        "QUALIFICAÇÕES E ATIVIDADES PROFISSIONAIS <br/>" +
        "•	[Descrição] ([Local], conclusão em [Ano de Conclusão Do Curso ou Atividade]). <br/>" +
        "•	[Descrição] ([Local], conclusão em [Ano de Conclusão Do Curso ou Atividade]). <br/>" +
        "•	[Descrição] ([Local], conclusão em [Ano de Conclusão Do Curso ou Atividade]). <br/>" +
        " <br/>" +
        "INFORMAÇÕES ADICIONAIS <br/>" +
        "•	[Descrição Informação Adicional] <br/>" +
        "•	[Descrição Informação Adicional] <br/>" +
        "•	[Descrição Informação Adicional] <br/>";
        
        return curriculoBranco;
    }
    
    public void cadastrarUsuario(){
        if(nome.equals("") || senha.equals("") || areaAtuacao.equals("") || tipoUsuario == null){
            camposObrigatoriosEMbranco();
        }else if(buscarIdCliente(login)==0){
            
            int id = cadastroFachada.getMaiorID();
            cadastroFachada.persist(new Cadastros(id, login, nome, senha, empresa, areaAtuacao, curriculoBranco(), Integer.parseInt(tipoUsuario)));
            
            loginCadastrado();
        }else{
            loginDuplicado();
        }
        limparTela();
    }
    
    public Integer buscarIdCliente(String login){
        int codCLiente = 0;
        
        List<Cadastros> listaCadastros = cadastroFachada.getListaLogins();
        for(Cadastros usuario: listaCadastros ){
            if(usuario.getLogin().equals(login)){
                codCLiente = usuario.getId();
                break;
            }
        }
        return codCLiente;
    }
    
    
    public String realizarLogin(){
        int codPagina = -1;
        for(Cadastros cliente : cadastroFachada.getListaLogins()){
            if(cliente.getSenha().equals(senha) && cliente.getLogin().equals(login)){
                cadastroFachada.setLogin(cliente.getLogin()); //Guarda ID para futuras operações
                textoCurriculo = cliente.getCurriculo();
                vagasFachada1.setAreaAt(cliente.getAtuacao());
                
                vagasFachada1.setIdEMP(cliente.getLogin());
                
                codPagina = cliente.getTipousuario();
                break;
            }else{
                codPagina = 3;
            }
        }
        limparTela();
        switch (codPagina) {
            case 1:
                return "empregado";
            case 2:
                return "empregador";
            default:
                loginNaoEncontrado();
                return "index";
        }
        
    }
    /*
    public void updateCurriculo(){
        Integer codId = buscarIdCliente(cadastroFachada.getLogin());
        
        cadastroFachada.updateCurriculo(codId, textoCurriculo);
        curriculoAtulizado();
    }*/
    
    public void updateCadastro(){
        if(nome.equals("") || senha.equals("") || empresa.equals("")|| areaAtuacao.equals("") || textoCurriculo.equals("<br><canvas id=\"netbeans_glasspane\" :netbeans_generated=\"true\" style=\"position: fixed; top: 0px; left: 0px; z-index: 50000; pointer-events: none;\" width=\"600\" height=\"197\"></canvas>")){
            camposObrigatoriosEMbranco();
        }else{
            Integer codId = buscarIdCliente(cadastroFachada.getLogin());
            cadastroFachada.updateCadastro(codId,nome, senha, empresa, areaAtuacao,textoCurriculo);
            cadastroAtualizado();
        }
    }
    
    public List<Cadastros> getCurriculosNaVga(Integer codVaga){
        return cadastroFachada.getCadastrosDaVaga(codVaga);
    }
    
    public void limparTela(){
        this.login = "";
        this.senha ="";
        this.nome="";
        this.empresa="";
        this.areaAtuacao="";
    }
    
    public String novoCadastro(){
        limparTela();
        return "cadastroNovo";
    }
    
    //Mensagens
    public void loginDuplicado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Login já cadastrado."));
    }
    
    public void camposObrigatoriosEMbranco() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Branco", "Verifique os campos com preenchimentório obrigatório(*)."));
    }
    public void loginCadastrado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado", "Usuário cadastrado com sucesso."));
    }
    
    public void loginNaoEncontrado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não cadastrado", "Login ou senha incorreto."));
    }
    
    public void cadastroAtualizado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado", "Cadastro atualizado."));
    }
    public void curriculoAtulizado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado", "Curriculo atualizado."));
    }
}
