package de.unidue.langtech.teaching.pp.example.newType;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unidue.langtech.teaching.pp.type.MyType;
import de.unidue.langtech.teaching.pp.type.MyOwnType;
/**
 * 
 * IRRELEVANTE DATEI FÜR DAS PRAXISPROJEKT
 *
 */
public class OpinionMiningWriter
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        String documentText = jcas.getDocumentText();

        System.out.println(documentText);
    }

}