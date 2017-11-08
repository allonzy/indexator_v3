package indexation.processing;

import indexation.Index;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.content.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Objet construisant un index prenant 
 * la forme d'un fichier inversé. Il a
 * pour cela besoin de recevoir la liste
 * normalisée des paires (tokens, docId).
 */
public class Builder
{	
	private final int INDEX_SIZE = 1000;
	/**
	 * Construit l'index à partir
	 * des tokens passés en paramètres.
	 * 
	 * @param tokens
	 * 		Liste normalisée de tokens à traiter.
	 * @return
	 * 		L'index produit.
	 */
	public Index buildIndex(List<Token> tokens)
	{	
		long start = System.currentTimeMillis();
		Index index = new Index(INDEX_SIZE);
		Collections.sort(tokens);
		this.filterTokens(tokens);
		this.buildPostings(tokens, index);
		//TODO méthode à modifier  (TP5-ex6)
		long end = System.currentTimeMillis();
		System.out.println("Builder.buildIndex(): "+(end-start)+"ms");
		return index;
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
	{	int countDistinctTokens = 0;
		Iterator<Token> it = tokens.iterator();
		Token lastToken = null;
		while(it.hasNext()){
			Token token = it.next();
			if(token.equals(lastToken)){
				it.remove();
			}else{
				lastToken = token;
				countDistinctTokens ++;
			}
			
		}
		//TODO méthode à modifier  (TP5-ex4)
		return countDistinctTokens;
	}
	
	/**
	 * Construit un index à partir de
	 * la liste de tokens normalisée, triée et filtrée
	 * passée en paramètre.
	 * 
	 * @param tokens
	 * 		Liste normalisée, triée et filtrée de tokens.
	 * @param index
	 * 		L'index obtenu, sous forme de fichier inverse.
	 * @return
	 * 		Nombre de postings listés.
	 */
	private int buildPostings(List<Token> tokens, Index index)
	{	

		Iterator<Token> it = tokens.iterator();
		String previousTerm = null;
		// on initialise les variables pour l'element 0 de la liste
		
		Token token = it.next();
		IndexEntry indexEntry = new IndexEntry(token.getType());
		List<Posting> postings = new ArrayList<Posting>();
		Posting posting = new Posting(token.getDocId());
		postings.add(posting);
		
		int postingscount = 1;// on as déjà traiter l'element 0
		while(it.hasNext()){
			token = it.next();
			String term = token.getType();
			posting = new Posting(token.getDocId());

			if(term.equals(previousTerm)){
				postings.add(posting);
			}else{
				indexEntry.setPostings(postings);
				indexEntry.setFrequency(postings.size());
				indexEntry = new IndexEntry(term);
				postings.clear();
				postings.add(posting);
				previousTerm = term;
			}
			postingscount ++;
		}
		if(postings.size() > 0 ){
			indexEntry.setPostings(postings);
		}
		//TODO méthode à modifier  (TP2-ex4)
		//TODO méthode à modifier  (TP5-ex5)
		return postingscount;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	// test de filterTokens
		Builder builder = new Builder();
		List<Token> tokens = new ArrayList();
		tokens.add(new Token("t1", 0));
		tokens.add(new Token("t1", 0));
		tokens.add(new Token("t2", 0));
		tokens.add(new Token("t1", 1));
		tokens.add(new Token("t1", 1));
		tokens.add(new Token("t1", 2));
		List<Token> tokensCopy = new ArrayList(tokens);
		//TODO méthode à compléter (TP2-ex1)
		int tokensCount = builder.filterTokens(tokens);
		System.out.println("Avant filtre : "+tokensCopy+"\nAprès filtre : "+tokens+"\nNombre de tokens:"+tokensCount);
		// test de buildPostings
		//TODO méthode à compléter (TP2-ex2)
		
		// test de buildIndex
		//TODO méthode à compléter (TP2-ex3)
	}
}
