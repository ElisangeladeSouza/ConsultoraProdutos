package br.com.projetospessoais.consultoraprodutos.dao;

import br.com.projetospessoais.consultoraprodutos.dao.interfaces.Dao;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Esta classe representa um DAO genérico e contém todos os métodos básicos para
 * efetuar um CRUD.
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 * @param <T>
 */
public abstract class DaoAbstract<T> implements Dao<T>, Serializable {
    
    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(DaoAbstract.class);

    @Inject
    private transient EntityManager entityManager;

    private Class<T> entity;

    /**
     *
     */
    public DaoAbstract() {
    }

    /**
     * Construtor da classe que captura a entidade que chamar esta classe.
     *
     * @param entityClass
     */
    public DaoAbstract(Class<T> entityClass) {
        this.entity = entityClass;
    }

    /**
     * Método get para a instância do EntityManager
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param entity
     */
    @Override
    public void salvar(T entity) {
        entityManager.merge(entity);
    }

    /**
     * Método utilizado para remover um cadastro do banco de dados
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Método utilizado para retornar uma lista com todos os resultados
     * encontrados no banco de dados para a esntidade que a chamar. A consulta é
     * feita através de Criteria
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Método utilizado para buscar um registro no banco de dados para
     * determinada entidade através da passagem do seu ID como parâmetro.
     *
     * @param id
     * @return
     */
    @Override
    public T findById(Long id) {
        return entityManager.find(entity, id);
    }

    /**
     * @param campo
     * @param valor
     * @return
     */
    @Override
    public T buscarPorCampo(String campo, Object valor) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> createQuery = criteriaBuilder.createQuery(entity);
            Root<T> root = createQuery.from(entity);
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.<T>get(campo), valor));
            createQuery.where(predicate);
            return entityManager.createQuery(createQuery).getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.info("Informação não encontrada" + ex.getMessage());
        }
        return null;
    }

    /**
     * Método set da instância do entityManager necessária para a aplicação de
     * testes de unidade.
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
//    public List<T> query(String query, Object... params) {
//        List<T> result = null;
//        Query q = entityManager.createQuery(query);
//        int paramPos = 1;
//        for (Object o : params) {
//            q.setParameter(paramPos++, o);
//        }
//        result = q.getResultList();
//        return result;
//    }

}
