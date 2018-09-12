

import java.util.Scanner;

//import com.sun.tools.internal.xjc.reader.gbind.OneOrMore;

public class CrudGeneros {

    private static Scanner console = new Scanner(System.in);

    private ArquivoIndexado<Genero> arqGeneros;

    public CrudGeneros() throws Exception{
        this.arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "genero_dados.db", "genero_dados.idx");
    }

    /**
     * Método principal, cujo objetivo é criar uma interface para o usuário
     */
    /*
        public  void mainmenu(ArquivoIndexado<Genero> arqGeneros) {

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
               opcao = Integer.valueOf(console.nextLine());

               switch(opcao) {
                   case 1: listarGeneros(arqGeneros); break;
                   case 2: incluirGenero(arqGeneros); break;
                   case 3: alterarGenero(arqGeneros); break;
                   case 4: excluirGenero(arqGeneros); break;
                   case 5: buscarGenero(arqGeneros); break;

                   case 0: break;
                   default: System.out.println("Opção inválida");
               }

           } while(opcao!=0);
       } catch(Exception e) {
           e.printStackTrace();
       }
    }*/

   
   public  void listarGeneros() throws Exception {

       Object[] obj = arqGeneros.listar();

       System.out.println("\nLISTA DE GENEROS");
       for(int i=0; i<obj.length; i++) {
           System.out.println((Genero)obj[i]);
       }
       pausa();

   }
   

   public  void incluirGenero() throws Exception {
       System.out.print("Digite o dado do Genero a ser inserido. \nNome: ");
       String nome = console.nextLine();

       System.out.print("\nNome: " + nome
       + "\nEsse dado está correto? [s/n]: ");
       char confirma = console.nextLine().charAt(0);
       if(confirma=='s' || confirma=='S') {
           Genero obj = new Genero(-1, nome);
           int id = arqGeneros.incluir(obj);
           System.out.println("Genero incluído com ID: "+id);
       }

       pausa();
   }

   public  void alterarGenero() throws Exception {

       System.out.println("\nALTERAÇÃO DE GENERO");

       int id;
       System.out.print("ID do Genero: ");
       id = Integer.valueOf(console.nextLine());
       if(id <=0)
           return;

       Genero obj;
       if( (obj = (Genero)arqGeneros.buscar(id))!=null ) {
            System.out.println(obj);

            System.out.print("Digite o dado do Genero a ser inserido. \nNome: ");
            String nome = console.nextLine();

            System.out.print("\nNome: " + nome
            +  "\nEsse dado está correto? [s/n]: ");

            if(nome.length()>0) {
                System.out.print("\nConfirma alteração? ");
                char confirma = console.nextLine().charAt(0);
                if(confirma=='s' || confirma=='S') {

                obj.nome = (nome.length()>0 ? nome : obj.nome);

                if( arqGeneros.alterar(obj) )
                        System.out.println("Genero alterado.");
                    else
                        System.out.println("Genero não pode ser alterado.");
                }
            }
       }
       else
           System.out.println("Genero não encontrado");
       pausa();

   }
   
   
   public void excluirGenero(CrudFilmes crudF) throws Exception {

       System.out.println("\nEXCLUSÃO DE GENERO");

       int id;
       System.out.print("ID do Genero: ");
       id = Integer.valueOf(console.nextLine());
       if(id <=0)
           return;

       Genero obj;
       if( (obj = (Genero)arqGeneros.buscar(id))!=null ) {
            System.out.println(obj);
            //Verifica quantos filmes tem o genero pesquisado
            int nFilmes =  crudF.listarGenero(id);
            //Caso tenha 0 filmes, continua o processo de exclusão
            if( nFilmes == 0 ){
              System.out.print("\nConfirma exclusão? ");
              char confirma = console.nextLine().charAt(0);
              if(confirma=='s' || confirma=='S'){
                  //Tenta excluir arquivo, caso consiga exibe mensagem de sucesso
                  if( arqGeneros.excluir(id) ) 
                      System.out.println("Genero excluído.");
              }
            }//If de exclusao
            else
              System.out.println("Há " + nFilmes + " filme com esse genero, primeiro exclua os filmes antes de tentar excluir o genero");
       }
       else
           System.out.println("Genero não encontrado");

       pausa();
   }

   
   public  Genero buscarGenero(int codigo) throws Exception {

        Genero obj = null;
        if(codigo > 0)
            obj = (Genero)arqGeneros.buscar(codigo);
        return obj;


   }
   

  /* public static boolean buscarGenero(ArquivoIndexado<Genero> arqGeneros, int n) throws Exception {
       int codigo = Integer.valueOf(n);
       if(codigo <=0)
           return false;

       Genero obj;
       if( (obj = (Genero)arqGeneros.buscar(codigo))!=null ){
           return true;
       }else{
           System.out.println("Codigo nao encontrado. Tentar novamente.");
           return false;
        }

   }*/



    public static void pausa() throws Exception {
        System.out.println("\nPressione ENTER para continuar ...");
        console.nextLine();
    }
    


}
