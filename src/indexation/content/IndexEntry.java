package indexation.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	{	
		this.term = term;
		this.postings = null;
		this.frequency = 0;
		//TODO méthode à modifier  (TP2-ex4)
	}
	
	////////////////////////////////////////////////////
	//	TERME
	////////////////////////////////////////////////////
	/** Terme concerné par ce posting */
	private String term;
	////////////////////////////////////////////////////
	//	POSTINGS
	////////////////////////////////////////////////////
	/** Liste des postings contenant le terme */
	//TODO champ à créer (TP1-ex11)
	private List<Posting> postings;
	////////////////////////////////////////////////////
	//	FREQUENCE
	////////////////////////////////////////////////////
	/** Fréquence du terme exprimée en nombre de documents */
	private int frequency;

	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(IndexEntry entry)
	{	
		return this.term.compareTo(entry.getTerm());
	}
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public final List<Posting> getPostings() {
		return postings;
	}
	public void setPostings(List<Posting> postings){
		this.postings = postings;
	}

	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	
		String postingsString = (this.postings ==null)?"()":this.postings.toString().replace("[", "(").replace("]", ")").replace(","," ");
		return "<"+this.term+"("+this.frequency+" occurences) "+postingsString+">";
	}
	
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public boolean equals(Object o)
	{	
		if(o instanceof IndexEntry){
			return this.compareTo((IndexEntry)o) == 0;
		}else{
			return false;
		}
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test du constructeur et de toString
		IndexEntry indexEntry1 = new IndexEntry("lorem");
		System.out.println(indexEntry1);
		// test de equals
		String nonPosting = "lorem";
		IndexEntry indexEntry2 = new IndexEntry("lorem");
		IndexEntry indexEntry3 = new IndexEntry("ipsum");
		System.out.println(indexEntry1+" == "+indexEntry2+" : "+indexEntry1.equals(indexEntry2));
		System.out.println(indexEntry1+" == "+indexEntry3+" : "+indexEntry1.equals(indexEntry3));
		System.out.println(indexEntry1+" == (String)"+nonPosting+" : "+indexEntry1.equals(nonPosting));
		// test de compareTo

		System.out.println(indexEntry1+" cmpTo "+indexEntry2+" : "+indexEntry1.compareTo(indexEntry2));
		System.out.println(indexEntry1+" cmpTo "+indexEntry3+" : "+indexEntry1.compareTo(indexEntry3));
		System.out.println(indexEntry3+" cmpTo "+indexEntry1+" : "+indexEntry3.compareTo(indexEntry1));

	}
}
