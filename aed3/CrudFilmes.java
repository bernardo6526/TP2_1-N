

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class CrudFilmes {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Filme> arqFilmes;
    private static CrudGeneros crudGen;


    public CrudFilmes(CrudGeneros crudGen) throws Exception{
        this.arqFilmes = new ArquivoIndexado<>(Filme.class.getConstructor(), "filme_dados.db", "filme_dados.idx");
        this.crudGen = crudGen;
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

        pausa();
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

   }*/

   
   public  void excluirFilme() throws Exception {

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

   public  void buscarFilme() throws Exception {

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
   
    public  void pausa() throws Exception {
        System.out.println("\nPressione ENTER para continuar ...");
        console.nextLine();
    }
}
