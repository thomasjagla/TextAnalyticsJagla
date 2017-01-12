package de.unidue.langtech.teaching.pp.example.newType;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.example.BaselineExample;
import de.unidue.langtech.teaching.pp.example.OpinionReader;
import de.unidue.langtech.teaching.pp.example.ReaderExample;

public class OpinionEvaluatorPipeline {

	public static void main(String[] args) throws Exception {
		SimplePipeline.runPipeline(CollectionReaderFactory.createReader(
				OpinionReader.class, OpinionReader.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"), 
				AnalysisEngineFactory.createEngineDescription(OpinionEvaluator.class, OpinionEvaluator.PARAM_INPUT_FILE_POSITIVE, "src/test/resources/test/positiveWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEUTRAL, "src/test/resources/test/neutralWords.txt", OpinionEvaluator.PARAM_INPUT_FILE_NEGATIVE, "src/test/resources/test/negativeWords.txt"));
	}
}
