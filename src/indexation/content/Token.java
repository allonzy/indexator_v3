package indexation.content;

/**
 * Représente un token, i.e. un couple
 * (mot,numéro) de document.
 */
public class Token implements Comparable<Token>
{	

	/**
	 * Crée un nouveau token à partir
	 * du type et du numéro de document
	 * passés en paramètres.
	 * 
	 * @param type
	 * 		Type dont le token est une occurrence.
	 * @param docId
	 * 		Numéro du document concerné.
	 */
	public Token(String type, int docId)
	{	
		this.type = type;
		this.docId = docId;
	}
	
	////////////////////////////////////////////////////
	//	TYPE
	////////////////////////////////////////////////////
	/** Type associé au token */
	private String type;

	////////////////////////////////////////////////////
	//	DOC ID
	////////////////////////////////////////////////////
	/** Numéro du document contenant le token */
	private Integer docId;
	
	////////////////////////////////////////////////////
	//	POSITION
	////////////////////////////////////////////////////
	/** Position du token dans le document */
	//TODO champ à créer (TP5-ex2)
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(Token token)
	{	Integer result = this.type.compareTo(token.type);
		if (result == 0){
			return this.docId.compareTo(token.docId);
		}else{
			return result;
		}
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex4)
		return "("+this.type+", "+String.valueOf(this.docId)+")";
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		if(o instanceof Token){
			return (this.compareTo((Token)o) == 0);	
		}else{
			return false;
		}
		
	}
	
	////////////////////////////////////////////////////
	//	TEST!
	////////////////////////////////////////////////////
	public static void main(String[] args)
	{	// test du constructeur et de toString
		Boolean fail = false;
		Token a = new Token("mot1",123);
		System.out.println(a.toString());
		
		// test de equals
		// TODO méthode à compléter (TP1-ex4)
		Token b = new Token("mot1",123);
		Token c = new Token("mot1",500);
		Token d = new Token("mot2",600);
		
		if(a.equals(b) == false){
			System.out.println("test 1 fail,"+a.toString()+" must be equal to "+b.toString());
			fail = true;
		}
		if (a.equals(c)){
			System.out.println("test 2 fail, "+a.toString()+" must not be equal to "+c.toString());
			fail = true;
		}
		if (a.equals(d)){
			System.out.println("test 3 fail, "+a.toString()+" must not be equal to "+d.toString());
			fail = true;
		}
		// test de compareTo
		// TODO méthode à compléter (TP1-ex4)
		if(fail == false){
			System.out.println("all test have succeded");
		}
	}
}
