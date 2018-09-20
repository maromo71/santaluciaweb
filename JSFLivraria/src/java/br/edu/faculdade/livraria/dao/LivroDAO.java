package br.edu.faculdade.livraria.dao;

import br.edu.faculdade.livraria.model.Livro;
import java.util.List;

/**
 *
 * @author maromo
 */
public interface LivroDAO {
     public void adicionarLivro(Livro livro);

    public List<Livro> listarLivro();

    public void alterarLivro(Livro livro);
    
    public void excluirLivro(Livro livro);
}
