package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import hmm.TagTest;
import hmm.TagTestEvaluate;
import hmm.TagTrainFile;
import hmm.TagTraining;
import hmm.TagTrainingResult;

import org.junit.Test;

import basic.BasicDataMap;
import basic.BasicStatisticData;
import basic.TPair;
import basicFiles.TextFile;

public class HmmViterbiTest {

	@Test
	public void tagOneSentence() {
		String testFile = "hw3_test_00_oneSentence.txt";
		TagTrainingResult ttr = new TagTrainingResult();
		ttr.learnAllTrainingResult();
		TagTest tt = new TagTest();
		tt.test(ttr, testFile, "testfiles/taged.txt");
		TextFile testResult = new TextFile("testfiles/taged.txt");
		assertTrue(testResult.exists());
		TagTestEvaluate tte = new TagTestEvaluate();
		tte.setTrainingResult(ttr);
		tte.evaluate("testfiles/taged.txt", "testfiles/taged_ref.txt");
		tte.outputAccuracy();
//		if (dif.size() > 0) {
//			Set<Entry<String, TPair>> entries = dif.entrySet();
//			for (Entry<String, TPair> en: entries) {
//				TPair pr = en.getValue();
//				System.out.println(en.getKey() + "/" + pr.getPair());
//			}
//		}
//		assertTrue(dif.size() == 0);
	}

	@Test
	public void tagTest() {	
		String testFile = "hw3_test_00.txt";
		TagTrainingResult ttr = new TagTrainingResult();
		ttr.learnAllTrainingResult();
		TagTest tt = new TagTest();
		tt.test(ttr, testFile, "testfiles/taged.txt");
		TextFile testResult = new TextFile("testfiles/taged.txt");
		assertTrue(testResult.exists());
		TagTestEvaluate tte = new TagTestEvaluate();
		tte.setTrainingResult(ttr);
		tte.evaluate("testfiles/taged.txt", "hw3_test_ref_00.txt");
		tte.outputAccuracy();
//		if (dif.size() > 0) {
//			
//			Set<Entry<String, TPair>> entries = dif.entrySet();
//			for (Entry<String, TPair> en: entries) {
//				TPair pr = en.getValue();
//				System.out.println(en.getKey() + "/" + pr.getPair());
//			}
//		}
//		assertTrue(dif.size() == 0);
	}

	@Test
	public void tagTestEvaluate() {
		TagTrainingResult ttr = new TagTrainingResult();
		ttr.learnAllTrainingResult();
		TagTestEvaluate tte = new TagTestEvaluate();
		tte.setTrainingResult(ttr);
		BasicDataMap dif = tte.evaluate("hw3_testfiles/taged.txt", "hw3_test_ref_00.txt");
		tte.outputAccuracy();
//		if (dif.getCount() > 0) {
//			
//			int unseenCnt = 0;
//			Set<Entry<String, BasicStatisticData>> entries = dif.entrySet();
//			for (Entry<String, BasicStatisticData> en: entries) {
//				String word = en.getKey();
//				BasicStatisticData pr = en.getValue();
//				String s = "";
//				if (ttr.wordMap.containsKey(word)) {
//					unseenCnt++;
//					s = "unseen";
//				}
//				System.out.println(word + "/" + pr.getPair() + s);
//			}
//		}
//		assertTrue(dif.size() == 0);
	}
	
//	@Test
//	public void tagTestEvaluate2() {
//		TagTrainingResult ttr = new TagTrainingResult();
//		ttr.learnAllTrainingResult();
//		TagTestEvaluate tte = new TagTestEvaluate();
//		tte.setTrainingResult(ttr);
//		TextFile t = new TextFile("testfiles/taged.txt");
//		TextFile r = new TextFile("hw3_test_ref_00.txt");		
//		List<String> ts = t.readLines();
//		List<String> rs = r.readLines();
//		for (int i = 0; i < rs.size(); i++) {
//			TextFile tsf = new TextFile("ts.txt");
//			TextFile rsf = new TextFile("rs.txt");
//			tsf.write(ts.get(i));
//			rsf.write(rs.get(i));
//			Map<String, TPair> dif = tte.evaluate("ts.txt", "rs.txt");
//			if (dif.size() > 0) {
//				if (tte.getTotalAccuracy()<0.9) {
//					System.out.println(ts.get(i));
//					System.out.println(rs.get(i));
//					tte.outputAccuracy();
//				}
//				Set<Entry<String, TPair>> entries = dif.entrySet();
//				for (Entry<String, TPair> en: entries) {
//					String word = en.getKey();
//					TPair pr = en.getValue();
//					System.out.println(word + "/" + pr.getPair() + 
//							(ttr.wordMap.containsKey(word) ? "" : " unseen"));
//				}
//			}
//		}
//	}
}
