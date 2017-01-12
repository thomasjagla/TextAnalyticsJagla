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
		System.out.println("Formatted Text: "+documentText);
		

		
		
		ArrayList<String> positiveWords = null;
	    FileReader fileReader = null;
		try {fileReader = new FileReader(inputPositiveWords);} catch (Exception e) {e.printStackTrace();}
	    BufferedReader reader = new BufferedReader(fileReader);
	    
	    String currentLine="";
	    try {
			while((currentLine=reader.readLine())!=null){
				positiveWords.add(currentLine);
			}
		} catch (IOException e) {e.printStackTrace();}
	    
		int positive, neutral, negative;
		positive = neutral = negative = 0;
			
		EvaluationTendency evalTend = new EvaluationTendency(jcas);
		evalTend.setEvalTend(0);
		evalTend.addToIndexes();
	}

}
