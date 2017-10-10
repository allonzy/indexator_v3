package query;

import indexation.Index;
import indexation.content.IndexEntry;
import indexation.content.Posting;

import java.util.List;

/**
 * Objet capable de traiter une
 * requête booléenne sur un index.
 */
public class AndQueryEngine
{	/**
	 * Initialise ce moteur de requête avec
	 * l'index passé en paramêtre, qui sera
	 * considéré comme index de référence
	 * lors de l'évaluation des requêtes
	 * reçues.
	 * 
	 * @param index
	 * 		Index de référence.
	 */
	public AndQueryEngine(Index index)
	{	//TODO méthode à modifier (TP3-ex1)
	}
	
	////////////////////////////////////////////////////
	//	TRAITEMENT GENERAL
	////////////////////////////////////////////////////
	/**
	 * Traite la requête passée en paramètre
	 * et renvoie la liste des documents concernés.
	 * 
	 * @param query.
	 * 		Requête à traiter.
	 * @return
	 * 		Liste des documents concernés.
	 */
	public List<Posting> processQuery(String query)
	{	List<Posting> result = null;
		//TODO méthode à compléter (TP3-ex6)
		//TODO méthode à modifier  (TP5-ex13)
		//TODO méthode à modifier  (TP6-ex10)
		return result;
	}
	
	/**
	 * Comparateur traitant deux listes de postings.
	 * On utilise simplement leurs longueurs.
	 */
	//TODO champ à créer (TP3-ex4)
	//TODO champ à modifier (TP6-ex5)
	
	////////////////////////////////////////////////////
	//	CONJONCTIONS
	////////////////////////////////////////////////////
	/**
	 * Tokénise et normalise la requête, de manière
	 * à obtenir une liste de termes. Ces termes
	 * sont ensuite traités pour récupérer les
	 * entrées correspondantes dans l'index, et
	 * surtout leurs listes de postings.
	 * 
	 * @param query
	 * 		Requête à traiter.
	 * @param result
	 * 		Liste résultat à compléter, qui doit contenir à
	 * 		la fin du traitement les postings de l'index 
	 * 		correspondant aux termes obtenus après nettoyage 
	 * 		de la requête. 
	 */
	private void splitQuery(String query, List<List<Posting>> result)
	{	//TODO méthode à compléter (TP3-ex2)
		//TODO méthode à modifier  (TP5-ex11)
		//TODO méthode à modifier  (TP6-ex8)
	}

	/**
	 * Décompose la requête en fonction de la présence
	 * et de la position d'opérateurs ET positionnel.
	 * 
	 * @param query
	 * 		Requête à traiter.
	 * @return
	 * 		Liste de listes de postings, chaque sous-liste correspondant à un ET positionnel.
	 */
	private void splitQuery(String query, List<List<Posting>> result, List<Integer> thresholds)
	{	//TODO méthode à compléter (TP5-ex12)
	}
	
	/**
	 * Combine les deux listes de positions passées
	 * en paramètre en utilisant l'opérateur ET positionnel.
	 * 
	 * @param list1
	 * 		Première liste de positions, sous la forme d'un posting.
	 * @param list2
	 * 		Seconde liste de positions, elle aussi sous la forme d'un posting.
	 * @param threshold
	 * 		Paramètre de l'opérateur ET positionnel.
	 * @return
	 * 		Le résultat de ET positionnel sur ces deux listes, sous la forme
	 * 		d'un nouvel objet de classe {@code Posting}.
	 */
	private Posting processPositionalConjunction(Posting posting1, Posting posting2, int threshold)
	{	Posting result = null;
		//TODO méthode à compléter (TP5-ex8)
		return result;
	}
	
	/**
	 * Combine les deux listes de postings passées
	 * en paramètre en utilisant l'opérateur ET.
	 * 
	 * @param list1
	 * 		Première liste de postings.
	 * @param list2
	 * 		Seconde liste de postings.
	 * @return
	 * 		Le résultat de ET sur ces deux listes.
	 */
	private List<Posting> processConjunction(List<Posting> list1, List<Posting> list2)
	{	List<Posting> result = null;
		//TODO méthode à compléter (TP3-ex3)
		//TODO méthode à modifier  (TP5-ex9)
		//TODO méthode à modifier  (TP6-ex5)
		return result;
	}

	/**
	 * Traite une conjonction de plus de
	 * deux termes.
	 * 
	 * @param lists
	 * 		Liste de postings de l'index correspondant aux termes à traiter.
	 * @return
	 * 		Intersection de toutes les listes de postings.
	 */
	private List<Posting> processConjunctions(List<List<Posting>> postings)
	{	List<Posting> result = null;
		//TODO méthode à compléter (TP3-ex5)
		//TODO méthode à modifier  (TP5-ex10)
		//TODO méthode à modifier  (TP6-ex5)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	POIDS
	////////////////////////////////////////////////////
	/**
	 * Calcule la pondération log-fréquence
	 * associée à un terme dans un document.
	 * 
	 * @param tf
	 * 		Fréquence du terme dans le document.
	 * @return
	 * 		Poids correspondant.
	 */
	private float processWf(Posting posting)
	{	float result = 0;
		//TODO méthode à compléter (TP6-ex6)
		return result;
	}
	
	/**
	 * Calcule la fréquence de document inverse
	 * associée à un terme dans une collection.
	 * 
	 * @param tf
	 * 		Fréquence du terme dans la collection, 
	 * 		exprimée en nombre de documents.
	 * @return
	 * 		Fréquence inverse correspondant.
	 */
	private float processIdf(IndexEntry entry)
	{	float result = 0;
		//TODO méthode à compléter (TP6-ex7)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	SCORES
	////////////////////////////////////////////////////
	/**
	 * Trie les documents en fonction de leur similarité avec
	 * la requête spécifiée, et ne garde que les {@code k} plus
	 * pertinents (ainsi que leurs scores).
	 * 
	 * @param queryEntries
	 * 		Entrées correspondant à la requête à traiter.
	 * @param k
	 * 		Nombre de documents désiré.
	 * @param docIds
	 * 		DocIds des {@code k} documents les plus pertinents.
	 * @param docScores
	 * 		Scores associés à ces documents.
	 */
	private void sortDocuments(List<IndexEntry> queryEntries, int k, List<DocScore> docScores)
	{	//TODO méthode à compléter (TP6-ex9)
	}
	
	////////////////////////////////////////////////////
	//	INDEX
	////////////////////////////////////////////////////
	/** Index de référence */
	//TODO champ à créer (TP3-ex1)
}
