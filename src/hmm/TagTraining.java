package hmm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import basic.BasicStatisticData;
import basic.ResultParser;
import basic.TPair;
import basicFiles.TextFile;

public class TagTraining extends TagTrainingResult{
	private TagDataFile trainFile;

	public TagTraining(TagDataFile tdf) {
		super();
		this.trainFile = tdf;
	}

	public void train() {
		getTagsForWord();
		calculateProbObservation();
		getTagPairs();
		calculateProbTransition();
		outputTrainResultTag("hw3_tag.txt");
		outputTrainResultWord("hw3_word.txt");
		outPutWordTagData("hw3_word_tag.txt");
		outPutTagTagData("hw3_tag_tag.txt");
	}
	

	private void calculateProbTransition() {
		Set<Entry<String, BasicStatisticData>> entries = tagTagPairMap.entrySet();
		for (Entry<String, BasicStatisticData> en: entries) {
			TPair pr = new TPair(en.getKey(), ResultParser.DEFAULT_SEPARATOR);
			BasicStatisticData sd = en.getValue();
			int base = tagMap.getCount(pr.getS1());
			int cnt = sd.getCount();
			double prob = (double)cnt / (double)base;
			sd.setProbability(prob);
		}
	}

	private void getTagPairs() {
		List<TPair> pairs = new ArrayList<TPair>();
		trainFile.getTagPairs(pairs);
		for (TPair pr: pairs) {
			String t1 = pr.getS1();
			String t2 = pr.getS2();
			//tag map
			if (tagMap.containsKey(t1)) {
				tagMap.updateKey(t1, t2);						
			}
			else {
				tagMap.createKey(t1, t2);				
			}
			//tag tag pair map
			String pair = pr.getPair();
			if (tagTagPairMap.containsKey(pair)) {
				tagTagPairMap.increaseCount(pair);
			}
			else {
				tagTagPairMap.createKey(pair);
			}
		}
	}

	public void calculateProbObservation() {
		Set<Entry<String, BasicStatisticData>> entries = wordTagPairMap.entrySet();
		for (Entry<String, BasicStatisticData> en: entries) {
			TPair pr = new TPair(en.getKey(), ResultParser.DEFAULT_SEPARATOR);
			BasicStatisticData sd = en.getValue();
			String word = pr.getS1();
			int base = wordMap.getCount(word);
			int cnt = sd.getCount();
			double prob = (double)cnt / (double)base;
			//test
			if (word.equalsIgnoreCase("it")) {
				System.out.println(word + ","
						+ base + ","
						+ cnt + ",");
			}
			sd.setProbability(prob);
		}
	}

	private void getTagsForWord() {
		List<TPair> pairs = new ArrayList<TPair>(); 
		trainFile.getWordTagPairs(pairs);
		for (TPair pr: pairs) {
			String word = pr.getS1();
			String tag = pr.getS2();
			//word map
			if (wordMap.containsKey(word)) {
				wordMap.updateKey(word, tag);						
			}
			else {
				wordMap.createKey(word, tag);				
			}
			//word tag pair map
			String pair = word + " " + tag;
			if (wordTagPairMap.containsKey(pair)) {
				wordTagPairMap.increaseCount(pair);
			}
			else {
				wordTagPairMap.createKey(pair);
			}
		}
	}

}
