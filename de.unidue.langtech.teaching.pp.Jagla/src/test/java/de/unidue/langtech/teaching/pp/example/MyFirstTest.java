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
import de.unidue.langtech.teaching.pp.example.newType.LetterAnnotator;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.MyOwnType;

/**
 * 
 * IRRELEVANTE DATEI FÜR DAS PRAXISPROJEKT
 *
 */
public class MyFirstTest {
	@Test
	public void testBaselineAnnotationEnglish() throws UIMAException {
		String text = "Peter Piper picked a peck of pickled peppers?";

		// We don't have a pipeline here,
		// thus we create an empty document by hand,
		// the following utility-method call helps us
		JCas jcas = JCasFactory.createJCas();
		// We set the text to our empty document
		jcas.setDocumentText(text);
		jcas.setDocumentLanguage("en");

		// We just instantiate our annotator by hand and
		// call process() by-hand (in a pipeline, the framework does that for
		// us)

		// Do you remember, our baseline need tokens - we have to do the
		// segmentation, too
		AnalysisEngineDescription lAnnotator = createEngineDescription(LetterAnnotator.class);
		AnalysisEngine lEngine = createEngine(lAnnotator);
		lEngine.process(jcas);

		MyOwnType a = JCasUtil.selectSingle(jcas, MyOwnType.class);
		System.out.println("gezählt:"+a.getCountLetterA());

		assertEquals(1 , a.getCountLetterA());
	}

}
