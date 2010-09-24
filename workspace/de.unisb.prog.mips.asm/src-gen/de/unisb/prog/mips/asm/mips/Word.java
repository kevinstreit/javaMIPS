/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Word</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.Word#getVal <em>Val</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getWord()
 * @model
 * @generated
 */
public interface Word extends EObject
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' containment reference.
   * @see #setVal(IntList)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getWord_Val()
   * @model containment="true"
   * @generated
   */
  IntList getVal();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.Word#getVal <em>Val</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' containment reference.
   * @see #getVal()
   * @generated
   */
  void setVal(IntList value);

} // Word
