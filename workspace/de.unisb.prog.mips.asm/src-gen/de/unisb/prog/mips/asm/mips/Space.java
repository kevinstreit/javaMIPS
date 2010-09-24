/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.Space#getBytes <em>Bytes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getSpace()
 * @model
 * @generated
 */
public interface Space extends EObject
{
  /**
   * Returns the value of the '<em><b>Bytes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bytes</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bytes</em>' attribute.
   * @see #setBytes(int)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getSpace_Bytes()
   * @model
   * @generated
   */
  int getBytes();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.Space#getBytes <em>Bytes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bytes</em>' attribute.
   * @see #getBytes()
   * @generated
   */
  void setBytes(int value);

} // Space
