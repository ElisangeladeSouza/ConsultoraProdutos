package br.com.projetospessoais.consultoraprodutos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;

/**
 *
 * @author elisangela <elysangeladesouza@gmail.com>
 */
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private Long id;
    
    @Column(name = "nome_cliente", length = 30)
    private String nome;
    
    @Column(name = "produto", length = 30)
    private String produto;
    
    @Column(name = "valor")
    private double valor;
    
    @Column(name = "total")
    private double total;

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
