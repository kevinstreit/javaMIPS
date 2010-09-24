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
 * A representation of the model object '<em><b>Text Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.TextSegment#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getTextSegment()
 * @model
 * @generated
 */
public interface TextSegment extends EObject
{
  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link de.unisb.prog.mips.asm.mips.TextItem}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getTextSegment_Items()
   * @model containment="true"
   * @generated
   */
  EList<TextItem> getItems();

} // TextSegment
