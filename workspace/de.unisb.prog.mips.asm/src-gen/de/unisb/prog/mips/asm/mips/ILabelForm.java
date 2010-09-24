/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ILabel Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.ILabelForm#getReg <em>Reg</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.ILabelForm#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getILabelForm()
 * @model
 * @generated
 */
public interface ILabelForm extends EObject
{
  /**
   * Returns the value of the '<em><b>Reg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reg</em>' containment reference.
   * @see #setReg(Reg)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getILabelForm_Reg()
   * @model containment="true"
   * @generated
   */
  Reg getReg();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.ILabelForm#getReg <em>Reg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reg</em>' containment reference.
   * @see #getReg()
   * @generated
   */
  void setReg(Reg value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' reference.
   * @see #setLabel(Label)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getILabelForm_Label()
   * @model
   * @generated
   */
  Label getLabel();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.ILabelForm#getLabel <em>Label</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' reference.
   * @see #getLabel()
   * @generated
   */
  void setLabel(Label value);

} // ILabelForm
