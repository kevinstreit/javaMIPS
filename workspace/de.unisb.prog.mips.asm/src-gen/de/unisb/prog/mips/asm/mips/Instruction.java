/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instruction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.Instruction#getOpcode <em>Opcode</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.Instruction#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getInstruction()
 * @model
 * @generated
 */
public interface Instruction extends EObject
{
  /**
   * Returns the value of the '<em><b>Opcode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Opcode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Opcode</em>' attribute.
   * @see #setOpcode(String)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getInstruction_Opcode()
   * @model
   * @generated
   */
  String getOpcode();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.Instruction#getOpcode <em>Opcode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Opcode</em>' attribute.
   * @see #getOpcode()
   * @generated
   */
  void setOpcode(String value);

  /**
   * Returns the value of the '<em><b>Form</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Form</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Form</em>' containment reference.
   * @see #setForm(EObject)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getInstruction_Form()
   * @model containment="true"
   * @generated
   */
  EObject getForm();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.Instruction#getForm <em>Form</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Form</em>' containment reference.
   * @see #getForm()
   * @generated
   */
  void setForm(EObject value);

} // Instruction
