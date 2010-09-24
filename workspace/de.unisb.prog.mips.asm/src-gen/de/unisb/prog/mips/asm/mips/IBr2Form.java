/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IBr2 Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IBr2Form#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IBr2Form#getRs <em>Rs</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IBr2Form#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIBr2Form()
 * @model
 * @generated
 */
public interface IBr2Form extends EObject
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
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIBr2Form_Rt()
   * @model containment="true"
   * @generated
   */
  Reg getRt();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.IBr2Form#getRt <em>Rt</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rt</em>' containment reference.
   * @see #getRt()
   * @generated
   */
  void setRt(Reg value);

  /**
   * Returns the value of the '<em><b>Rs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rs</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rs</em>' containment reference.
   * @see #setRs(Reg)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIBr2Form_Rs()
   * @model containment="true"
   * @generated
   */
  Reg getRs();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.IBr2Form#getRs <em>Rs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rs</em>' containment reference.
   * @see #getRs()
   * @generated
   */
  void setRs(Reg value);

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
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIBr2Form_Label()
   * @model
   * @generated
   */
  Label getLabel();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.IBr2Form#getLabel <em>Label</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' reference.
   * @see #getLabel()
   * @generated
   */
  void setLabel(Label value);

} // IBr2Form
