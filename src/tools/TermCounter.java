package tools;

import indexation.content.Token;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Objet comptant les occurrences de termes
 * dans un corpus et exportant le résultat
 * sous forme de fichier texte.
 */
public class TermCounter
{	
	/** Dossier contenant le corpus */
	public static final String CORPUS_FOLDER = ".."+File.separator+"Common"+File.separator+"corpus";

	/**
	 * Méthode principale.
	 * 
	 * @param args
	 * 		Ignorés.
	 * @throws UnsupportedEncodingException 
	 * 
	 * @throws ClassNotFoundException 
	 * 		Problème lors de la création du fichier.
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{	//TODO méthode à compléter (TP4-ex4)
		processCorpus(CORPUS_FOLDER, "data"+File.separator+"term-count.txt");
	}

	/**
	 * Compte le nombre d'occurrences de chaque
	 * terme présent dans le corpus dont le dossier
	 * est spécifié, puis enregistre ces décomptes
	 * dans un fichier texte.
	 * 
	 * @param folder
	 * 		Dossier contenant le corpus.
	 * @param outFile
	 * 		Chemin du fichier texte à créer.
	 * 
	 * @throws FileNotFoundException 
	 * 		Problème lors de la création du fichier.
	 * @throws UnsupportedEncodingException 
	 */
	public static void processCorpus(String folder, String outFile) throws FileNotFoundException, UnsupportedEncodingException
	{	
		//(TP4-ex3)
		Tokenizer tokenizer = new Tokenizer();
		Normalizer normalizer = new Normalizer();
		List<Token> tokens = new LinkedList<Token>();
		tokenizer.tokenizeCorpus(folder, tokens);
		normalizer.normalizeTokens(tokens);
		Map<String,Integer> countTerm = countTerms(tokens);
		writeCounts(countTerm, outFile);
	}
	
	/**
	 * Compte le nombre d'occurrences de chaque
	 * terme dans la liste passée en paramètre.
	 * 
	 * @param tokens
	 * 		Liste de tokens normalisés à traiter.
	 * @return
	 * 		Map associant son nombre d'occurrences à chaque terme.
	 */
	private static Map<String,Integer> countTerms(List<Token> tokens)
	{	
		System.out.println("Counting terms ...");
		long start = System.currentTimeMillis();
		Map<String,Integer> result = new HashMap<String,Integer>();
		//TODO méthode à compléter (TP4-ex1)
		for (Token token : tokens){
			String key = token.getType();
			Integer value = result.get(key);
			result.put(key, (value != null)? value + 1: 1);
		}
		long end = System.currentTimeMillis();
		System.out.println("countTerms(): "+(end-start)+" ms");
		return result;
	}
	
	/**
	 * Enregistre les décomptes des termes.
	 * 
	 * @param counts
	 * 		Map contenant les décomptes des termes.
	 * @param fileName
	 * 		Nom du fichier texte à créer.
	 * 
	 * @throws FileNotFoundException 
	 * 		Problème lors de la création du fichier.
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'écriture des résultats.
	 */
	private static void writeCounts(Map<String,Integer> counts, String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{	System.out.println("Write counts... ");
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		PrintWriter writer = new PrintWriter(osw);
		for (Entry<String,Integer> entry : counts.entrySet()){
			String key = entry.getKey();
			Integer value = entry.getValue();
			writer.println(key+"\t"+value);
		}
		writer.close();
		System.out.println("Counts has succefully writed to "+fileName);
	}
}