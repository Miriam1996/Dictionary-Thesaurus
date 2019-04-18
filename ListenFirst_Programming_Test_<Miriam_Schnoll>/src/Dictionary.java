/**
 * @author Miriam Schnoll
 */
import java.util.*;

public class Dictionary {
	//hash map of strings for definitions
	private HashMap<String, String> definitions;
	//hash map string word to  set of synomnyms
	// used a hash set for quicker retrival time
	private HashMap<String,HashSet<String>  > Synonyms;
	public Dictionary() {
		definitions = new HashMap<String, String>();
		Synonyms = new HashMap<String,HashSet<String> >();
		
	}
	
	public void addWord(String word,String definition) {
		if(!definitions.containsKey(word)) {
			definitions.put(word,definition);
		}
		else {
			System.out.println("contains defintions");
		}
			
		
	}
	//return defintion from hashmap
	public String lookupWord(String word) {
		if(definitions.containsKey(word)){
			return definitions.get(word);
		}
		//assuming you are not asking to look at synonyms defintion
		else {
			throw new NullPointerException("word not found");
		}
	}
	// add word and synonym to synonyms hash map word to hash set of synonyms
	//add to word and synonym as a two way operation
	public void addSynonym(String word, String Synonym) {
		//checking that both words are in the dictionary
		if(!definitions.containsKey(word)|| !definitions.containsKey(Synonym)) {
			System.out.println("one of the word is not in the dictionary");
			return;
		}
		//doesn't have a synonym
		if(!Synonyms.containsKey(word)) {
			HashSet<String> hs = new HashSet<String>();
			hs.add(Synonym);
			Synonyms.put(word,hs);
		}
		else {
			HashSet<String> s = Synonyms.get(word);
			if(s ==null) {
				s = new HashSet<String>();
				Synonyms.put(word,s);
			}
			s.add(Synonym);
		}
		//if it doesn't have a synonym
		if(!Synonyms.containsKey(Synonym)) {
			HashSet<String> hs1 = new HashSet<String>();
			hs1.add(word);
			Synonyms.put(Synonym,hs1);
		}
		//has a synonym already
		else {
			HashSet<String> s = Synonyms.get(Synonym);
			if(s ==null) {
				s = new HashSet<String>();
				Synonyms.put(Synonym,s);
			}
			s.add(word);
		}
	}
	//returning hashset of synonyms
	//using breadth first search
	public List<String> LookupSynonyms(String word){
		
		
		List<String> sl = new LinkedList<String>();
		//have to retrive all connected words
		//breath first search to find all synonyms
		if(Synonyms.containsKey(word)) {
			//holds synonyms to get their synonyms
			Queue <String> q = new LinkedList<String> ();
			//hold already looked at synonyms
			HashSet<String> read = new HashSet<String>();
			//add to queue
			q.add(word);
			//add to visited
			read.add(word);
			
			while(!q.isEmpty()) {
				String w = q.poll();
				if( !w.equals(word))
					sl.add(w);
				HashSet<String> hs = Synonyms.get(w);
				Iterator<String> value = hs.iterator();
				while(value.hasNext()) {
				String val = value.next();
				if(!read.contains(val)) {
					read.add(val);
					q.add(val);
				
				}
			}
				
			}
			
		}
		else {
			throw new NullPointerException("no synonyms found");
		}
		//show throw a not found exception no synonym
		return sl;
	
	}
	
	
}
	

