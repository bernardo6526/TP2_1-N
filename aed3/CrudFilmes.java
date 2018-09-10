package aed3;

import java.util.Scanner;

public class CrudFilmes {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Filme> arqFilmes;
    private static CrudGeneros crudGen = new CrudGeneros();
    private static ArquivoIndexado<Genero> arqGeneros;

    /**
     * Método principal, cujo objetivo é criar uma interface para o usuário
     */
    public static void main(String[] args) {

        try {

            arqFilmes = new ArquivoIndexado<>(Filme.class.getConstructor(), "filme_dados.db", "filme_dados.idx");
            arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "genero_dados.db", "genero_dados.idx");

            // menu
           int opcao;
           do {
               System.out.println("\n\nGESTÃO DE FILMES");
               System.out.println("-----------------------------\n");
               System.out.println("1 - Listar");
               System.out.println("2 - Incluir");
               System.out.println("3 - Alterar");
               System.out.println("4 - Excluir");
               System.out.println("5 - Buscar");
               System.out.println("6 - Gestão de Gêneros");
               System.out.println("0 - Sair");
               System.out.print("\nOpcao: ");
               opcao = Integer.valueOf(console.nextLine());

               switch(opcao) {
                   case 1: listarFilmes(); break;
                   case 2: incluirFilme(); break;
                   case 3: alterarFilme(); break;
                   case 4: excluirFilme(); break;
                   case 5: buscarFilme(); break;
                   case 6: crudGen.mainmenu(arqGeneros); break;

                   case 0: break;
                   default: System.out.println("Opção inválida");
               }

           } while(opcao!=0);
       } catch(Exception e) {
           e.printStackTrace();
       }
    }


   public static void listarFilmes() throws Exception {

       Object[] obj = arqFilmes.listar();

       System.out.println("\nLISTA DE FILMES");
       for(int i=0; i<obj.length; i++) {
           System.out.println((Filme)obj[i]);
       }
       pausa();

   }

   public static void incluirFilme() throws Exception {

       System.out.print("Digite os dados do filme a ser inserido. \nTítulo: ");
       String titulo = console.nextLine();

       System.out.print(" \nTítulo_Original: ");
       String titulo_original = console.nextLine();

       System.out.print(" \nPaís: ");
       String pais = console.nextLine();

       System.out.print(" \nAno: ");
       Short ano = console.nextShort();

       System.out.print(" \nDuração: ");
       Short duracao = console.nextShort();

       console.nextLine();

       System.out.print(" \nDiretor: ");
       String diretor = console.nextLine();

       System.out.print(" \nSinopse: ");
       String sinopse = console.nextLine();

       boolean r = false;
       int id_gen = -1;
       while(r == false){
        System.out.print("\nEscolha o código do gênero.");
        id_gen = verificarId();
        r = crudGen.buscarGenero(arqGeneros, id_gen);
       }




       System.out.print("\nTitulo: " + titulo
       + "\nTitulo Original: " + titulo_original
       + "\nPais: " + pais
       + "\nAno: " + ano
       + "\nDuração: " + duracao
       + "\nDiretor: " + diretor
       + "\nSinopse: " + sinopse + "\nEsses dados estão corretos? [s/n]: ");
       char confirma = console.nextLine().charAt(0);
       if(confirma=='s' || confirma=='S') {
           Filme obj = new Filme(-1, titulo, titulo_original, pais, ano, duracao, diretor, sinopse, id_gen);
           int id = arqFilmes.incluir(obj);
           System.out.println("Filme incluído com ID: "+id);
       }

       pausa();
   }


   public static void alterarFilme() throws Exception {

       System.out.println("\nALTERAÇÃO DE FILME");

       int id;
       System.out.print("ID do filme: ");
       id = Integer.valueOf(console.nextLine());
       if(id <=0)
           return;

       Filme obj;
       if( (obj = (Filme)arqFilmes.buscar(id))!=null ) {
            System.out.println(obj);

            System.out.print("Digite os dados do filme a ser inserido. \nTítulo: ");
            String titulo = console.nextLine();

            System.out.print(" \nTítulo_Original: ");
            String titulo_original = console.nextLine();

            System.out.print(" \nPaís: ");
            String pais = console.nextLine();

            System.out.print(" \nAno: ");
            Short ano = console.nextShort();

            System.out.print(" \nDuração: ");
            Short duracao = console.nextShort();

            console.nextLine();

            System.out.print(" \nDiretor: ");
            String diretor = console.nextLine();

            System.out.print(" \nSinopse: ");
            String sinopse = console.nextLine();

            System.out.print("\nTitulo: " + titulo
            + "\nTitulo Original: " + titulo_original
            + "\nPais: " + pais
            + "\nAno: " + ano
            + "\nDuração: " + duracao
            + "\nDiretor: " + diretor
            + "\nSinopse: " + sinopse + "\nEsses dados estão corretos? [s/n]: ");

            if(titulo.length()>0 || duracao>=0 || ano >=0) {
                System.out.print("\nConfirma alteração? ");
                char confirma = console.nextLine().charAt(0);
                if(confirma=='s' || confirma=='S') {

                obj.titulo = (titulo.length()>0 ? titulo : obj.titulo);
                obj.titulo_original = (titulo_original.length()>0 ? titulo_original : obj.titulo_original);
                obj.pais = (pais.length()>0 ? pais : obj.pais);
                obj.diretor = (diretor.length()>0 ? diretor : obj.diretor);
                obj.sinopse = (sinopse.length()>0 ? sinopse : obj.sinopse);
                obj.duracao = (duracao>=0?duracao:obj.duracao);
                obj.ano = (ano>=0?ano:obj.ano);

                if( arqFilmes.alterar(obj) )
                        System.out.println("Filme alterado.");
                    else
                        System.out.println("Filme não pode ser alterado.");
                }
            }
       }
       else
           System.out.println("Filme não encontrado");
       pausa();

   }


   public static void excluirFilme() throws Exception {

       System.out.println("\nEXCLUSÃO DE FILME");

       int id;
       System.out.print("ID do filme: ");
       id = Integer.valueOf(console.nextLine());
       if(id <=0)
           return;

       Filme obj;
       if( (obj = (Filme)arqFilmes.buscar(id))!=null ) {
            System.out.println(obj);

            System.out.print("\nConfirma exclusão? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S') {
                if( arqFilmes.excluir(id) ) {
                    System.out.println("Filme excluído.");
                }
            }
       }
       else
           System.out.println("Filme não encontrado");
       pausa();

   }


   public static void buscarFilme() throws Exception {

       System.out.println("\nBUSCA DE FILME POR CÓDIGO");

       int codigo;
       System.out.print("Código: ");
       codigo = Integer.valueOf(console.nextLine());
       if(codigo <=0)
           return;

       Filme obj;
       if( (obj = (Filme)arqFilmes.buscar(codigo))!=null )
           System.out.println(obj);
       else
           System.out.println("Filme não encontrado");
       pausa();

   }

    public static void pausa() throws Exception {
        System.out.println("\nPressione ENTER para continuar ...");
        console.nextLine();
    }

    public static int verificarId() throws Exception{
        boolean resp;
        int id = 0;
        crudGen.listarGeneros(arqGeneros);
        System.out.println("Digite o código do genero: ");
        id = Integer.valueOf(console.nextLine());
        return id;
    }


}
