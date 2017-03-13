
/* First created by JCasGen Thu Nov 17 11:13:33 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/**
 * 
 * IRRELEVANTE DATEI FÜR DAS PRAXISPROJEKT
 *
 */
public class MyOwnType_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MyOwnType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.MyOwnType");
 
  /** @generated */
  final Feature casFeat_countLetterA;
  /** @generated */
  final int     casFeatCode_countLetterA;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCountLetterA(int addr) {
        if (featOkTst && casFeat_countLetterA == null)
      jcas.throwFeatMissing("countLetterA", "de.unidue.langtech.teaching.pp.type.MyOwnType");
    return ll_cas.ll_getIntValue(addr, casFeatCode_countLetterA);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCountLetterA(int addr, int v) {
        if (featOkTst && casFeat_countLetterA == null)
      jcas.throwFeatMissing("countLetterA", "de.unidue.langtech.teaching.pp.type.MyOwnType");
    ll_cas.ll_setIntValue(addr, casFeatCode_countLetterA, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MyOwnType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_countLetterA = jcas.getRequiredFeatureDE(casType, "countLetterA", "uima.cas.Integer", featOkTst);
    casFeatCode_countLetterA  = (null == casFeat_countLetterA) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_countLetterA).getCode();

  }
}



    