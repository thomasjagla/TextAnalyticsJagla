package de.unidue.langtech.teaching.pp.example;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

/**
 * The baseline always identifies "EN" as the document language.
 * 
 * @author zesch
 *
 */
public class BaselineExample
    extends JCasAnnotator_ImplBase
{
	public static final String PARAM_MESSAGE = "PARAM_MESSAGE";
	@ConfigurationParameter(name = PARAM_MESSAGE, mandatory=true, defaultValue = "PARAM_MESSAGE: Hello im Default")
	protected String message;
	
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        System.out.println("Document is: " + jcas.getDocumentText());
        
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        System.out.println("CAS contains " + tokens.size() + " tokens.");
        
        
        DetectedLanguage languageAnno = detectLanguage(tokens, jcas);
        languageAnno.addToIndexes();
    }
    
    private DetectedLanguage detectLanguage(Collection<Token> tokens, JCas jcas){
    	String[] mostUsedEN = {"the","be","to","of","and","a","in","that","have","I","it","for","not","on","with"};
    	String[] mostUsedDE = {"ich","du","er","sie","es","wir","ihr","sie","Sie","der","die","das","eine","ein","dieser","und","jeder","jener","alle","ist","nicht","was"};
    	String[] mostUsedFR = {"être", "avoir", "je", "de", "ne","pas","le","la","tu","vous","il","et","à","un","qui","aller","les","en","faire","tout","on"};		
    	int en, de, fr;
    	en = de = fr = 0;
    	for(Token t: tokens){
    		for(int i=0; i<mostUsedEN.length;i++){
    			if(t.getCoveredText().equals(mostUsedEN[i]))
    				en++;
    		}
    		for(int i=0; i<mostUsedEN.length;i++){
    			if(t.getCoveredText().equals(mostUsedDE[i]))
    				de++;
    		}
    		for(int i=0; i<mostUsedEN.length;i++){
    			if(t.getCoveredText().equals(mostUsedFR[i]))
    				fr++;
    		}
    	}
    	DetectedLanguage l = new DetectedLanguage(jcas);
    	if(en >= de && en >= fr)
    		l.setLanguage("EN");
    	if(fr > en && fr > de)
    		l.setLanguage("FR");
    	if(de > en && de >= fr)
    		l.setLanguage("DE");
    	
    	
    	return l;
    }
    
    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        System.out.println(message);
    }
}