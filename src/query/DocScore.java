package query;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe représentant un couple (docId,score).
 */
public class DocScore implements Comparable<DocScore>
{
	/**
	 * Crée un nouvel objet à partir
	 * des paramètres spécifiés.
	 * 
	 * @param type
	 * 		Type dont le token est une occurrence.
	 * @param docId
	 * 		Numéro du document concerné.
	 * @param position
	 * 		Numéro du token dans le document.
	 */
	public DocScore(int docId, float score)
	{	//TODO méthode à compléter (TP6-ex4)
	}
	
	////////////////////////////////////////////////////
	//	DOC ID
	////////////////////////////////////////////////////
	/** Numéro du document */
	//TODO champ à créer (TP6-ex4)
	
	////////////////////////////////////////////////////
	//	POSITION
	////////////////////////////////////////////////////
	/** Score du document */
	//TODO champ à créer (TP6-ex4)
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(DocScore docScore)
	{	int result = 0;
		//TODO méthode à compléter (TP6-ex4)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	/** Format utilisé lors de l'affichage du score, pour le limiter à 4 décimales */
	//TODO champ à créer (TP6-ex4)
	
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP6-ex4)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP6-ex4)
		return result;
	}
}
