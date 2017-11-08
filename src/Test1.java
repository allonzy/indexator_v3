import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import query.DocScore;
import indexation.Index;
import indexation.content.Posting;
import indexation.processing.Tokenizer;

/**
 * Classe permettant de tester
 * notre indexation.
 */
public class Test1
{	/**
	 * Méthode principale.
	 * 
	 * @param args
	 * 		Paramètres ignorés.
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 * @throws ClassNotFoundException 
	 * 		Problème lors de la lecture de l'index
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{	
		System.out.println("Debut des tests");

		// test de testIndexation
		//TODO voir pourquoi c'est si long
		testIndexation();
		// test de getFileNames
		List<Posting> postings= new ArrayList();
		postings.add(new Posting(1));
		postings.add(new Posting(2));
		postings.add(new Posting(3));
		System.out.println(getFileNamesFromPostings(postings));
		// test de Index.read
		//TODO méthode à compléter (TP2-ex12)
		
		// test de testQuery
//		testQuery();
		System.out.println("Fin des tests");
	}

	////////////////////////////////////////////////////
	//	INDEXATION
	////////////////////////////////////////////////////
	/** Dossier contenant le corpus */
	public static final String CORPUS_FOLDER = ".."+File.separator+"Common"+File.separator+"corpus";

	/**
	 * Test des classes écrites lors des TP1 et TP2
	 * (i.e. fichier inverse).
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 */
	private static void testIndexation() throws IOException
	{
		Index i = Index.indexCorpus(CORPUS_FOLDER);
		i.print();
		
		//TODO méthode à compléter (TP2-ex11) vérifier parceque coder a la rache
		File file = new File("data"+File.separator+"index.data");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(i);
		oos.close();
		//TODO méthode à compléter (TP4-ex9)
		
		//TODO méthode à modifier  (TP5-ex6)
	}
	
	////////////////////////////////////////////////////
	//	REQUÊTES
	////////////////////////////////////////////////////
	/**
	 * Test des classes écrites lors du TP3
	 * et TP4 (requêtes).
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 * @throws ClassNotFoundException 
	 * 		Problème lors de la lecture de l'index
	 */
	private static void testQuery() throws IOException, ClassNotFoundException
	{	//TODO méthode à compléter (TP3-ex2)
		//TODO méthode à compléter (TP3-ex3)
		//TODO méthode à compléter (TP3-ex4)
		//TODO méthode à compléter (TP3-ex5)
		//TODO méthode à compléter (TP3-ex6)
		//TODO méthode à compléter (TP3-ex7)
		//TODO méthode à compléter (TP3-ex13)
		
		//TODO méthode à compléter (TP4-ex10)
		
		//TODO méthode à compléter (TP5-ex14)
		
		//TODO méthode à compléter (TP6-ex3)
		//TODO méthode à compléter (TP6-ex12)
	}
	
	////////////////////////////////////////////////////
	//	FILE NAMES
	////////////////////////////////////////////////////
	/**
	 * Renvoie la liste des noms de fichier correspondant
	 * aux postings passés en paramètres.
	 * 
	 * @param postings
	 * 		Liste de postings.
	 * @return
	 * 		Liste de noms de fichiers.
	 */
	private static List<String> getFileNamesFromPostings(List<Posting> postings)
	{	
		File corpusFolder = new File(CORPUS_FOLDER);
		File[] corpusFiles = corpusFolder.listFiles();
		List<String> result = new ArrayList();
		for (Posting posting : postings){
			try{
				File corpusFile = corpusFiles[posting.getDocId()];
				result.add(corpusFile.getName());
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Un problème avec votre liste de Posting est survenue, veuillez la regénerer");
				throw e;
			}
		}
		return result;
	}

	/**
	 * Renvoie la liste des noms de fichier correspondant
	 * aux scores passés en paramètres.
	 * 
	 * @param docScores
	 * 		Liste de couples (docId,scores).
	 * @return
	 * 		Liste de noms de fichiers.
	 */
	private static List<String> getFileNamesFromDocScores(List<DocScore> docScores)
	{	List<String> result = null;
		//TODO méthode à compléter (TP6-ex11)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	INDEX POSITIONNEL
	////////////////////////////////////////////////////
	/**
	 * Affiche des extraits du document passé en paramètre,
	 * en se concentrant sur les positions indiquées dans 
	 * le posting.
	 * 
	 * @param posting
	 * 		Posting indiquant les positions à traiter.
	 * @param document
	 * 		Fichier contenant le document.
	 * @param tokenizer
	 * 		Tokenizer utilisé lors de l'indexation.
	 * 
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	private static void printPosting(Posting posting, File document, Tokenizer tokenizer) throws UnsupportedEncodingException
	{	//TODO méthode à compléter (TP6-ex1)
	}
	
	/**
	 * Affiche des extraits du document passé en paramètre,
	 * en se concentrant sur les positions indiquées dans 
	 * chaque posting spécifié.
	 * 
	 * @param posting
	 * 		Liste de postings indiquant les positions à traiter.
	 * @param corpus
	 * 		Chemin du corpus concerné.
	 * @param index
	 * 		Index préalablement chargé.
	 * 
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	private static void printPostings(List<Posting> postings, String corpus, Index index) throws UnsupportedEncodingException
	{	//TODO méthode à compléter (TP6-ex2)
	}
}
