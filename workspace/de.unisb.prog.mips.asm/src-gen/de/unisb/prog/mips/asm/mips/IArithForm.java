/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IArith Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IArithForm#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IArithForm#getRs <em>Rs</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.IArithForm#getImm <em>Imm</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIArithForm()
 * @model
 * @generated
 */
public interface IArithForm extends EObject
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
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIArithForm_Rt()
   * @model containment="true"
   * @generated
   */
  Reg getRt();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.IArithForm#getRt <em>Rt</em>}' containment reference.
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
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIArithForm_Rs()
   * @model containment="true"
   * @generated
   */
  Reg getRs();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.IArithForm#getRs <em>Rs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rs</em>' containment reference.
   * @see #getRs()
   * @generated
   */
  void setRs(Reg value);

  /**
   * Returns the value of the '<em><b>Imm</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Imm</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imm</em>' attribute.
   * @see #setImm(int)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getIArithForm_Imm()
   * @model
   * @generated
   */
  int getImm();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.IArithForm#getImm <em>Imm</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Imm</em>' attribute.
   * @see #getImm()
   * @generated
   */
  void setImm(int value);

} // IArithForm
