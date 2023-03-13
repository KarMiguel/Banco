
package entities;

import static entities.ContaBanco.getListaContas;
import java.time.LocalDate;
import java.time.Period;

public class Cliente  {
    
    protected String nome;
    protected String cpf;
    protected LocalDate dataNasc;

    public Cliente(String nome, String cpf, LocalDate dataNasc) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public Cliente() {
    
    }
         
    public static int calcIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears();
    }
    public static  boolean validarCPF(String cpf){
        ContaBanco conta = null;
        for (int i = 0; i <getListaContas().size(); i++) {
            if(getListaContas().get(i).getCpf().equals(cpf)){
                return true;
            }
        }
    return false;
    }    

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public LocalDate getDataNasc() {
        return this.dataNasc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    
}
  
