package de.unidue.langtech.teaching.pp.example.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.unidue.langtech.teaching.pp.example.OpinionMiningReader;
import de.unidue.langtech.teaching.pp.example.newType.OpinionMiningRatingEvaluator;
import de.unidue.langtech.teaching.pp.example.newType.OpinionMiningWriter;
/**
 * 
 * IRRELEVANTE DATEI FÜR DAS PRAXISPROJEKT
 *
 */
public class OpinionMiningPipeline
{

    public static void main(String[] args)
        throws Exception
    {
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        OpinionMiningReader.class,
                        OpinionMiningReader.PARAM_INPUT_FILE, "src/test/resources/test/inputOpinions.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(OpinionMiningWriter.class),
                AnalysisEngineFactory.createEngineDescription(OpinionMiningRatingEvaluator.class)
        );
    }
}