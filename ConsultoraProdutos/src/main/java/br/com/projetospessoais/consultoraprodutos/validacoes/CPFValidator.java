package br.com.projetospessoais.consultoraprodutos.validacoes;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Classe que representa um validador e tem como responsabilidade validar um CPF
 * inserido em telas da camada de apresentação.
 *
 * Créditos ao JavaFree.org pelo algoritmo de validação de CPF
 */
@FacesValidator(value = "cpfValidator")
public class CPFValidator implements Validator {

    /**
     * Método padrão do validador sobrescrito da interface Validator
     *
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }
        String cpf = (String) value;

        if (cpf.length() != 11 || !calcularDigitoVerificador(cpf.substring(0, 9)).equals(cpf.substring(9, 11))) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido.", "Favor informar um CPF válido."));
        }
    }

    @SuppressWarnings({"UnnecessaryBoxing", "UnnecessaryUnboxing"})
    private String calcularDigitoVerificador(String num) {
        //if condicional para verificar se o CPF tem números iguais nos nove primeiros digitos. 
        if (!(num.substring(0, 3).equals(num.substring(3, 6)))) {
            //Calculo do 1º dígito
            Integer primDig, segDig;
            int soma = 0;
            int peso = 10;
            for (int i = 0; i < num.length(); i++) {
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
            }
            if (soma % 11 == 0 || soma % 11 == 1) {
                primDig = new Integer(0);
            } else {
                primDig = new Integer(11 - (soma % 11));
            }

            //Calculo do 2º dígito
            soma = 0;
            peso = 11;
            for (int i = 0; i < num.length(); i++) {
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
            }
            soma += primDig.intValue() * 2;
            if (soma % 11 == 0 || soma % 11 == 1) {
                segDig = new Integer(0);
            } else {
                segDig = new Integer(11 - (soma % 11));
            }
            return primDig.toString() + segDig.toString();
        }
        return "Erro de validação desconhecido em: CPFValidator";
    }
}
