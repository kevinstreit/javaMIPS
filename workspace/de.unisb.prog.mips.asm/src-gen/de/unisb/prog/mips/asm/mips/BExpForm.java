/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BExp Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.BExpForm#getAddr <em>Addr</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getBExpForm()
 * @model
 * @generated
 */
public interface BExpForm extends EObject
{
  /**
   * Returns the value of the '<em><b>Addr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Addr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Addr</em>' attribute.
   * @see #setAddr(int)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getBExpForm_Addr()
   * @model
   * @generated
   */
  int getAddr();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.BExpForm#getAddr <em>Addr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Addr</em>' attribute.
   * @see #getAddr()
   * @generated
   */
  void setAddr(int value);

} // BExpForm
