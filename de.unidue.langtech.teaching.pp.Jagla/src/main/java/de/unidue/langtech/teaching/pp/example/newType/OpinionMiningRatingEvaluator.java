package de.unidue.langtech.teaching.pp.example.newType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.MyType;

/**
 * 
 * IRRELEVANTE DATEI FÜR DAS PRAXISPROJEKT
 *
 */

public class OpinionMiningRatingEvaluator
    extends JCasAnnotator_ImplBase
{
	public static final String PARAM_POSITIVE_WORDS_INPUT_FILE = "positiveWordsInputFile";
    @ConfigurationParameter(name = PARAM_POSITIVE_WORDS_INPUT_FILE, mandatory = true)
    private String positiveWordsInputFile;
    
    public static final String PARAM_NEGATIVE_WORDS_INPUT_FILE = "negativeWordsInputFile";
    @ConfigurationParameter(name = PARAM_NEGATIVE_WORDS_INPUT_FILE, mandatory = true)
    private String negativeWordsInputFile;
    
    FileInputStream fisPositive;
    FileInputStream fisNegative;
    
	final boolean TESTSPLITTOSENTENCES = false;
	final boolean TESTPRINTWORDTOSENTENCE = true;
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
    	initFileInputStreams();
    	
    	String documentText = jcas.getDocumentText();
        String[] sentences = splitToSentences(documentText);
        
        if(TESTSPLITTOSENTENCES){
        	for(int i=0; i<sentences.length; i++){
        		System.out.println("Split to Sentences:"+sentences[i]);
        	}
        }
        
        evaluate(sentences, jcas);
    }

    public void evaluate(String[] sentences, JCas jcas){
    	int negative, neutral, positive;
		negative = neutral = positive = 0;
		
		
    	for(int i=0; i<sentences.length; i++){
    		String[] words = sentences[i].split(" ");
    		for(int k=0; k< words.length; k++)
    		{
    			char letztesZeichen = words[k].charAt(words[k].length()-1);
 
    			if(letztesZeichen=='.' || letztesZeichen=='?' || letztesZeichen=='!')
    				words[k]=words[k].substring(0, words[k].length()-1); //Entfernen von Satzzeichen
    			words[k]=words[k].toLowerCase();//Umwandlung Kleinbuchstaben
    			
    			if(TESTPRINTWORDTOSENTENCE)
        			System.out.println("Sentence:"+i+" Word:"+words[k]);
    		}
    		
    		for(int k=0; k< words.length; k++)
    		{
    			
    		}
    	}
    	
    	
    }
    
    
    public String[] splitToSentences(String documentText){
    	char[] parts = documentText.toCharArray();
    	ArrayList<String> sentences = new ArrayList<String>();
    	int lastSentence=0;
    	int c=0;
    	for(int current = 0; current < parts.length; current++){
    		if(parts[current]=='.' || parts[current]=='!' || parts[current]=='?'){
    			String currentSentence = "";
    			while(lastSentence<=current){
    				currentSentence+=parts[lastSentence];
    				lastSentence++;
    			}
    			sentences.add(currentSentence);
    		}
    		c=current;
    	}
    	
    	if(lastSentence<parts.length){
    		String currentSentence = "";
			while(lastSentence<=c){
				currentSentence+=parts[lastSentence];
				lastSentence++;
			}
			sentences.add(currentSentence);
    	}
    	
    	String[] sArray = new String[sentences.size()];
    	for(int i=0; i<sentences.size(); i++){
    		sArray[i]=sentences.get(i);
    	}
    	for(int i=0; i<sArray.length; i++){
    		if(sArray[i].charAt(0)==' '){
    			sArray[i] = sArray[i].trim();
    		}
    		
    	}
    	return sArray;
    }
    
    public void initFileInputStreams(){
    	if(fisPositive==null || fisNegative==null){
    		try {
				fisPositive = new FileInputStream(positiveWordsInputFile);
				fisNegative = new FileInputStream(negativeWordsInputFile);
				
				System.out.println(fisPositive.read());
			} catch (Exception e) {e.printStackTrace();}
    	}
    }
}

