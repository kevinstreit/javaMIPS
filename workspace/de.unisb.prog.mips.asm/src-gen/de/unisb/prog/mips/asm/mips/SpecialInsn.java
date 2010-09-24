/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Special Insn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.SpecialInsn#getInsn <em>Insn</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getSpecialInsn()
 * @model
 * @generated
 */
public interface SpecialInsn extends EObject
{
  /**
   * Returns the value of the '<em><b>Insn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Insn</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Insn</em>' containment reference.
   * @see #setInsn(EObject)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getSpecialInsn_Insn()
   * @model containment="true"
   * @generated
   */
  EObject getInsn();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.SpecialInsn#getInsn <em>Insn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Insn</em>' containment reference.
   * @see #getInsn()
   * @generated
   */
  void setInsn(EObject value);

} // SpecialInsn
