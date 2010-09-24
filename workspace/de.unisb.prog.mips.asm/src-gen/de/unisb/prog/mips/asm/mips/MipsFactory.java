/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.unisb.prog.mips.asm.mips.MipsPackage
 * @generated
 */
public interface MipsFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MipsFactory eINSTANCE = de.unisb.prog.mips.asm.mips.impl.MipsFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Asm</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Asm</em>'.
   * @generated
   */
  Asm createAsm();

  /**
   * Returns a new object of class '<em>Text Segment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Text Segment</em>'.
   * @generated
   */
  TextSegment createTextSegment();

  /**
   * Returns a new object of class '<em>Text Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Text Item</em>'.
   * @generated
   */
  TextItem createTextItem();

  /**
   * Returns a new object of class '<em>Instruction</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Instruction</em>'.
   * @generated
   */
  Instruction createInstruction();

  /**
   * Returns a new object of class '<em>RForm</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>RForm</em>'.
   * @generated
   */
  RForm createRForm();

  /**
   * Returns a new object of class '<em>IArith Form</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>IArith Form</em>'.
   * @generated
   */
  IArithForm createIArithForm();

  /**
   * Returns a new object of class '<em>IExp Form</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>IExp Form</em>'.
   * @generated
   */
  IExpForm createIExpForm();

  /**
   * Returns a new object of class '<em>ILabel Form</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ILabel Form</em>'.
   * @generated
   */
  ILabelForm createILabelForm();

  /**
   * Returns a new object of class '<em>IBr2 Form</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>IBr2 Form</em>'.
   * @generated
   */
  IBr2Form createIBr2Form();

  /**
   * Returns a new object of class '<em>BExp Form</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>BExp Form</em>'.
   * @generated
   */
  BExpForm createBExpForm();

  /**
   * Returns a new object of class '<em>BLabel Form</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>BLabel Form</em>'.
   * @generated
   */
  BLabelForm createBLabelForm();

  /**
   * Returns a new object of class '<em>Special Insn</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Special Insn</em>'.
   * @generated
   */
  SpecialInsn createSpecialInsn();

  /**
   * Returns a new object of class '<em>Load Constant</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Load Constant</em>'.
   * @generated
   */
  LoadConstant createLoadConstant();

  /**
   * Returns a new object of class '<em>Load Address</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Load Address</em>'.
   * @generated
   */
  LoadAddress createLoadAddress();

  /**
   * Returns a new object of class '<em>Reg</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Reg</em>'.
   * @generated
   */
  Reg createReg();

  /**
   * Returns a new object of class '<em>Data Segment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Segment</em>'.
   * @generated
   */
  DataSegment createDataSegment();

  /**
   * Returns a new object of class '<em>Data Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Item</em>'.
   * @generated
   */
  DataItem createDataItem();

  /**
   * Returns a new object of class '<em>Data Decl</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Decl</em>'.
   * @generated
   */
  DataDecl createDataDecl();

  /**
   * Returns a new object of class '<em>Label</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Label</em>'.
   * @generated
   */
  Label createLabel();

  /**
   * Returns a new object of class '<em>Align</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Align</em>'.
   * @generated
   */
  Align createAlign();

  /**
   * Returns a new object of class '<em>Space</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Space</em>'.
   * @generated
   */
  Space createSpace();

  /**
   * Returns a new object of class '<em>Word</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Word</em>'.
   * @generated
   */
  Word createWord();

  /**
   * Returns a new object of class '<em>Half</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Half</em>'.
   * @generated
   */
  Half createHalf();

  /**
   * Returns a new object of class '<em>Byte</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Byte</em>'.
   * @generated
   */
  Byte createByte();

  /**
   * Returns a new object of class '<em>Str</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Str</em>'.
   * @generated
   */
  Str createStr();

  /**
   * Returns a new object of class '<em>Int List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Int List</em>'.
   * @generated
   */
  IntList createIntList();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  MipsPackage getMipsPackage();

} //MipsFactory
