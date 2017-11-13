package query;

import indexation.Index;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Objet capable de traiter une
 * requête booléenne sur un index.
 */
public class AndQueryEngine
{	
	////////////////////////////////////////////////////
	//	INDEX
	////////////////////////////////////////////////////
	/** Index de référence */
	Index index;
	/**
	 * Comparateur traitant deux listes de postings.
	 * On utilise simplement leurs longueurs.
	 */
	public final static Comparator<List<Posting>> COMPARATOR = new Comparator<List<Posting>>(){

		@Override
		public int compare(List<Posting> o1, List<Posting> o2) {
			return o1.size() - o2.size();
		}
		
	};
	/**!
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
	{	
		this.index = index;
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
	{	
		System.out.println("Processing Request \""+query+"\"");
		long start = System.currentTimeMillis();
		List<List<Posting>> queryPostings = new ArrayList<List<Posting>>();
		this.splitQuery(query, queryPostings);		
		List<Posting> result = this.processConjunctions(queryPostings);
		//TODO méthode à modifier  (TP5-ex13)
		//TODO méthode à modifier  (TP6-ex10)
		long end = System.currentTimeMillis();
		System.out.println("Query processed, duration="+(end-start)+" ms");
		return result;
	}
	

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
	public void splitQuery(String query, List<List<Posting>> result)
	{	
		Tokenizer tokenizer = new Tokenizer();
		Normalizer normalizer = new Normalizer();
		
		//TODO méthode à compléter (TP3-ex2)
		List<String> tokenizedQuery = tokenizer.tokenizeString(query);
		List<String> normalizedQuery = new ArrayList<String>();
		for (String tokenString : tokenizedQuery ){
			String normalizedToken = normalizer.normalizeType(tokenString);
			normalizedQuery.add(normalizedToken);
			IndexEntry indexEntry = this.index.getEntry(normalizedToken);
			if(indexEntry != null){
				result.add(indexEntry.getPostings());
			}else{
				result.add(new ArrayList<Posting>());
			}
		}
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
	public Posting processPositionalConjunction(Posting postings1,Posting postings2, int threshold)
	{		
		return null;
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
	public List<Posting> processConjunction(List<Posting> list1, List<Posting> list2)
	{			
		List<Posting> result = new ArrayList<Posting>();
		Iterator<Posting> itPos1 = list1.iterator();
		Iterator<Posting> itPos2 = list2.iterator();
		if (itPos1.hasNext() == false || itPos2.hasNext() == false){
			return result;
		}
		Posting pos1 = itPos1.next();
		Posting pos2 = itPos2.next();
		while  (itPos1.hasNext() && itPos2.hasNext() ){
			if(pos1.getDocId() < pos2.getDocId()){
				pos1 = itPos1.next();
			}
			else if (pos2.getDocId() < pos1.getDocId()){
				pos2 = itPos2.next();
			}
			else/*(pos1 == pos2 )*/{
				result.add(pos1);
				pos1 = itPos1.next();
				pos2 = itPos2.next();
			}
	}
	return result;
		//TODO méthode à modifier  (TP5-ex9)
		//TODO méthode à modifier  (TP6-ex5)
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
	public List<Posting> processConjunctions(List<List<Posting>> postingsList)
	{
		Collections.sort(postingsList,COMPARATOR);
		Iterator<List<Posting>> itPostingsList = postingsList.iterator();
		List<Posting> result = itPostingsList.next();
		while(itPostingsList.hasNext()){
			List<Posting> currentPostingList = itPostingsList.next();
			result = this.processConjunction(result, currentPostingList);
		}
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
}
