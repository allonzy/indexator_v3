package indexation;

import java.io.IOException;
import java.io.Serializable;

import indexation.content.IndexEntry;

/**
 * Objet représentant un index sous
 * la forme d'un fichier inverse simple.
 */
public class Index implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construit un nouvel index vide,
	 * de la taille indiquée en paramètre.
	 * 
	 * @param size
	 * 		Taille de l'index (exprimée en nombre de termes).
	 */
	public Index(int size)
	{	//TODO méthode à compléter (TP1-ex12)
	}
	
	/**
	 * Méthode de classe permettant la création
	 * d'un index prenant la forme d'un fichier inverse.
	 * Le dossier passe en paramètre est supposé
	 * contenir le corpus à traiter.
	 * 
	 * @param folder
	 * 		Dossier du corpus à traiter.
	 * @return
	 * 		Index repr�sentant le corpus.
	 */
	public static Index indexCorpus(String folder)
	{	Index result = null;
		//TODO méthode à compléter (TP2-ex5)
		//TODO méthode à modifier  (TP2-ex9)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	DONNÉES
	////////////////////////////////////////////////////
	/** Lexique et postings de l'index */
	//TODO champ à créer (TP1-ex12)
	/** Nombre de documents dans la collection */
	//TODO champ à créer (TP1-ex12)
	
	/**
	 * Renvoie l'entrée correspondant au terme
	 * passé en paramètre. Si une telle entrée n'existe 
	 * pas, alors la méthode renvoie {@code null}.
	 * 
	 * @param term
	 * 		Terme à rechercher.
	 * @return
	 * 		Entr�e associée au terme.
	 */
	public IndexEntry getEntry(String term)
	{	IndexEntry result = null;
		//TODO méthode à compléter (TP1-ex14)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	TOKÉNISATION
	////////////////////////////////////////////////////
	/** Objet utilisé pour tokéniser le texte lors de l'indexation */
	//TODO champ à créer (TP1-ex12)
	
	////////////////////////////////////////////////////
	//	NORMALISATION
	////////////////////////////////////////////////////
	/** Objet utilisé pour normaliser le texte lors de l'indexation */
	//TODO champ à créer (TP1-ex12)

	////////////////////////////////////////////////////
	//	STOCKAGE
	////////////////////////////////////////////////////
	/**
	 * Lecture d'un index dans le fichier spécifié.
	 * On utilise simplement le mécanisme de sérialisation
	 * de Java.
	 * 
	 * @param fileName
	 * 		Nom du fichier à lire.
	 * @return
	 * 		L'index lu dans le fichier.
	 * 
	 * @throws IOException
	 * 		Problème lors de la lecture de l'index.
	 * @throws ClassNotFoundException
	 * 		Problème lors de la lecture de l'index.
	 */
	public static Index read(String fileName) throws IOException, ClassNotFoundException
	{	Index result = null;
		//TODO méthode à compléter (TP2-ex12)
		return result;
	}
	
	/**
	 * Enregistrement de cet index dans un fichier.
	 * On utilise simplement le mécanisme de sérialisation
	 * de Java.
	 * 
	 * @param fileName
	 * 		Nom du fichier à créer (ou écraser).
	 * 
	 * @throws IOException
	 * 		Problème lors de l'écriture de l'index.
	 */
	public void write(String fileName) throws IOException
	{	//TODO méthode à compléter (TP2-ex11)
	}

	////////////////////////////////////////////////////
	//	AFFICHAGE
	////////////////////////////////////////////////////
	/**
	 * Affiche le contenu de l'index.
	 */
	public void print()
	{	//TODO méthode à compléter (TP1-ex13)
	}

	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test du constructeur et de print
		//TODO méthode à compléter (TP1-ex13)
		
		// test de getEntry
		//TODO méthode à compléter (TP1-ex14)
		
		// test de indexCorpus
		//TODO méthode à compléter (TP2-ex5)
	}
}
