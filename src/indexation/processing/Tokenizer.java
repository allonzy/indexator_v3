package indexation.processing;

import indexation.content.Token;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Objet segmentant des textes
 * en utilisant tous les caractères
 * non alphanumériques comme séparateurs.
 */
public class Tokenizer implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	/**
	 * Tokenize tout le corpus et renvoie les
	 * tokens obtenus via la liste passée en
	 * paramètre. La méthode renvoie aussi le
	 * nombre de documents traités dans la
	 * collection spécifiée par le paramètre 
	 * folder, correspondant au chemin de son 
	 * dossier.
	 * 
	 * @param folderPath
	 * 		Chemin du dossier contenant les fichiers texte à traiter.
	 * @param tokens
	 * 		Liste de tokens résultant du traitement.
	 * @return
	 * 		Nombre de documents traités.
	 * 
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public int tokenizeCorpus(String folderPath, List<Token> tokens) throws UnsupportedEncodingException,FileNotFoundException
	{	
		int docId = 0;
		File folder = new File(folderPath);
		for(File file : folder.listFiles()){
			this.tokenizeDocument(file, docId, tokens);
			docId ++;
		}
		return docId;
	}
	
	/**
	 * Méthode qui segmente le document
	 * spécifié, et renvoie le résultat
	 * en complétant la liste passée en 
	 * paramètre.
	 * 
	 * @param document
	 * 		Fichier contenant le document à traiter.
	 * @param docId
	 * 		Numéro du document à traiter.
	 * @param tokens
	 * 		La liste de tokens à compléter.
	 * 
	 * @throws UnsupportedEncodingException, FileNotFoundException
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public void tokenizeDocument(File document, int docId, List<Token> tokens) throws UnsupportedEncodingException,FileNotFoundException
	{	
			FileInputStream fis = new FileInputStream(document);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			Scanner scanner = new Scanner(isr);
			
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				List<String> stringTokenList = this.tokenizeString(line);
				for (String str: stringTokenList){
					if (str.equals("")){
						Token token = new Token(str,docId);
						tokens.add(token);
					}
				}
			}
			scanner.close();

		
		//TODO méthode à modifier  (TP5-ex3)
	}
	
	/**
	 * Renvoie la liste des tokens pour
	 * la chaîne de caractères spécifiée.
	 * 
	 * @param string
	 * 		Chaîne de caractères à traiter.
	 * @return
	 * 		La liste de types correspondant.
	 */
	public List<String> tokenizeString(String string)
	{	
		return new ArrayList<String>(Arrays.asList(string.split("[^\\pL\\pN]+")));
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	/**
	 * Test des méthodes de cette classe.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * 
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'accès aux textes.
	 */
	public static void main(String[] args) 
	{	Tokenizer tokenizer = new Tokenizer();
		// test de tokenizeString
		System.out.println(tokenizer.tokenizeString("lorem ipsum, sît dolôr àmet çonsectetûr"));
		System.out.println(tokenizer.tokenizeString(""));
		// test de tokenizeDocument
		List<Token> tokens =  new ArrayList<Token>();
		try {
			tokenizer.tokenizeDocument(new File(".."+File.separator+"Common"+File.separator+"corpus"+File.separator+"00ac1db5-77eb-481f-b7c4-595c75a06425.txt"), 0,tokens);
			System.out.println(tokens);
			List<Token> tokens2 =  new ArrayList<Token>();
			tokenizer.tokenizeCorpus(".."+File.separator+"Common"+File.separator+"test", tokens2);
			System.out.println((tokens2.size()));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// test de tokenizeCorpus
		// TODO méthode à compléter (TP1-ex7)
	}
}
