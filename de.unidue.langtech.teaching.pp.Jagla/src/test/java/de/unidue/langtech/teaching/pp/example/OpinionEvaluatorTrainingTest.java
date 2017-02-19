package de.unidue.langtech.teaching.pp.example;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.example.newType.OpinionEvaluator;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.EvaluationTendency;

public class OpinionEvaluatorTrainingTest {
	@Test
	public void testTrainingEvaluateOpinion() throws UIMAException {
		List<String> positiveOpinions = new ArrayList<String>();
		List<String> neutralOpinions = new ArrayList<String>();
		List<String> negativeOpinions = new ArrayList<String>();
		
		List<String> wrongEvaluated = new ArrayList<String>();
		
		double correct = 0;
		double wrong = 0;
		
		FileReader fr;
		BufferedReader br;
		String line;
		try{
			fr = new FileReader("src/test/resources/test/positiveOpinions.txt");
			br = new BufferedReader(fr);
			line="";
			while((line = br.readLine()) != null){
				positiveOpinions.add(line);
			}
			
			fr = new FileReader("src/test/resources/test/neutralOpinions.txt");
			br = new BufferedReader(fr);
			line="";
			while((line = br.readLine()) != null){
				neutralOpinions.add(line);
			}
			
			fr = new FileReader("src/test/resources/test/negativeOpinions.txt");
			br = new BufferedReader(fr);
			line="";
			while((line = br.readLine()) != null){
				negativeOpinions.add(line);
			}
		}
		catch(Exception e){e.printStackTrace();}
		
		JCas jcas;
		AnalysisEngineDescription opEval;
		AnalysisEngine lEngine;
		EvaluationTendency eval;
		
		for(int i=0; i<positiveOpinions.size(); i++){
			jcas = JCasFactory.createJCas();
			jcas.setDocumentText(positiveOpinions.get(i));
			jcas.setDocumentLanguage("de");
			
			opEval = createEngineDescription(OpinionEvaluator.class, OpinionEvaluator.PARAM_INPUT_FILE_POSITIVE, "src/test/resources/test/positiveWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEUTRAL, "src/test/resources/test/neutralWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEGATIVE, "src/test/resources/test/negativeWords.txt");
			lEngine = createEngine(opEval);
			lEngine.process(jcas);
			
			eval = JCasUtil.selectSingle(jcas, EvaluationTendency.class);
			if(eval.getEvalTend()==1)correct++;
			else{
				wrong++;
				wrongEvaluated.add(("Positive, but evaluated:"+eval.getEvalTend()+" ; "+positiveOpinions.get(i)));
			}
		}
		
		for(int i=0; i<neutralOpinions.size(); i++){
			jcas = JCasFactory.createJCas();
			jcas.setDocumentText(neutralOpinions.get(i));
			jcas.setDocumentLanguage("de");
			
			opEval = createEngineDescription(OpinionEvaluator.class, OpinionEvaluator.PARAM_INPUT_FILE_POSITIVE, "src/test/resources/test/positiveWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEUTRAL, "src/test/resources/test/neutralWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEGATIVE, "src/test/resources/test/negativeWords.txt");
			lEngine = createEngine(opEval);
			lEngine.process(jcas);
			
			eval = JCasUtil.selectSingle(jcas, EvaluationTendency.class);
			if(eval.getEvalTend()==0)correct++;
			else{
				wrong++;
				wrongEvaluated.add(("Neutral, but evaluated:"+eval.getEvalTend()+" ; "+neutralOpinions.get(i)));
			}
		}
		
		for(int i=0; i<negativeOpinions.size(); i++){
			jcas = JCasFactory.createJCas();
			jcas.setDocumentText(negativeOpinions.get(i));
			jcas.setDocumentLanguage("de");
			
			opEval = createEngineDescription(OpinionEvaluator.class, OpinionEvaluator.PARAM_INPUT_FILE_POSITIVE, "src/test/resources/test/positiveWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEUTRAL, "src/test/resources/test/neutralWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEGATIVE, "src/test/resources/test/negativeWords.txt");
			lEngine = createEngine(opEval);
			lEngine.process(jcas);
			
			eval = JCasUtil.selectSingle(jcas, EvaluationTendency.class);
			if(eval.getEvalTend()==(-1))correct++;
			else{
				wrong++;
				wrongEvaluated.add(("Negative, but evaluated:"+eval.getEvalTend()+" ; "+negativeOpinions.get(i)));
			}
		}
		
		double correctPercent = correct/(wrong+correct)*100;
		double wrongPercent = wrong/(wrong+correct)*100;
		System.out.println("Wrong: "+wrong+", Correct: "+correct);
		System.out.println("Correct: "+correctPercent);
		System.out.println("Wrong: "+wrongPercent);
		for(int i=0; i<wrongEvaluated.size(); i++){
			System.out.println("Falsch evaluiert: "+wrongEvaluated.get(i));
		}
		assertEquals(wrong, 0);
	}

}
