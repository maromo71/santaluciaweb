/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.faculdade.livraria.control;

import br.edu.faculdade.livraria.dao.LivroDAO;
import br.edu.faculdade.livraria.dao.LivroDAOImpl;
import br.edu.faculdade.livraria.model.Livro;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author maromo
 */
@ManagedBean(name = "LivroController")
@SessionScoped
public class LivroController {
private Livro livro;
    private DataModel<Livro> listaDeLivros;
    private String msg;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public DataModel<Livro> getListaDeLivros() {

        LivroDAO dao = new LivroDAOImpl();
        List<Livro> lista = dao.listarLivro();
        listaDeLivros = new ListDataModel<Livro>(lista);
        return listaDeLivros;
    }

    public void setListaDeLivros(DataModel<Livro> listaDeLivros) {
        this.listaDeLivros = listaDeLivros;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String adicionarForm() {
        livro = new Livro();
        return "InserirLivro_Form";
    }

    public String alterarForm(Livro l) {
        livro = l;
        return "AlterarLivro_Form";
    }

    public String adicionarLivro() {
        LivroDAO dao = new LivroDAOImpl();
        dao.adicionarLivro(livro);
        setMsg("Livro salvo com sucesso!");
        return "ListarLivros_Form";
    }

    public String excluirLivro() {
        Livro l = (Livro) (listaDeLivros.getRowData());
        LivroDAO dao = new LivroDAOImpl();
        dao.excluirLivro(l);
        setMsg("Livro Excluído com sucesso!");
        return "ListarLivros_Form";
    }
    ///Trabalhar aqui

    public String alterarLivro() {
        LivroDAO dao = new LivroDAOImpl();
        dao.alterarLivro(livro);
        setMsg("Livro alterado com sucesso!");
        return "ListarLivros_Form";
    }

    public String listarLivros() {

        return "ListarLivros_Form";
    }

    @PostConstruct
    public void init() {
        livro = new Livro();
    }    
}
