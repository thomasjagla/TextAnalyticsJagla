

/* First created by JCasGen Wed Jan 11 12:12:37 CET 2017 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;

//########################
//Datei des Praxisprojects
//########################
/** 
 * Updated by JCasGen Wed Jan 11 12:12:37 CET 2017
 * XML source: C:/Users/Thomas/Desktop/Textanalysewerkzeuge Workspace/Git Repository/TextAnalyticsJagla/de.unidue.langtech.teaching.pp.Jagla/src/main/resources/desc/type/EvaluationTendency.xml
 * @generated */
public class EvaluationTendency extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(EvaluationTendency.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected EvaluationTendency() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public EvaluationTendency(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public EvaluationTendency(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public EvaluationTendency(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: EvalTend

  /** getter for EvalTend - gets Stores the result of the Opinion Evaluation. Stores Values from -1 to 1 (-1: negative; 0: neutral; 1: positive).
   * @generated
   * @return value of the feature 
   */
  public int getEvalTend() {
    if (EvaluationTendency_Type.featOkTst && ((EvaluationTendency_Type)jcasType).casFeat_EvalTend == null)
      jcasType.jcas.throwFeatMissing("EvalTend", "de.unidue.langtech.teaching.pp.type.EvaluationTendency");
    return jcasType.ll_cas.ll_getIntValue(addr, ((EvaluationTendency_Type)jcasType).casFeatCode_EvalTend);}
    
  /** setter for EvalTend - sets Stores the result of the Opinion Evaluation. Stores Values from -1 to 1 (-1: negative; 0: neutral; 1: positive). 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEvalTend(int v) {
    if (EvaluationTendency_Type.featOkTst && ((EvaluationTendency_Type)jcasType).casFeat_EvalTend == null)
      jcasType.jcas.throwFeatMissing("EvalTend", "de.unidue.langtech.teaching.pp.type.EvaluationTendency");
    jcasType.ll_cas.ll_setIntValue(addr, ((EvaluationTendency_Type)jcasType).casFeatCode_EvalTend, v);}    
  }

    