package indexation.processing;

import indexation.Index;
import indexation.content.Token;

import java.util.List;

/**
 * Objet construisant un index prenant 
 * la forme d'un fichier inversé. Il a
 * pour cela besoin de recevoir la liste
 * normalisée des paires (tokens, docId).
 */
public class Builder
{	/**
	 * Construit l'index à partir
	 * des tokens passés en paramètres.
	 * 
	 * @param tokens
	 * 		Liste normalisée de tokens à traiter.
	 * @return
	 * 		L'index produit.
	 */
	public Index buildIndex(List<Token> tokens)
	{	Index result = null;
		//TODO méthode à compléter (TP2-ex3)
		//TODO méthode à modifier  (TP2-ex9)
		//TODO méthode à modifier  (TP5-ex6)
		return result;
	}
	
	/**
	 * Supprime de la liste les occurrences
	 * multiples de tokens, à condition qu'ils
	 * appartiennent au même document.
	 * Bien sûr, on garde quand même une occurrence.
	 * 
	 * @param tokens
	 * 		La liste normalisée et triée de tokens à traiter.
	 * @return
	 * 		Nombre de termes distincts dans la liste.
	 */
	private int filterTokens(List<Token> tokens)
	{	int result = 0;
		//TODO méthode à compléter (TP2-ex1)
		//TODO méthode à modifier  (TP5-ex4)
		return result;
	}
	
	/**
	 * Construit un index à partir de
	 * la liste de tokens normalisée, triée et filtrée
	 * passée en paramètre.
	 * 
	 * @param tokens
	 * 		Liste normalis�e, triée et filtrée de tokens.
	 * @param index
	 * 		L'index obtenu, sous forme de fichier inverse.
	 * @return
	 * 		Nombre de postings listés.
	 */
	private int buildPostings(List<Token> tokens, Index index)
	{	int result = 0;
		//TODO méthode à compléter (TP2-ex2)
		//TODO méthode à modifier  (TP2-ex4)
		//TODO méthode à modifier  (TP5-ex5)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test de filterTokens
		//TODO méthode à compléter (TP2-ex1)
		
		// test de buildPostings
		//TODO méthode à compléter (TP2-ex2)
		
		// test de buildIndex
		//TODO méthode à compléter (TP2-ex3)
	}
}
