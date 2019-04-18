import java.io.File;
import java.util.*;
/**
 * 
 * @author Miriam Schnoll
 * it reads from a file with command line argument
 * prints result to console
 * 
 *
 */
public class Main {
	public static void main(String[] args) {
		try {
		Dictionary d = new Dictionary();
		if(args.length==0) {
			System.exit(0);
		}
		File file = new File(args[0]);
		Scanner in = new Scanner(file);
		while(in.hasNext()) {
			String[] s = in.next().split(":");
			if (s.length == 2) {
				//matched syntax to the input but can differ in caps
				if (s[0].toUpperCase().equals("lookupWord".toUpperCase())) {
					System.out.println(s[1] + ":" + d.lookupWord(s[1]));
				} else if (s[0].toUpperCase().equals("lookupSynonyms".toUpperCase())) {
					System.out.print(s[1] + ":");
					List<String> l = d.LookupSynonyms(s[1]);
					Iterator<String> iter = l.iterator();
					while (iter.hasNext()) {
						System.out.print(iter.next());
						if (iter.hasNext())
							System.out.print(",");
					}
					System.out.println();

				}
			} else if (s.length == 3) {
				if (s[0].toUpperCase().equals("addWord".toUpperCase()))
					d.addWord(s[1], s[2]);

				else if (s[0].toUpperCase().equals("addSynonym".toUpperCase()))
					d.addSynonym(s[1], s[2]);

			}
		}
		in.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
