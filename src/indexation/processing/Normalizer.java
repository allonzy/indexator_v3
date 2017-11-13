package indexation.processing;

import indexation.content.Token;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Objet normalisant des tokens
 * en supprimant les signes diacritiques
 * et en les passant en minuscules.
 */
public class Normalizer implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construit un nouveau normalizer ne traitant
	 * pas les mots-vides.
	 */
	public Normalizer()
	{	// rien à faire
	}
	
	/**
	 * Construit un nouveau normalizer capable
	 * de traiter les mots-vides. Le fichier passé
	 * en paramètre contient la liste de mots vides.
	 * 
	 * @param fileName
	 * 		Nom du fichier contenant les mots vides.
	 * 
	 * @throws FileNotFoundException
	 * 		Problème lors de l'accès au fichier contenant les mots vides.
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public Normalizer(String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{	
		
		this.stopWords = this.loadStopWords(fileName);
		System.out.println();
	}
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	/**
	 * Nettoie les tokens reçus en paramètres.
	 * 
	 * @param tokens
	 * 		Liste de tokens à traiter.
	 */
	public void normalizeTokens(List<Token> tokens)
	{	
		ListIterator<Token> lite = tokens.listIterator();
		while(lite.hasNext()){
			Token token = lite.next();
			String normalizedType = this.normalizeType(token.getType());
			if(normalizedType != null){
				token.setType(normalizedType);
			}else{
				lite.remove();
			}
		}
	}
	
	/**
	 * Nettoie le type de token reçu en paramètre.
	 * S'il ne correspond pas à un terme, c'est
	 * la valeur {@code null} qui est renvoée.
	 * 
	 * @param string
	 * 		La chaîne à traiter : un type associé à un token.
	 * @return
	 * 		La chaîne après traitement : un terme (ou {@code null}).
	 */
	public String normalizeType(String string)
	{
		string =  java.text.Normalizer.normalize(string, Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase();
		if (this.stopWords != null && this.stopWords.contains(string)){
			return null;
		}else{
			return string;
		}

	}

	////////////////////////////////////////////////////
	//	MOTS VIDES
	////////////////////////////////////////!////////////
	/** Liste des mots vides */
	TreeSet<String> stopWords;
	/**
	 * Charge la liste de mots vides contenue
	 * dans le fichier dont le nom est passé en paramètre.
	 * 
	 * @param fileName
	 * 		Fichier à traiter.
	 * @return
	 * 		Ensemble trié de mots vides.
	 * 
	 * @throws FileNotFoundException
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	private TreeSet<String> loadStopWords(String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{	
		TreeSet<String> result = new TreeSet<String>();
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		Scanner scanner = new Scanner(isr);
		while(scanner.hasNext()){
			String word = scanner.next();
			result.add(word);
		}
		scanner.close();
		return result;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	/**
	 * Test des méthodes de cette classe.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * @throws FileNotFoundException 
	 * 
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'accès aux textes.
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
	{	// test de normalizeType
		Normalizer normalizer = new Normalizer();
		System.out.println(normalizer.normalizeType("lOrEmr"));
		
		// test de loadStopWords
		TreeSet<String> stopWords =  normalizer.loadStopWords("data"+File.separator+"stop-words.txt");
		System.out.println(stopWords);
		// test de normalizeTokens
		//TODO méthode à compléter (TP1-ex9)
	}
}
