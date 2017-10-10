package tools;

import indexation.content.Token;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Objet comptant les occurrences de termes
 * dans un corpus et exportant le résultat
 * sous forme de fichier texte.
 */
public class TermCounter
{	/**
	 * Méthode principale.
	 * 
	 * @param args
	 * 		Ignorés.
	 * 
	 * @throws ClassNotFoundException 
	 * 		Problème lors de la création du fichier.
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	//TODO méthode à compléter (TP4-ex4)
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
	 */
	public static void processCorpus(String folder, String outFile) throws FileNotFoundException
	{	//TODO méthode à compléter (TP4-ex3)
	}
	
	/**
	 * Compte le nombre d'occurrences de chaque
	 * terme dans la liste pass�e en paramètre.
	 * 
	 * @param tokens
	 * 		Liste de tokens normalisés à traiter.
	 * @return
	 * 		Map associant son nombre d'occurrences à chaque terme.
	 */
	private static Map<String,Integer> countTerms(List<Token> tokens)
	{	Map<String,Integer> result = null;
		//TODO méthode à compléter (TP4-ex1)
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
	{	//TODO méthode à compléter (TP4-ex2)
	}
}
