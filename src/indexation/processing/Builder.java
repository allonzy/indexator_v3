package indexation.processing;

import indexation.Index;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.content.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Objet construisant un index prenant 
 * la forme d'un fichier inversé. Il a
 * pour cela besoin de recevoir la liste
 * normalisée des paires (tokens, docId).
 */
public class Builder
{	
	/**
	 * Construit l'index à partir
	 * des tokens passés en paramètres.
	 * 
	 * @param tokens
	 * 		Liste normalisée de tokens à traiter.
	 * @return
	 * 		L'index produit.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public Index buildIndex(List<Token> tokens,Normalizer normalizer,Tokenizer tokenizer) throws FileNotFoundException, UnsupportedEncodingException
	{	
		long start = System.currentTimeMillis();
		
		long startSection = 0;
		long endSection = 0 ;
		
		System.out.println("Sorting tokens...");
		startSection = System.currentTimeMillis();
		Collections.sort(tokens);
		endSection = System.currentTimeMillis();
		System.out.println(tokens.size()+" tokens sorted, duration="+(endSection-startSection)+"ms");
		
		System.out.println("Filtering tokens ...");
		startSection = System.currentTimeMillis();
		int nbTerms = this.filterTokens(tokens);
		endSection = System.currentTimeMillis();
		System.out.println(tokens.size()+" tokens remaining, corresponding to "+nbTerms+" terms, duration="+(endSection-startSection)+"ms");
		Index index =  new Index(nbTerms,normalizer,tokenizer);
		System.out.println("Building posting list"); 
		startSection = System.currentTimeMillis();
		this.buildPostings(tokens, index);		// test de buildIndex
		endSection = System.currentTimeMillis();
		System.out.println(tokens.size()+" postings listed, duration="+(endSection-startSection)+"ms");	//TODO méthode à modifier  (TP5-ex6)
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
	{	
		int countDistinctTokens = 1;
		Iterator<Token> it = tokens.iterator();
		Token lastToken = null;
		while(it.hasNext()){
			Token token = it.next();
			if(token.equals(lastToken)){
				it.remove();
			}else{
				if(lastToken != null && token.getType().equals(lastToken.getType()) == false ){
					countDistinctTokens ++;
				}
				lastToken = token;
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
		// on initialise les variables pour l'element 0 de la liste
		
		Token token = it.next();
		String previousTerm = token.getType();
		IndexEntry indexEntry = new IndexEntry(token.getType());
		List<Posting> postings = new ArrayList<Posting>();
		Posting posting = new Posting(token.getDocId());
		postings.add(posting);
		int postingscount = 1;// on as déjà traiter l'element 0
		int postingByTermCount = 1;
		while(it.hasNext()){
			token = it.next();
			String term = token.getType();
			posting = new Posting(token.getDocId());
			if(term.equals(previousTerm) && it.hasNext() == true){
				postings.add(posting);
				postingscount ++;
				postingByTermCount ++;
			}else{
				indexEntry.setPostings(postings);
				indexEntry.setFrequency(postingByTermCount);
				index.addToData(indexEntry);
				postingByTermCount = 1;
				indexEntry = new IndexEntry(term);
				postings = new ArrayList<Posting>();
				postings.add(posting);
				previousTerm = term;
			}
		}
		//TODO méthode à modifier  (TP5-ex5)
		return postingscount;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException 
	{	
		// test de filterTokens
		Builder builder = new Builder();
		Tokenizer tokenizer = new Tokenizer();
		Normalizer normalizer = new Normalizer();
		List<Token> tokens = new ArrayList();
		tokens.add(new Token("t1", 0,1));
		tokens.add(new Token("t1", 0,2));
		tokens.add(new Token("t2", 0,3));
		tokens.add(new Token("t1", 1,4));
		tokens.add(new Token("t1", 1,5));
		tokens.add(new Token("t1", 2,6));
		Collections.sort(tokens);
		List<Token> tokensCopy = new ArrayList(tokens);
		//TODO méthode à compléter (TP2-ex1)
		int tokensCount = builder.filterTokens(tokens);
		System.out.println("Avant filtre : "+tokensCopy+"\nAprès filtre : "+tokens+"\nNombre de tokens:"+tokensCount);
		// test de buildPostings
		//TODO méthode à compléter (TP2-ex2)
		
		Index index = new Index(1000);
		int nbPostings = builder.buildPostings(tokens,index);
		index.print();
		System.out.println(nbPostings+" postings for test corpus");
		//TODO méthode à compléter (TP2-ex3)
		Index index2 = Index.indexCorpus(".."+File.separator+"Common"+File.separator+"test");
		
	}
}
