package de.unidue.langtech.teaching.pp.example.newType;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unidue.langtech.teaching.pp.type.MyType;
import de.unidue.langtech.teaching.pp.type.MyOwnType;

public class LetterAnnotator
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        String documentText = jcas.getDocumentText();

        int countLetterE = 0;
        for (char c : documentText.toCharArray()) {
            if (c == 'e' || c == 'E') {
                countLetterE++;
            }
        }
        
        //Set this integer value to the property of the new type 'MyType'
        MyType myType = new MyType(jcas);
        myType.setCountLetterE(countLetterE);
        myType.addToIndexes();
        
        MyOwnType myOwnType = new MyOwnType(jcas);
        myOwnType.setCountLetterE(countLetterE);
        myOwnType.addToIndexes();

        System.out.println("Counted: "+myOwnType.getCountLetterE() +" E's.");
    }

}