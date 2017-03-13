package de.unidue.langtech.teaching.pp.example;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertEquals;

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

//########################
//Datei des Praxisprojects
//########################
public class OpinionEvaluatorTest {	//Überprüft für jeweils einen positiven, negativen und neutralen Satz die Tendenz
	@Test
	public void testBaselineEvaluateOpinion() throws UIMAException {
		String text = "Das Produkt gefällt mir sehr. Es ist vielseitig einsetzbar und gefällt meiner Familie.";
		String text2 = "Das Produkt gefällt mir nicht. Es steht nur im Weg rum und vehindert die Sicht.";
		String text3 = "Das Produkt ist toll, aber es stürzt andauernd ab.";
		
		//TEXT1
		JCas jcas = JCasFactory.createJCas();
		jcas.setDocumentText(text);
		jcas.setDocumentLanguage("de");

		AnalysisEngineDescription opEval = createEngineDescription(OpinionEvaluator.class, OpinionEvaluator.PARAM_INPUT_FILE_POSITIVE, "src/test/resources/test/positiveWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEUTRAL, "src/test/resources/test/neutralWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEGATIVE, "src/test/resources/test/negativeWords.txt");
		AnalysisEngine lEngine = createEngine(opEval);
		lEngine.process(jcas);

		EvaluationTendency eval = JCasUtil.selectSingle(jcas, EvaluationTendency.class);
		System.out.println("Evaluiert: "+jcas.getDocumentText());
		System.out.println("Evaluation Satz 1: "+eval.getEvalTend()+" (-1: negative; 0:neutral; 1: positive");
		assertEquals(1 , eval.getEvalTend());
		
		//TEXT2
		jcas = JCasFactory.createJCas();
		jcas.setDocumentText(text2);
		jcas.setDocumentLanguage("de");

		lEngine.process(jcas);

		eval = JCasUtil.selectSingle(jcas, EvaluationTendency.class);
		System.out.println("Evaluiert: "+jcas.getDocumentText());
		System.out.println("Evaluation Satz 2: "+eval.getEvalTend()+" (-1: negative; 0:neutral; 1: positive");
		assertEquals(-1 , eval.getEvalTend());
		
		//TEXT3
		jcas = JCasFactory.createJCas();
		jcas.setDocumentText(text3);
		jcas.setDocumentLanguage("de");

		lEngine.process(jcas);

		eval = JCasUtil.selectSingle(jcas, EvaluationTendency.class);
		System.out.println("Evaluiert: "+jcas.getDocumentText());
		
		//Auswertung
		System.out.println("Evaluation Satz 3: "+eval.getEvalTend()+" (-1: negative; 0:neutral; 1: positive");
		assertEquals(0 , eval.getEvalTend());
		
	}

}
