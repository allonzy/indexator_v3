package indexation.content;

import java.io.Serializable;
import java.util.List;

/**
 * Représente un posting, c'est à dire
 * ici : simplement le numéro du document
 * contenant un token donné.
 */
public class Posting implements Serializable, Comparable<Posting>
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construit un nouveau posting
	 * à partir du numéro de document
	 * pass� en paramètre.
	 * 
	 * @param docId
	 * 		Numéro du document concerné.
	 */
	public Posting(int docId)
	{	this.docId = docId;
		//TODO méthode à modifier  (TP5-ex1)
	}

	////////////////////////////////////////////////////
	//	DOC ID
	////////////////////////////////////////////////////
	/** Numéro du document représenté par ce posting */
	//TODO champ à définir (TP1-ex10)
	private int docId;
	////////////////////////////////////////////////////
	//	POSITIONS
	////////////////////////////////////////////////////
	/** Liste des positions occupées dans le document concerné par ce posting */
	public List<Integer> positions;
	
	////////////////////////////////////////////////////
	//	FRÉQUENCE
	////////////////////////////////////////////////////
	/** Fréquence du terme dans le document */
	//TODO champ à créer (TP5-ex1)
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(Posting posting)
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex10)
		return result;
	}
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex10)
		//TODO méthode à modifier  (TP5-ex1)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex10)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test du constructeur et de toString
		// TODO méthode à compléter (TP1-ex10)
		
		// test de equals
		// TODO méthode à compléter (TP1-ex10)
				
		// test de compareTo
		// TODO méthode à compléter (TP1-ex10)
	}
}
