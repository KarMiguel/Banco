package Banco;

import entities.Cliente;
import entities.ContaBanco;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class BancoMain {

    public static void main(String[] args) throws ParseException {
        int op =0;
        Scanner entr = new Scanner(System.in);
        LocalDate  fmt1 = LocalDate.of(2003,10,06);

        ContaBanco conta = null;
        Cliente cliente = new Cliente();
        ContaBanco conta1 = new ContaBanco("Carlos","123", fmt1);
        conta1.addListaContas(conta1);

        do{
            System.out.println("\n------------------------------");
            System.out.println("-----BEM VIDO AO DarkBank-----");
            System.out.println("------------------------------");
            System.out.println("1 - Abrir conta\n2 - Extrato\n3 - Depositar");
            System.out.println("4 - Sacar\n5 - Contas Cadastradas \n6 - Tranferencia/PIX");
            System.out.println("7 - Buscar Dados da conta\n8 - Encerrar Conta\n9 - Sair");
            System.out.println("------------------------------");

            op = entr.nextInt();
           
            if(op == 1){
                System.out.println("INFORME SEUS DADOS:");
                System.out.print("Nome: ");
                entr.nextLine();
                String nome = entr.nextLine();
                System.out.print("CPF:");
                String cpf=entr.next(); 
                while (cliente.validarCPF(cpf)) {
                    System.out.println("CPF já cadastrado. Por favor, digite outro CPF:");
                    cpf =entr.next(); 
                }
                System.out.print("Data de nascimento(dd/MM/YYYY): ");
                String datNasc = entr.next();
                LocalDate dataNascimento = LocalDate.parse(datNasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if(cliente.calcIdade(dataNascimento)>=18){
                    conta = new ContaBanco(nome,cpf,dataNascimento);
                    conta.addListaContas(conta);
                    System.out.println("\nConta Aberta com Sucesso!");
                }else{
                    System.out.println("\nNão abrimos conta para menor de idade!");
                }
                
            }else if(op == 2){
                System.out.print("Informe o cpf da conta ha ver o extrato:");
                String cpf=entr.next();
                ContaBanco cc= ContaBanco.buscarConta(cpf);
                if(cc==null){
                    System.out.println("\nConta Inexistente!");
                }else{
                    System.out.println(cc.toString());
                }
            }else if(op == 3){
                System.out.print("Informe o cpf da conta que deseja depositar:");
                String cpf=entr.next();
                ContaBanco cc= ContaBanco.buscarConta(cpf);
                if(cc==null){
                    System.out.println("\nConta Inexistente!");
                }else{
                    System.out.print("Digite o valor a ser depositado:"); 
                    double valor=entr.nextDouble();
                    cc.depositar(valor);      
                }           
            }else if(op == 4){
                 System.out.print("Informe o cpf da conta que deseja sacar:");
                String cpf=entr.next();
                ContaBanco cc= ContaBanco.buscarConta(cpf);
                if(cc==null){
                    System.out.println("\nConta Inexistente!");
                }else{
                    System.out.print("Informe o valor que dejeja sacar:");
                    double saque=entr.nextDouble();
                    cc.sacar(saque);
                }           
            }else if(op == 5){
                ContaBanco.ContasCadastradas();
            }else if(op == 6){
                System.out.print("Informe o seu CPF :");
                String cpf1 = entr.next();
                ContaBanco cc1 = ContaBanco.buscarConta(cpf1);
                System.out.print("Informe o CPF da conta a transferir:");
                String cpf2 = entr.next();
                ContaBanco cc2 = ContaBanco.buscarConta(cpf2);
                System.out.print("Informe o numero da conta a transferir:");
                Integer numConta = entr.nextInt(); 
                System.out.print("Informe o valor há ser transferido:");
                double valor = entr.nextDouble();
                
                ContaBanco.transferir(cc1,cc2,cpf2,numConta,valor);
                
            }else if(op==7){
                System.out.print("Informe o CPF da conta ha ver os dados: ");
                String cpf = entr.next();
                ContaBanco cc = ContaBanco.buscarConta(cpf);
                    if(cc==null){
                        System.out.println("\nConta Inexistente!");
                    }else{
                        cc.mostrarDadosConta();
                    }
            }else if(op == 8){
            System.out.print("Para encerrar a conta informe seu CPF: ");
            String cpf = entr.next();
            ContaBanco cc = ContaBanco.buscarConta(cpf);
                if(cc==null){
                    System.out.println("\nConta Inexistente!");
                }else{
                    System.out.println("1 - confirma || outro cancela operação");
                    int num = entr.nextInt();
                   if(num==1){
                       cc.fecharConta(cc);
                   }else{
                       System.out.println("Operação Cancelada com sucesso!");
                   }
                }       
            }else{
                if (op==9) {
                    System.out.println("Obrigado pela Prefêrencia!");
                    break;
                }
                System.out.println("Opção Invalida!");
            }
        }while(op != 9);
    }
}
