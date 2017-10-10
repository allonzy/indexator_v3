package indexation.content;

import java.io.Serializable;

/**
 * Représente une entrée de l'index,
 * comprenant un terme, une liste
 * de postings et la fréquence du terme
 * exprimée en documents.
 */
public class IndexEntry implements Serializable, Comparable<IndexEntry>
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crée une nouvelle entrée d'index,
	 * à partir du terme passé en paramètre.
	 * 
	 * @param term
	 * 		Terme inséré dans l'index.
	 */
	public IndexEntry(String term)
	{	//TODO méthode à compléter (TP1-ex11)
		//TODO méthode à modifier  (TP2-ex4)
	}
	
	////////////////////////////////////////////////////
	//	TERME
	////////////////////////////////////////////////////
	/** Terme concerné par ce posting */
	//TODO champ à créer (TP1-ex11)
	
	////////////////////////////////////////////////////
	//	POSTINGS
	////////////////////////////////////////////////////
	/** Liste des postings contenant le terme */
	//TODO champ à créer (TP1-ex11)
	
	////////////////////////////////////////////////////
	//	FREQUENCE
	////////////////////////////////////////////////////
	/** Fréquence du terme exprimée en nombre de documents */
	//TODO champ à créer (TP2-ex4)

	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(IndexEntry entry)
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex11)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex11)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex11)
		//TODO méthode à modifier  (TP2-ex4)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test du constructeur et de toString
		// TODO méthode à compléter (TP1-ex11)
		
		// test de equals
		// TODO méthode à compléter (TP1-ex11)
				
		// test de compareTo
		// TODO méthode à compléter (TP1-ex11)
	}
}
