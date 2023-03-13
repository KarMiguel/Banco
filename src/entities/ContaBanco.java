package entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ContaBanco extends Cliente {
    
   
    private  int numConta;
    private double saldo;
    private double limite;
    private static ArrayList<ContaBanco> listaContas = new ArrayList<>();
    private static final Scanner entr = new Scanner(System.in);

    public ContaBanco( String nome, String cpf, LocalDate dataNasc) {
        super(nome, cpf, dataNasc);
        Random aleatorio = new Random();
        setNumConta(aleatorio.nextInt(1000)%2000);
        setLimite(200);
    
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

    public void sacar(double saque){
        if(saque<=getSaldo() && saque<=1000){
            setSaldo(getSaldo()- saque); 
            System.out.println("Saque realizado com sucesso!\n");
        }else if(saque>getSaldo() && saque <= 1000){
            if(saque <= (getSaldo()+getLimite())){
                setSaldo((getSaldo() + getLimite()) - saque);
                setLimite(getSaldo());
                setSaldo(0);
                System.out.println("Saque realizado com sucesso!!\n");
            }else if(saque > (getSaldo()+getLimite())){
                System.out.println("Prezado "+getNome() +",saldo Insuficiente!!!");
                System.out.println("Saldo :"+getSaldo());
                System.out.println("Limite :"+ getLimite());
            }
        }else if(saque <= (getSaldo() +getLimite()) && saque >1000){
            do{   
                System.out.println("Limite de saque execedido !");
                System.out.println("Digite um valor menor que R$1000,00:");
                saque = entr.nextDouble();
                    if(saque<getSaldo() && saque <=1000){
                        setSaldo(getSaldo()- saque); 
                    }else if(saque <= (getSaldo()+getLimite()) && saque<=1000){
                        setSaldo((getSaldo() + getLimite()) - saque);
                        setLimite(getSaldo());
                            if(getSaldo()!=0){
                                setSaldo(0);
                            }
                    }if(saque <=1000){
                        System.out.println("Saque realizado com sucesso!!\n");
                    }
            }while(saque>1000);
        }else if(saque >1000 && saque>(getSaldo()+getLimite())){
            System.out.println("Prezado "+getNome() +",saldo Insuficiente!!!");
            System.out.println("Saldo :"+getSaldo());
            System.out.println("Limite :"+ getLimite());
        }
        
    }
    public static ContaBanco buscarConta(String cpf){
        ContaBanco conta = null;
        for (int i = 0; i <getListaContas().size(); i++) {
            if(getListaContas().get(i).getCpf().equals(cpf)){
                conta = ContaBanco.getListaContas().get(i);
            }
        }
        return conta;
    }    
    public static void ContasCadastradas() {
        System.out.println("Contas Cadastradas:\n");
        for (ContaBanco cc : getListaContas()) {
            System.out.println(cc);
        }
        System.out.println("");
    }
    public void fecharConta(ContaBanco cc){
        if(getSaldo() > 0){
            System.out.println("Conta não pode ser fechada pois tem saldo");
        }else if(getSaldo() < 0){
            System.out.println("Conta não pode ser fechada pois tem débito");
        }else{
            removerListaContas(cc);
            System.out.println("Conta fechada com Sucesso!");
        }
    }
    public void  depositar(double valor){     
        if(valor>0){
            this.setSaldo(getSaldo()+ valor);
            System.out.println("Deposito Realizado na conta "+getNome());
        }else{
            System.out.println("Impossivel depositar!");
        }
    }
    public static void transferir(ContaBanco c1,ContaBanco c2,String cpf,Integer numConta,Double valor){
        int validar = 0;
        for (int i = 0; i <getListaContas().size(); i++) {
            if(numConta == ContaBanco.getListaContas().get(i).getNumConta()){
                if(cpf.equals(ContaBanco.getListaContas().get(i).getCpf())){      
                    if(valor<c1.getSaldo() && valor <=5000){
                        c1.setSaldo(c1.getSaldo() - valor);
                        c2.setSaldo(c2.getSaldo() + valor);

                        System.out.println("\n===========COMPROVANTE===============");
                        System.out.println("Nome de destinario : "+c2.getNome());
                        System.out.println("CPF do destinatario:"+ c2.getCpf());
                        System.out.printf("Valor da trasnferencia: %.2f\n",valor);
                        System.out.println("Transferencia realizada com sucesso!!\n");
                        System.out.println("\n=========================================");
                    }else if(valor <= (c1.getSaldo()+c1.getLimite()) && valor<=5000){
                        c1.setSaldo((c1.getSaldo() + c1.getLimite()) - valor);
                        c2.setSaldo(c2.getSaldo() + valor);
                        c1.setLimite(c1.getSaldo());
                            if(c1.getSaldo()!=0){
                            c1.setSaldo(0);

                            }
                        System.out.println("\n===========COMPROVANTE===============");
                        System.out.println("Nome de destinario : "+c2.getNome());
                        System.out.println("CPF do destinatario:"+ c2.getCpf());
                        System.out.printf("Valor da trasnferencia: %.2f\n",valor);
                        System.out.println("Transsferencia realizada com sucesso!!");
                        System.out.println("=========================================");

                    }else if(valor>(c1.getSaldo()+c1.getLimite())){ 
                        System.out.println("\nPrezado "+c1.getNome()+"!! saldo Insuficiente!!!");
                        System.out.printf("Saldo :%.2f",c1.getSaldo());
                        System.out.printf("\nLimite :%.2f",c1.getLimite());
                    }else if(valor < (c1.getSaldo()+c1.getLimite())&& valor > 5000){
                        System.out.println("Limite de transfêrencia excedido!");
                    }
                }validar=1;
            }
        }if(validar !=1){
            System.out.println("Dados Inconsistentes!!");
        }        
    }
    public void mostrarDadosConta(){
        System.out.println("Conta: "+getNumConta());
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade:"+calcIdade(getDataNasc())+" anos");
        System.out.println("Saldo: "+getSaldo());
        System.out.println("Limite: "+getLimite());
    }
    @Override
    public String toString() {
        return "--------------EXTRATO----------------"
            +"\nConta: "+getNumConta()
            +"\nNome: "+ getNome()
            +"\nSaldo: "+getSaldo()
            +"\nLimite: "+getLimite();
    }
    
    public void addListaContas(ContaBanco c){
        getListaContas().add(c);
    } 
    public void removerListaContas(ContaBanco c){
        getListaContas().remove(c);
    }
    @Override
    public String getNome() {
        return this.nome;
    }
    @Override
    public LocalDate getDataNasc() {
        return this.dataNasc;
    }
    @Override
    public String getCpf() {
        return this.cpf;
    }
    public int getNumConta() {
        return numConta;
    }
    public static ArrayList<ContaBanco> getListaContas() {
        return listaContas;
    }
    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }
   
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

 }
