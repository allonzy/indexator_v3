import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import query.AndQueryEngine;
import query.DocScore;
import indexation.Index;
import indexation.content.IndexEntry;
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
		// testIndexation();
		// test de getFileNames
		// testGetFilesName();
		// test de Index.read
		// testIndexRead();
		// test de testQuery
		testQuery();
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
	private static void testGetFilesName(){
		List<Posting> postings= new ArrayList<Posting>();
		postings.add(new Posting(1));
		postings.add(new Posting(2));
		postings.add(new Posting(3));
		System.out.println(getFileNamesFromPostings(postings));
	}
	private static void testIndexRead(){
		Index i;
		try {
			i = Index.read("data"+File.separator+"index.data");
			System.out.println("affichage de l'index");
			i.print();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	{	
		Index i = Index.read("data"+File.separator+"index.data");
		AndQueryEngine andQueryEngine = new AndQueryEngine(i);
		//TODO méthode à compléter (TP3-ex2)
		List<List<Posting>> result = new ArrayList<List<Posting>>();
		andQueryEngine.splitQuery("recherche INFORMATION Web", result);
		for (List<Posting> postings : result){
			System.out.println(postings.toString());
		}

		//(TP3-ex3)
		List<Posting> intersection1 = andQueryEngine.processConjunction(result.get(0),result.get(1));
		System.out.println("intersection entre recherche et INFORMATION" + intersection1);
		//(TP3-ex4)
		
		// (TP3-ex5)
		List<Posting> intersection2 = andQueryEngine.processConjunctions(result);
		System.out.println("intersection de la requète \"recherche INFORMATION Web\" " +intersection2);
		//(TP3-ex6)
		List<Posting> intersection3 = andQueryEngine.processQuery("recherche INFORMATION Web");
		System.out.println("intersection de la requète \"recherche INFORMATION Web\" "+intersection3);
		//TODO méthode à compléter (TP3-ex7)
		List<String> queries = new ArrayList<String>();
		queries.add("project");
		queries.add("project SOFTWARE");
		queries.add("project SOFTWARE Web");
		queries.add("recherche");
		queries.add("recherche INFORMATION");
		queries.add("recherche INFORMATION Web");
		for (String query : queries){
			List<Posting> intersection  = andQueryEngine.processQuery(query);
			System.out.println("Result: "+intersection.size()+" document(s)");
			System.out.println(intersection);
			System.out.println("Files:");
			System.out.println(getFileNamesFromPostings(intersection));
		}
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
