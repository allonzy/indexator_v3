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
	{	
		return this.docId - posting.docId;
	}
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	
		//TODO méthode à compléter (TP1-ex10)
		return Integer.toString(this.docId);
	}
	
	@Override
	public boolean equals(Object o)
	{	
		if (o instanceof Posting){
			return (this.compareTo((Posting) o ) == 0 );
		}else{
			return false;
		}
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test du constructeur et de toString
		// TODO méthode à compléter (TP1-ex10)
		Posting p1 = new Posting(1);
		System.out.println(p1);
		// test de equals
		Posting p1bis = new Posting(1);
		Posting p2 = new Posting(2);
		
		System.out.println(p1+"=="+p1bis+": "+p1.equals(p1bis));
		System.out.println(p1+"=="+p2+": "+p1.equals(p2));

		// test de compareTo

		System.out.println(p1+" cmpTo "+p1bis+": "+p1.compareTo(p1bis));
		System.out.println(p1+" cmpTo "+p2+": "+p1.compareTo(p2));
		System.out.println(p2+" cmpTo "+p1+": "+p2.compareTo(p1));x


	}
}
