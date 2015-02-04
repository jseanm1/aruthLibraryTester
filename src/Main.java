import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNode;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;


public class Main {
	
	private final static String WORDNETPATH = "conf/file_properties.xml";
	private static FileInputStream inputStream;
	private static Dictionary dictionary = null;
	private static IndexWord word = null;
	
	public static void main (String arg[]) throws FileNotFoundException, JWNLException {
		
		String noun[] = {"මුව","බට","සමය","ශීර්ෂය","ඉර","කඩ","පාදම","පතුල","අඩිය","මනෝමය_ස්වභාවය"};
		
		for (String n : noun) {
			doIt(n);
		}
	
	}
	
	private static void doIt (String noun) throws FileNotFoundException, JWNLException {
		inputStream = new FileInputStream(WORDNETPATH);
		dictionary = Dictionary.getInstance(inputStream);
				
		word = dictionary.getIndexWord(POS.NOUN, noun);	
		
		System.out.println(word.getLemma());
		// since -above- is printed, the word is in the WN
		
		List<Synset> synset = word.getSenses();
		
		PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(synset.get(0));
		
		System.out.println(hypernyms.size());
		
		for (PointerTargetNode h : hypernyms) {
			System.out.println(h.getSynset());
		}
	}

}