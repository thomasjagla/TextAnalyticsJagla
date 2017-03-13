
/* First created by JCasGen Wed Jan 11 12:12:37 CET 2017 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

//########################
//Datei des Praxisprojects
//########################

/** 
 * Updated by JCasGen Wed Jan 11 12:12:37 CET 2017
 * @generated */
public class EvaluationTendency_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = EvaluationTendency.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.EvaluationTendency");
 
  /** @generated */
  final Feature casFeat_EvalTend;
  /** @generated */
  final int     casFeatCode_EvalTend;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEvalTend(int addr) {
        if (featOkTst && casFeat_EvalTend == null)
      jcas.throwFeatMissing("EvalTend", "de.unidue.langtech.teaching.pp.type.EvaluationTendency");
    return ll_cas.ll_getIntValue(addr, casFeatCode_EvalTend);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEvalTend(int addr, int v) {
        if (featOkTst && casFeat_EvalTend == null)
      jcas.throwFeatMissing("EvalTend", "de.unidue.langtech.teaching.pp.type.EvaluationTendency");
    ll_cas.ll_setIntValue(addr, casFeatCode_EvalTend, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public EvaluationTendency_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_EvalTend = jcas.getRequiredFeatureDE(casType, "EvalTend", "uima.cas.Integer", featOkTst);
    casFeatCode_EvalTend  = (null == casFeat_EvalTend) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_EvalTend).getCode();

  }
}



    