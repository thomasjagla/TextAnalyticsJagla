package de.unidue.langtech.teaching.pp.example;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class OpinionEvaluatorBaselineTest {
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
			fr = new FileReader("src/test/resources/test/positiveIndependentOpinions.txt");
			br = new BufferedReader(fr);
			line="";
			while((line = br.readLine()) != null){
				positiveOpinions.add(line);
			}
			
			fr = new FileReader("src/test/resources/test/neutralIndependentOpinions.txt");
			br = new BufferedReader(fr);
			line="";
			while((line = br.readLine()) != null){
				neutralOpinions.add(line);
			}
			
			fr = new FileReader("src/test/resources/test/negativeIndependentOpinions.txt");
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
		Random random = new Random();
		
		for(int i=0; i<positiveOpinions.size(); i++){
			jcas = JCasFactory.createJCas();
			jcas.setDocumentText(positiveOpinions.get(i));
			jcas.setDocumentLanguage("de");
			
			
			EvaluationTendency evalTend = new EvaluationTendency(jcas);
			evalTend.setEvalTend((random.nextInt(3)-1));
			evalTend.addToIndexes();
			
			if(evalTend.getEvalTend()==1)correct++;
			else{
				wrong++;
				wrongEvaluated.add(positiveOpinions.get(i));
			}
		}
		
		for(int i=0; i<neutralOpinions.size(); i++){
			jcas = JCasFactory.createJCas();
			jcas.setDocumentText(neutralOpinions.get(i));
			jcas.setDocumentLanguage("de");
			
			EvaluationTendency evalTend = new EvaluationTendency(jcas);
			evalTend.setEvalTend((random.nextInt(3)-1));
			evalTend.addToIndexes();
			
			if(evalTend.getEvalTend()==0)correct++;
			else{
				wrong++;
				wrongEvaluated.add(neutralOpinions.get(i));
			}
		}
		
		for(int i=0; i<negativeOpinions.size(); i++){
			jcas = JCasFactory.createJCas();
			jcas.setDocumentText(negativeOpinions.get(i));
			jcas.setDocumentLanguage("de");
			
			EvaluationTendency evalTend = new EvaluationTendency(jcas);
			evalTend.setEvalTend((random.nextInt(3)-1));
			evalTend.addToIndexes();
			
			if(evalTend.getEvalTend()==(-1))correct++;
			else{
				wrong++;
				wrongEvaluated.add(negativeOpinions.get(i));
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
