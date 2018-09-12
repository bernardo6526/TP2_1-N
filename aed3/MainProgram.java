/*
 * Autores: Tulio N. Polido Lopes, Joao Victor da Silva, Gustavo Lescowicz Kotarsky, Temistocles Altivo Schwartz
 * Data: 21/08/2018
 * */


import java.io.*;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

//import aed3.ArquivoIndexado;
//import aed3.CrudGeneros;

public class MainProgram {
	
	private static Scanner input;
	private static CrudFilmes crudF;
	private static CrudGeneros crudG;
	private static ArquivoIndexado<Genero> arqGeneros;
	private static ArquivoIndexado<Filme> arqFilmes;

	public static void main(String[] args) {
		input = new Scanner(System.in);	
		byte choice = -1;

		RandomAccessFile arq;

		try{
			crudG = new CrudGeneros();
			crudF = new CrudFilmes(crudG);
		}catch(Exception e){e.printStackTrace();}

		System.out.println("Bem-vindo ao CRUD de filmes!");

			int id;

			while(choice != 0) {
				System.out.print("\n\n\n-----GESTOR DE FILMES-----\n"+
				"0 - Finalizar programa\n"+
				"1 - Gerenciar filmes\n"+
				"2 - Gerenciar Generos\n"+
				"Inserir : ");
				choice = input.nextByte();
				if( choice == 1 )
					MenuFilme();
				else if(choice == 2)
					MenuGenero();
				//TRATAR EXCEÇÕES
			}		
				
	}//end main()

	private static void MenuFilme(){
		byte choice = -1;

		try{
			while(choice != 0 ){
				System.out.println("Menu:\n"+
				"0 - Sair;\n"+
				"1 - Lista Filmes\n"+
				"2 - Incluir filme\n"+
				"3 - Alterar filme;\n"+
				"4 - Excluir filme;\n"+
				"5 - Consultar filme;");
				
				choice = input.nextByte();
				int id;
	
				switch(choice) {
						case 0:
							System.out.println("Obrigado por utilizar o CRUD de filmes!");
							break;
						case 1:
							crudF.listarFilmes();
							break;
						case 2:	
							crudF.incluirFilme();
							break;
						case 3:
							System.out.println("Insira o ID do filme a ser alterado: ");
							break;
						case 4:
							System.out.print("Insira o ID do filme a ser excluído :");
							id = input.nextInt();
							System.out.print("Deseja confirma a exclusão? Insira (1):");
							if(input.nextByte() == 1)
								//crud.delete(id);

							break;
						case 5 :
							System.out.print("Insira o ID do filme a ser pesquisado :");
							id = input.nextInt();
						break;
						default:
							System.out.println("Opção inválida!");
							break;
						}
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void MenuGenero(){
		input = new Scanner(System.in);
		try {
            // menu
           int opcao;
           do {
               System.out.println("\n\nGESTÃO DE GENEROS");
               System.out.println("-----------------------------\n");
               System.out.println("1 - Listar");
               System.out.println("2 - Incluir");
               System.out.println("3 - Alterar");
               System.out.println("4 - Excluir");
               System.out.println("5 - Buscar");
               System.out.println("0 - Sair");
               System.out.print("\nOpcao: ");
               opcao = input.nextInt();

               switch(opcao) {
                   case 1: crudG.listarGeneros(); break;
                   case 2: crudG.incluirGenero(); break;
                   case 3:crudG.alterarGenero() ; break;
                   case 4: crudG.excluirGenero( crudF ); break;
                   case 5: ; break;

                   case 0: break;
                   default: System.out.println("Opção inválida");
               }

           } while(opcao!=0);
       } catch(Exception e) {
           e.printStackTrace();
       }
	}

}
