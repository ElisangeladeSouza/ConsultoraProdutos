package br.com.projetospessoais.consultoraprodutos.dao.interfaces;

import java.util.List;

/**
 * Interface da camada de dados que contém todos as assinaturas de métodos
 * necessários para fazer um CRUD e métodos adicionais usados em outras partes
 * do sistema.
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 * @param <T>
 */
public interface Dao<T> {

    /**
     * Metodo utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param entity
     */
    public void salvar(T entity);

    /**
     * Método utilizado para remover um cadastro do banco de dados
     *
     * @param entity
     */
    public void delete(T entity);

    /**
     * Método utilizado para retornar uma lista com todos os resultados
     * encontrados no banco de dados para a entidade que a chamar.
     *
     * @return
     */
    public List<T> findAll();

    /**
     * Método utilizado para buscar um registro no banco de dados para
     * determinada entidade através da passagem do seu ID como parâmetro.
     *
     * @param id
     * @return
     */
    public T findById(Long id);

    /**
     * Faz uma consulta no banco de dados baseado em um valor passado como
     * parâmetro e retorna o resultado da consulta.
     *
     * @param campo
     * @param valor
     * @return
     */
    public T buscarPorCampo(String campo, Object valor);

    /**
     * Recebe o valor passado pelo método buscarPorCampo() para determinar a
     * duplicidade do cadastro e lança uma exceção informando ao usuário qual
     * campo não pode ser inserido por já existir no banco de dados.
     *
     * @param campo
     * @param valor
     * @param id
     * @param entidade
     * @return
     */
//    public boolean checaCampoDuplicado(String campo, Object valor, Long id, T entidade) throws NegocioException ;

}
