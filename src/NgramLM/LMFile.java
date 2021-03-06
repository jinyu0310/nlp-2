package NgramLM;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import basicFiles.SentenceParser;
import basicFiles.TextFile;

public class LMFile extends TextFile{

	public LMFile(String pathname) {
		super(pathname);
	}

	public void getLMResult(DataMap uniMap, DataMap biMap) {
		List<String> unigrams = new ArrayList<String>();
		List<String> bigrams = new ArrayList<String>();		
		getResultLines(unigrams, bigrams);
		getUnigramResult(unigrams, uniMap);
		getBigramResult(bigrams, biMap);
	}
	
	private void getBigramResult(List<String> bigrams, DataMap biMap) {
		for (String line: bigrams) {
			SentenceParser sp = new TriSentenceParser();
			sp.setSeperator(' ');
			List<String> words = new ArrayList<String>();
			sp.putWordsToCollection(line, words);
			StatisticData sd = new StatisticData();
			sd.setLogProbability(Double.parseDouble(words.get(0)));
			biMap.createKey(words.get(1) + " " + words.get(2), sd);
		}
	}

	private void getUnigramResult(List<String> unigrams, DataMap uniMap) {
		for (String line: unigrams) {
			SentenceParser sp = new TriSentenceParser();
			sp.setSeperator(' ');
			List<String> words = new ArrayList<String>();
			sp.putWordsToCollection(line, words);
			StatisticData sd = new StatisticData();
			sd.setLogProbability(Double.parseDouble(words.get(0)));
			sd.setLogAlpha(Double.parseDouble(words.get(2)));
			uniMap.createKey(words.get(1), sd);
		}
	}

	public void getResultLines(List<String> unigrams, List<String> bigrams) {
		List<String> lines = this.readLines();	
		boolean readUnigram = false;
		boolean readBigram = false;
		for (String line: lines) {
		    if (line.length()<1) {
		    	continue;
		    }
			if (line.indexOf("unigram")>-1) {
				readUnigram = true;
			}
			else {
				if (line.indexOf("bigram")>-1) {
					readUnigram = false;
					readBigram = true;
				}
				else {
					if (readUnigram) {
						unigrams.add(line);
					}
					else if (readBigram) {
						bigrams.add(line);
					}
				}
			}
		}
	}

}
