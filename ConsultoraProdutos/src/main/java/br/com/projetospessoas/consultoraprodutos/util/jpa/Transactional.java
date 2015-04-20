package br.edu.ifpb.monteiro.ads.infosaude.atendimento.util.jpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

/**
 * Create the @Transactional annotation to indicate any method or class to do transaction in database.
 * 
 * @see TransactionInterceptor
 ** Based on estructure of codes of Algaworks GitHub Repository (http://github.com/algaworks)
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Transactional {

}
