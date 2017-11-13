package indexation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import indexation.content.IndexEntry;
import indexation.content.Token;
import indexation.processing.Builder;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;

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
	{	
		this.size = 0;
		this.data = new IndexEntry[size];
		this.tokenizer = new Tokenizer();
		this.normalizer = new Normalizer();
	}
	
	/**
	 * Méthode de classe permettant la création
	 * d'un index prenant la forme d'un fichier inverse.
	 * Le dossier passe en paramètre est supposé
	 * contenir le corpus à traiter.
	 * @throws UnsupportedEncodingException,FileNotFoundException
	 * @param folder
	 * 		Dossier du corpus à traiter.
	 * @return
	 * 		Index représentant le corpus.
	 */
	public static Index indexCorpus(String folder) throws UnsupportedEncodingException,FileNotFoundException
	{	
		long start = System.currentTimeMillis();
		Tokenizer tokenizer = new Tokenizer();
		Normalizer normalizer = new Normalizer();
		Builder builder = new Builder();
		List<Token> tokens = new LinkedList<Token>();
		tokenizer.tokenizeCorpus(folder,tokens);
		normalizer.normalizeTokens(tokens);
		Index index = builder.buildIndex(tokens);
		long end = System.currentTimeMillis();
		System.out.println("Index.indexCorpus :"+(end-start)+"ms");
		return index;
		//TODO méthode à modifier  (TP2-ex9)
	}
	
	////////////////////////////////////////////////////
	//	DONNÉES
	////////////////////////////////////////////////////
	/** Lexique et postings de l'index */
	private IndexEntry[] data;
	/** Nombre de documents dans la collection */
	private int size;
	/**
	 * Renvoie l'entrée correspondant au terme
	 * passé en paramètre. Si une telle entrée n'existe 
	 * pas, alors la méthode renvoie {@code null}.
	 * 
	 * @param term
	 * 		Terme à rechercher.
	 * @return
	 * 		Entrée associée au terme.
	 */
	public IndexEntry getEntry(String term)
	{	
		IndexEntry indexEntryTerm = new IndexEntry(term);
		int position = Arrays.binarySearch(this.data, indexEntryTerm);
		return (position > 0)?this.data[position]:null;
	}
	public void setData(IndexEntry[] data,int size){
		this.data = data;
	}
	public IndexEntry[] getData(){
		return this.data;
	}
	public void addToData(IndexEntry indexEntry){
		this.data[this.size] = indexEntry;
		this.size ++;
	}
	////////////////////////////////////////////////////
	//	TOKÉNISATION
	////////////////////////////////////////////////////
	/** Objet utilisé pour tokéniser le texte lors de l'indexation */
	Tokenizer tokenizer;
	
	////////////////////////////////////////////////////
	//	NORMALISATION
	////////////////////////////////////////////////////
	/** Objet utilisé pour normaliser le texte lors de l'indexation */
	Normalizer normalizer;
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
	{
		long start = System.currentTimeMillis();
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Index i = (Index) ois.readObject();
		long end = System.currentTimeMillis();
		long duration = start - end;
		System.out.println("Index loaded,duration ="+duration+"ms");
		return i;
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
	{
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}
	////////////////////////////////////////////////////
	//	AFFICHAGE
	////////////////////////////////////////////////////
	/**
	 * Affiche le contenu de l'index.
	 */
	public void print()
	{	
		for (IndexEntry indexEntry : this.data){
			if(indexEntry != null){
				System.out.println(indexEntry);
			}
		}
	}

	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	public static void main(String[] args) 
	{	
		IndexEntry indexEntry1 = new IndexEntry("lorem");
		IndexEntry indexEntry2 = new IndexEntry("ipsum");
		IndexEntry indexEntry3 = new IndexEntry("sit");
		
		// test du constructeur et de print
		Index index1 = new Index(100);
		index1.addToData(indexEntry1);
		index1.addToData(indexEntry2);
		index1.addToData(indexEntry3);
		index1.print();
		//TODO méthode à compléter (TP1-ex13)
		
		// test de getEntry
		//TODO méthode à compléter (TP1-ex14)
		
		// test de indexCorpus
		//TODO méthode à compléter (TP2-ex5)
	}
}
