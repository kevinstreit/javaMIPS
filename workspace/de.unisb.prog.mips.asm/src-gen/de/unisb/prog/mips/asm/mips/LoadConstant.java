/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Load Constant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.LoadConstant#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.LoadConstant#getVal <em>Val</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getLoadConstant()
 * @model
 * @generated
 */
public interface LoadConstant extends EObject
{
  /**
   * Returns the value of the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rt</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rt</em>' containment reference.
   * @see #setRt(Reg)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getLoadConstant_Rt()
   * @model containment="true"
   * @generated
   */
  Reg getRt();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.LoadConstant#getRt <em>Rt</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rt</em>' containment reference.
   * @see #getRt()
   * @generated
   */
  void setRt(Reg value);

  /**
   * Returns the value of the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' attribute.
   * @see #setVal(int)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getLoadConstant_Val()
   * @model
   * @generated
   */
  int getVal();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.LoadConstant#getVal <em>Val</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' attribute.
   * @see #getVal()
   * @generated
   */
  void setVal(int value);

} // LoadConstant
