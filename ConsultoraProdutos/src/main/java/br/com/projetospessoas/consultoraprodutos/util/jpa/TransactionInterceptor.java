package br.edu.ifpb.monteiro.ads.infosaude.atendimento.util.jpa;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/** 
 * Intercept any call by a transactional method or class and manage the procedures of transaction.
 * 
 * Before or after of execution of the method assigned with the @Transactional annotation, this object
 * intercept him and call the begin, roolback or commit methods.
 * 
 * @see Transactional
 ** Based on estructure of codes of Algaworks GitHub Repository (http://github.com/algaworks)
 */
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private @Inject EntityManager entityManager;
    
    /* The annotation @AroundInvoke sign this method for to be called automatically before the 
    method annotated with @Transactional */
    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception{
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean owner = false;
        
        try {
            if(!entityTransaction.isActive()){
                //A way to grant any operation pending will be rollback, preventing possible errors.
                entityTransaction.begin();   
                entityTransaction.rollback();
                
                // Now, after check about posible errors, the transaction is started.
                entityTransaction.begin();
                owner = true;
            }
            //After  transaction started, the content of any method intercepted as @Transactional is run.
            return context.proceed();
        } catch (Exception e) {
            if(entityTransaction != null && owner){
                //Any anomaly operation and the changes are undone
                entityTransaction.rollback();
            }
            throw e;
        }finally{
            if(entityTransaction != null && entityTransaction.isActive() && owner){
                //Finaly, without any errors, the changes are sended to the database.
                entityTransaction.commit();
            }
        }
    }
    
}
