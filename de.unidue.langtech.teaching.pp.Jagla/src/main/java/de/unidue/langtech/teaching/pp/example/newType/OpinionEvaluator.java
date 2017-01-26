package de.unidue.langtech.teaching.pp.example.newType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.unidue.langtech.teaching.pp.type.EvaluationTendency;
import de.unidue.langtech.teaching.pp.type.MyOwnType;
import de.unidue.langtech.teaching.pp.type.MyType;

public class OpinionEvaluator extends JCasAnnotator_ImplBase {

	public static final String PARAM_INPUT_FILE_POSITIVE = "InputPositiveWords";
    @ConfigurationParameter(name = PARAM_INPUT_FILE_POSITIVE, mandatory = true)
    private File inputPositiveWords;  
    
    public static final String PARAM_INPUT_FILE_NEGATIVE = "InputNegativeWords";
    @ConfigurationParameter(name = PARAM_INPUT_FILE_NEGATIVE, mandatory = true)
    private File inputNegativeWords;
    
    public static final String PARAM_INPUT_FILE_NEUTRAL = "InputNeutralWords";
    @ConfigurationParameter(name = PARAM_INPUT_FILE_NEUTRAL, mandatory = true)
    private File inputNeutralWords;
    
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
    	super.initialize(context);
    }

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String documentText = jcas.getDocumentText();

		documentText = documentText.toLowerCase();
		documentText = documentText.replace('.', '\0');
		documentText = documentText.replace(',', '\0');
		documentText = documentText.replace('!', '\0');
		documentText = documentText.replace('?', '\0');
		documentText = documentText.replace(':', '\0');
		
		System.out.println("Original Text: "+jcas.getDocumentText());
		//System.out.println("Formatted Text: "+documentText);
		

		
		
		ArrayList<String> positiveWords = new ArrayList<String>();
		ArrayList<String> negativeWords = new ArrayList<String>();
		ArrayList<String> neutralWords = new ArrayList<String>();
		
		//READ WORD FILES
	    FileReader fileReader = null;
	    BufferedReader reader = null;
	    String currentLine="";
		try {
			fileReader = new FileReader(inputPositiveWords);
	    	reader = new BufferedReader(fileReader);
			while((currentLine=reader.readLine())!=null){
				positiveWords.add(currentLine);
			}
			
			fileReader = new FileReader(inputNeutralWords);
			reader = new BufferedReader(fileReader);
			while((currentLine=reader.readLine())!=null){
				neutralWords.add(currentLine);
			}
			
			
			fileReader = new FileReader(inputNegativeWords);
			reader = new BufferedReader(fileReader);
			while((currentLine=reader.readLine())!=null){
				negativeWords.add(currentLine);
			}
		} catch (Exception e) {e.printStackTrace();}
	    
		//CONTAINS
		int positive, neutral, negative;
		positive = neutral = negative = 0;
		
		for(String s: positiveWords){
			if(documentText.contains(s.substring(0, s.length()-1)))
				positive+=Integer.parseInt(s.substring(s.length()-1, s.length()));
		}
		for(String s: neutralWords){
			if(documentText.contains(s.substring(0, s.length()-1)))
				neutral+=Integer.parseInt(s.substring(s.length()-1, s.length()));
		}
		for(String s: negativeWords){
			if(documentText.contains(s.substring(0, s.length()-1)))
				negative+=Integer.parseInt(s.substring(s.length()-1, s.length()));
		}
		int tendency = 0;
		
		if(positive > negative)tendency=1;
		if(negative > positive)tendency=-1;
		if(tendency==1 && neutral>positive)tendency=0;
		if(tendency==-1 && neutral>negative)tendency=0;
		
		EvaluationTendency evalTend = new EvaluationTendency(jcas);
		evalTend.setEvalTend(tendency);
		switch(tendency){
		case -1: System.out.println("---NEGATIVE---"); break;
		case 0: System.out.println("---NEUTRAL---"); break;
		case 1: System.out.println("---POSITIVE---"); break;
		default: System.out.println("---UNGÜLTIGE TENDENZ---");
		}
		evalTend.addToIndexes();
	}

}
