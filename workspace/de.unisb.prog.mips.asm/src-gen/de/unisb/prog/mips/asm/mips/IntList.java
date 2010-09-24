/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IntList#getVals <em>Vals</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIntList()
 * @model
 * @generated
 */
public interface IntList extends EObject
{
  /**
   * Returns the value of the '<em><b>Vals</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Integer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Vals</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vals</em>' attribute list.
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIntList_Vals()
   * @model unique="false"
   * @generated
   */
  EList<Integer> getVals();

} // IntList
