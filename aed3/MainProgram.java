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
	
	private static Scanner console;
	private static CrudFilmes crudF;
	private static CrudGeneros crudG;

   /**
    * Metodo Principal Main
    */
	public static void main(String[] args) {
		console = new Scanner(System.in);	
		int opcao = -1;

		RandomAccessFile arq;

		try{
			crudG = new CrudGeneros();
			crudF = new CrudFilmes(crudG);
		}catch(Exception e){e.printStackTrace();}

		System.out.println("Bem-vindo ao CRUD de filmes!");

			int id;
			ViewGenero viewGen = new ViewGenero();
			while(opcao != 0) {
				System.out.print("\n\n\n-----GESTOR DE FILMES-----\n"+
				"0 - Finalizar programa\n"+
				"1 - Gerenciar filmes\n"+
				"2 - Gerenciar Generos\n"+
				"Inserir : ");
				opcao = console.nextInt();
				if( opcao == 1 )
					MenuFilme();
				else if(opcao == 2){
					
					viewGen.menu();
				}//end else if
					
				//TRATAR EXCEÇÕES
			}//end while		
				
	}//end main()
  
   /**
    * Menu de Filme(com todas as opções do CRUD)
    */
	private static void MenuFilme(){
		int choice = -1;

		try{
			while(choice != 0 ){
				System.out.println("\nMenu:\n"+
				"0 - Sair;\n"+
				"1 - Lista Filmes\n"+
				"2 - Incluir filme\n"+
				"3 - Alterar filme;\n"+
				"4 - Excluir filme;\n"+
				"5 - Consultar filme;");
				
				choice = console.nextInt();
				System.out.println("choice : "+choice+"\n");
				int id;
	
				switch(choice) {
						case 0:
							System.out.println("Obrigado por utilizar o CRUD de filmes!");
							break;
						case 1:
							System.out.println("\t\t---Lista de filmes--\n");
							Filme[] listaF = crudF.listarFilmes();

							for(int i = 0; i < listaF.length;i++)
								System.out.println(listaF[i]+"\n\n");

							break;
						case 2:	
							Filme fil = criarObjetoFilme();
							if(fil!=null)
								crudF.incluirFilme(fil);
							break;
						case 3:
                     		crudF.alterarFilme();
							break;
						case 4:
							crudF.excluirFilme();
							break;
						case 5 :
							crudF.buscarFilme();
						break;
						default:
							System.out.println("Opção inválida!");
							break;
				}//end switch
			}//end while
		}catch(Exception e){
			e.printStackTrace();
		}//end catch
	}//end MenuFilme

  /**
   * Cria um novo objeto de filme
   * @return Filme objeto a ser inserido no arquivo(new Filme) 
   */
	public static Filme criarObjetoFilme(){

		Scanner console = new Scanner(System.in);
        String titulo,tituloOriginal,pais,diretor,sinopse;
        short ano;
        short min;
        int idGenero;
        Filme filme = null;

        System.out.print("Titulo: ");
        titulo = console.nextLine();

        System.out.print("Titulo Original: ");
        tituloOriginal = console.nextLine();

        System.out.print("Pais de origem: ");
        pais = console.nextLine();

        System.out.print("Diretor: ");
        diretor = console.nextLine();

        System.out.print("Sinopse: ");
        sinopse = console.nextLine();

        System.out.print("Ano: ");
        ano = console.nextShort();

        System.out.print("Minutos filme: ");
        min = console.nextShort();

		boolean isGenero = false;

        Genero obj =null;

        try{
            crudG.listarGeneros();
            do{
                System.out.print("Id do Gênero do filme: ");
            
                idGenero = console.nextInt();

                obj = crudG.buscarGenero(idGenero);

                if(obj == null)
                    System.out.println("Genero inválido!");
                else{
                    System.out.println("Genero : " + obj.getNome());
                    isGenero = true;
                }//end else
                    

            }while(!isGenero);
        }catch(Exception e ){e.printStackTrace();}
		
		System.out.print("Insira 1 para confirma inclusão ou qualquer coisa para cancelar: ");
        byte op=console.nextByte();
        if(op == 1){
			filme = new Filme(titulo,tituloOriginal,pais,ano,min,diretor,sinopse,obj.getId());
        }//end if
		return filme;
	}//end criarObjetoFilme
  


}//end MainProgram