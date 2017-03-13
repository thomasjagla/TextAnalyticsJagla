package de.unidue.langtech.teaching.pp.example.pipeline;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.example.BaselineExample;
import de.unidue.langtech.teaching.pp.example.EvaluatorExample;
import de.unidue.langtech.teaching.pp.example.ReaderExample;
import de.unidue.langtech.teaching.pp.example.newType.LetterAnnotator;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReader;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReaderSolution;
/**
 * 
 * IRRELEVANTE DATEI FÜR DAS PRAXISPROJEKT
 *
 */
public class BasicPipeline
{

    public static void main(String[] args)
        throws Exception
    {
//        SimplePipeline.runPipeline(
//                CollectionReaderFactory.createReader(
//                        ReaderExample.class,
//                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"
//                ),
//                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
//                AnalysisEngineFactory.createEngineDescription(BaselineExample.class, BaselineExample.PARAM_MESSAGE, "PARAM_MESSAGE: Ich bin nicht Default"),
//                AnalysisEngineFactory.createEngineDescription(LetterAnnotator.class),
//                AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class),
//                AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class, SnowballStemmer.PARAM_LANGUAGE , "en"),
//                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
//        );
        
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        NewReader.class,
                        NewReader.PARAM_INPUT_FILE, "src/test/resources/test/inputNewFormat.txt"
                ),
//                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
//                AnalysisEngineFactory.createEngineDescription(BaselineExample.class, BaselineExample.PARAM_MESSAGE, "PARAM_MESSAGE: Ich bin nicht Default"),
//                AnalysisEngineFactory.createEngineDescription(LetterAnnotator.class),
//                AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class),
//                AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class, SnowballStemmer.PARAM_LANGUAGE , "en"),
                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
        );
    }
}
