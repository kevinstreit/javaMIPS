/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RForm</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.RForm#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.RForm#getRs <em>Rs</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.RForm#getRd <em>Rd</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.RForm#getShamt <em>Shamt</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.unisb.prog.mips.asm.mips.MipsPackage#getRForm()
 * @model
 * @generated
 */
public interface RForm extends EObject
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
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getRForm_Rt()
   * @model containment="true"
   * @generated
   */
  Reg getRt();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.RForm#getRt <em>Rt</em>}' containment reference.
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
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getRForm_Rs()
   * @model containment="true"
   * @generated
   */
  Reg getRs();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.RForm#getRs <em>Rs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rs</em>' containment reference.
   * @see #getRs()
   * @generated
   */
  void setRs(Reg value);

  /**
   * Returns the value of the '<em><b>Rd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rd</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rd</em>' containment reference.
   * @see #setRd(Reg)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getRForm_Rd()
   * @model containment="true"
   * @generated
   */
  Reg getRd();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.RForm#getRd <em>Rd</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rd</em>' containment reference.
   * @see #getRd()
   * @generated
   */
  void setRd(Reg value);

  /**
   * Returns the value of the '<em><b>Shamt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Shamt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Shamt</em>' attribute.
   * @see #setShamt(int)
   * @see de.unisb.prog.mips.asm.mips.MipsPackage#getRForm_Shamt()
   * @model
   * @generated
   */
  int getShamt();

  /**
   * Sets the value of the '{@link de.unisb.prog.mips.asm.mips.RForm#getShamt <em>Shamt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Shamt</em>' attribute.
   * @see #getShamt()
   * @generated
   */
  void setShamt(int value);

} // RForm
