

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class CrudFilmes {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Filme> arqFilmes;


    public CrudFilmes() throws Exception{
        this.arqFilmes = new ArquivoIndexado<>(Filme.class.getConstructor(), "filme_dados.db", "filme_dados.idx");
    }


    public Filme[] listarFilmes() throws Exception {

        Object[] objs = arqFilmes.listar();

        Filme[] listF = new Filme[objs.length];

        for(int i=0; i<objs.length; i++) {
            listF[i] = (Filme)objs[i];
        }

        return listF;
    }

    public void  incluirFilme(Filme filme ) throws Exception {
        int id = arqFilmes.incluir(filme);
        System.out.println("Filme incluído com ID: "+id);

    }

   /*
   public  void alterarFilme() throws Exception {

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
                obj.tituloOriginal = (titulo_original.length()>0 ? titulo_original : obj.tituloOriginal);
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
   */

   
   public  boolean excluirFilme(int id) throws Exception {

   return arqFilmes.excluir(id);

   }

   public  Filme buscarFilme(int codigo) throws Exception {    

       Filme obj =null;
       if(codigo > 0)
           obj = (Filme)arqFilmes.buscar(codigo);

       return obj;

   }
   
    public  void pausa() throws Exception {
        System.out.println("\nPressione ENTER para continuar ...");
        console.nextLine();
    }

    /*
    public int  verificarId() throws Exception{
        boolean resp = false;
        int id = 0;
        crudGen.listarGeneros();


        System.out.println("Digite o código do genero: ");
        id = Integer.valueOf(console.nextLine());

        Genero obj;

        if((obj = crudGen.buscarGenero(id) ) != null)
            System.out.println(b);
        else
            id = -1;

        return id;

    }*/
    
     /**
    *   @param int idGenero id do genero a ser pesquisado
    *   Busca o numero de vezes em que o genero pesquisado aparece na lista de filmes
    *   @return resp numero de vezes em que o genero foi encontrado
    */
    public static int listarGenero(int idGenero) throws Exception {
        int resp = 0;

        Object[] obj = arqFilmes.listar();

        //Realiza a pesquisa enquanto houverem filmes
        //Adiciona ao somador cada iteração correspondente ao genero pesquisado
        for(int i=0; i<obj.length; i++) {
          if( (int)((Filme)obj[i]).getIdGenero() == idGenero )
            resp++;
        }

        return resp;
    }


}
