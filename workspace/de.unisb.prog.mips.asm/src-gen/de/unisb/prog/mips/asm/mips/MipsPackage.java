/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.unisb.prog.mips.asm.mips.MipsFactory
 * @model kind="package"
 * @generated
 */
public interface MipsPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "mips";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.unisb.de/prog/mips/asm/Mips";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "mips";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MipsPackage eINSTANCE = de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl.init();

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.AsmImpl <em>Asm</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.AsmImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getAsm()
   * @generated
   */
  int ASM = 0;

  /**
   * The feature id for the '<em><b>Elem</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASM__ELEM = 0;

  /**
   * The number of structural features of the '<em>Asm</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.TextSegmentImpl <em>Text Segment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.TextSegmentImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getTextSegment()
   * @generated
   */
  int TEXT_SEGMENT = 1;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEXT_SEGMENT__ITEMS = 0;

  /**
   * The number of structural features of the '<em>Text Segment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEXT_SEGMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.TextItemImpl <em>Text Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.TextItemImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getTextItem()
   * @generated
   */
  int TEXT_ITEM = 2;

  /**
   * The feature id for the '<em><b>Label</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEXT_ITEM__LABEL = 0;

  /**
   * The feature id for the '<em><b>Item</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEXT_ITEM__ITEM = 1;

  /**
   * The number of structural features of the '<em>Text Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEXT_ITEM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.InstructionImpl <em>Instruction</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.InstructionImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getInstruction()
   * @generated
   */
  int INSTRUCTION = 3;

  /**
   * The feature id for the '<em><b>Opcode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTRUCTION__OPCODE = 0;

  /**
   * The feature id for the '<em><b>Form</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTRUCTION__FORM = 1;

  /**
   * The number of structural features of the '<em>Instruction</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTRUCTION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.RFormImpl <em>RForm</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.RFormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getRForm()
   * @generated
   */
  int RFORM = 4;

  /**
   * The feature id for the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RFORM__RT = 0;

  /**
   * The feature id for the '<em><b>Rs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RFORM__RS = 1;

  /**
   * The feature id for the '<em><b>Rd</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RFORM__RD = 2;

  /**
   * The feature id for the '<em><b>Shamt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RFORM__SHAMT = 3;

  /**
   * The number of structural features of the '<em>RForm</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RFORM_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.IArithFormImpl <em>IArith Form</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.IArithFormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIArithForm()
   * @generated
   */
  int IARITH_FORM = 5;

  /**
   * The feature id for the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IARITH_FORM__RT = 0;

  /**
   * The feature id for the '<em><b>Rs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IARITH_FORM__RS = 1;

  /**
   * The feature id for the '<em><b>Imm</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IARITH_FORM__IMM = 2;

  /**
   * The number of structural features of the '<em>IArith Form</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IARITH_FORM_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.IExpFormImpl <em>IExp Form</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.IExpFormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIExpForm()
   * @generated
   */
  int IEXP_FORM = 6;

  /**
   * The feature id for the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEXP_FORM__RT = 0;

  /**
   * The feature id for the '<em><b>Imm</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEXP_FORM__IMM = 1;

  /**
   * The feature id for the '<em><b>Rs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEXP_FORM__RS = 2;

  /**
   * The number of structural features of the '<em>IExp Form</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEXP_FORM_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.ILabelFormImpl <em>ILabel Form</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.ILabelFormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getILabelForm()
   * @generated
   */
  int ILABEL_FORM = 7;

  /**
   * The feature id for the '<em><b>Reg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ILABEL_FORM__REG = 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ILABEL_FORM__LABEL = 1;

  /**
   * The number of structural features of the '<em>ILabel Form</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ILABEL_FORM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl <em>IBr2 Form</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIBr2Form()
   * @generated
   */
  int IBR2_FORM = 8;

  /**
   * The feature id for the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IBR2_FORM__RT = 0;

  /**
   * The feature id for the '<em><b>Rs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IBR2_FORM__RS = 1;

  /**
   * The feature id for the '<em><b>Label</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IBR2_FORM__LABEL = 2;

  /**
   * The number of structural features of the '<em>IBr2 Form</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IBR2_FORM_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.BExpFormImpl <em>BExp Form</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.BExpFormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getBExpForm()
   * @generated
   */
  int BEXP_FORM = 9;

  /**
   * The feature id for the '<em><b>Addr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEXP_FORM__ADDR = 0;

  /**
   * The number of structural features of the '<em>BExp Form</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEXP_FORM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.BLabelFormImpl <em>BLabel Form</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.BLabelFormImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getBLabelForm()
   * @generated
   */
  int BLABEL_FORM = 10;

  /**
   * The feature id for the '<em><b>Label</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLABEL_FORM__LABEL = 0;

  /**
   * The number of structural features of the '<em>BLabel Form</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLABEL_FORM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.SpecialInsnImpl <em>Special Insn</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.SpecialInsnImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getSpecialInsn()
   * @generated
   */
  int SPECIAL_INSN = 11;

  /**
   * The feature id for the '<em><b>Insn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPECIAL_INSN__INSN = 0;

  /**
   * The number of structural features of the '<em>Special Insn</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPECIAL_INSN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.LoadConstantImpl <em>Load Constant</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.LoadConstantImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getLoadConstant()
   * @generated
   */
  int LOAD_CONSTANT = 12;

  /**
   * The feature id for the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_CONSTANT__RT = 0;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_CONSTANT__VAL = 1;

  /**
   * The number of structural features of the '<em>Load Constant</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_CONSTANT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.LoadAddressImpl <em>Load Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.LoadAddressImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getLoadAddress()
   * @generated
   */
  int LOAD_ADDRESS = 13;

  /**
   * The feature id for the '<em><b>Rt</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_ADDRESS__RT = 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_ADDRESS__LABEL = 1;

  /**
   * The number of structural features of the '<em>Load Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOAD_ADDRESS_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.RegImpl <em>Reg</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.RegImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getReg()
   * @generated
   */
  int REG = 14;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REG__NUM = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REG__NAME = 1;

  /**
   * The number of structural features of the '<em>Reg</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REG_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.DataSegmentImpl <em>Data Segment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.DataSegmentImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getDataSegment()
   * @generated
   */
  int DATA_SEGMENT = 15;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_SEGMENT__ITEMS = 0;

  /**
   * The number of structural features of the '<em>Data Segment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_SEGMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.DataItemImpl <em>Data Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.DataItemImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getDataItem()
   * @generated
   */
  int DATA_ITEM = 16;

  /**
   * The feature id for the '<em><b>Label</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITEM__LABEL = 0;

  /**
   * The feature id for the '<em><b>Data</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITEM__DATA = 1;

  /**
   * The number of structural features of the '<em>Data Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITEM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.DataDeclImpl <em>Data Decl</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.DataDeclImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getDataDecl()
   * @generated
   */
  int DATA_DECL = 17;

  /**
   * The feature id for the '<em><b>Item</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_DECL__ITEM = 0;

  /**
   * The number of structural features of the '<em>Data Decl</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_DECL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.LabelImpl <em>Label</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.LabelImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getLabel()
   * @generated
   */
  int LABEL = 18;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL__NAME = 0;

  /**
   * The number of structural features of the '<em>Label</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.AlignImpl <em>Align</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.AlignImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getAlign()
   * @generated
   */
  int ALIGN = 19;

  /**
   * The feature id for the '<em><b>Align</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALIGN__ALIGN = 0;

  /**
   * The number of structural features of the '<em>Align</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALIGN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.SpaceImpl <em>Space</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.SpaceImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getSpace()
   * @generated
   */
  int SPACE = 20;

  /**
   * The feature id for the '<em><b>Bytes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPACE__BYTES = 0;

  /**
   * The number of structural features of the '<em>Space</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPACE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.WordImpl <em>Word</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.WordImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getWord()
   * @generated
   */
  int WORD = 21;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORD__VAL = 0;

  /**
   * The number of structural features of the '<em>Word</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORD_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.HalfImpl <em>Half</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.HalfImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getHalf()
   * @generated
   */
  int HALF = 22;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HALF__VAL = 0;

  /**
   * The number of structural features of the '<em>Half</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HALF_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.ByteImpl <em>Byte</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.ByteImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getByte()
   * @generated
   */
  int BYTE = 23;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BYTE__VAL = 0;

  /**
   * The number of structural features of the '<em>Byte</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BYTE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.StrImpl <em>Str</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.StrImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getStr()
   * @generated
   */
  int STR = 24;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STR__VAL = 0;

  /**
   * The number of structural features of the '<em>Str</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.unisb.prog.mips.asm.mips.impl.IntListImpl <em>Int List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.unisb.prog.mips.asm.mips.impl.IntListImpl
   * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIntList()
   * @generated
   */
  int INT_LIST = 25;

  /**
   * The feature id for the '<em><b>Vals</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LIST__VALS = 0;

  /**
   * The number of structural features of the '<em>Int List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LIST_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Asm <em>Asm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Asm</em>'.
   * @see de.unisb.prog.mips.asm.mips.Asm
   * @generated
   */
  EClass getAsm();

  /**
   * Returns the meta object for the containment reference list '{@link de.unisb.prog.mips.asm.mips.Asm#getElem <em>Elem</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elem</em>'.
   * @see de.unisb.prog.mips.asm.mips.Asm#getElem()
   * @see #getAsm()
   * @generated
   */
  EReference getAsm_Elem();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.TextSegment <em>Text Segment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Text Segment</em>'.
   * @see de.unisb.prog.mips.asm.mips.TextSegment
   * @generated
   */
  EClass getTextSegment();

  /**
   * Returns the meta object for the containment reference list '{@link de.unisb.prog.mips.asm.mips.TextSegment#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see de.unisb.prog.mips.asm.mips.TextSegment#getItems()
   * @see #getTextSegment()
   * @generated
   */
  EReference getTextSegment_Items();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.TextItem <em>Text Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Text Item</em>'.
   * @see de.unisb.prog.mips.asm.mips.TextItem
   * @generated
   */
  EClass getTextItem();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.TextItem#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.TextItem#getLabel()
   * @see #getTextItem()
   * @generated
   */
  EReference getTextItem_Label();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.TextItem#getItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Item</em>'.
   * @see de.unisb.prog.mips.asm.mips.TextItem#getItem()
   * @see #getTextItem()
   * @generated
   */
  EReference getTextItem_Item();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Instruction <em>Instruction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Instruction</em>'.
   * @see de.unisb.prog.mips.asm.mips.Instruction
   * @generated
   */
  EClass getInstruction();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Instruction#getOpcode <em>Opcode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Opcode</em>'.
   * @see de.unisb.prog.mips.asm.mips.Instruction#getOpcode()
   * @see #getInstruction()
   * @generated
   */
  EAttribute getInstruction_Opcode();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.Instruction#getForm <em>Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.Instruction#getForm()
   * @see #getInstruction()
   * @generated
   */
  EReference getInstruction_Form();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.RForm <em>RForm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>RForm</em>'.
   * @see de.unisb.prog.mips.asm.mips.RForm
   * @generated
   */
  EClass getRForm();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.RForm#getRt <em>Rt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rt</em>'.
   * @see de.unisb.prog.mips.asm.mips.RForm#getRt()
   * @see #getRForm()
   * @generated
   */
  EReference getRForm_Rt();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.RForm#getRs <em>Rs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rs</em>'.
   * @see de.unisb.prog.mips.asm.mips.RForm#getRs()
   * @see #getRForm()
   * @generated
   */
  EReference getRForm_Rs();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.RForm#getRd <em>Rd</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rd</em>'.
   * @see de.unisb.prog.mips.asm.mips.RForm#getRd()
   * @see #getRForm()
   * @generated
   */
  EReference getRForm_Rd();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.RForm#getShamt <em>Shamt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Shamt</em>'.
   * @see de.unisb.prog.mips.asm.mips.RForm#getShamt()
   * @see #getRForm()
   * @generated
   */
  EAttribute getRForm_Shamt();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.IArithForm <em>IArith Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IArith Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.IArithForm
   * @generated
   */
  EClass getIArithForm();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.IArithForm#getRt <em>Rt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rt</em>'.
   * @see de.unisb.prog.mips.asm.mips.IArithForm#getRt()
   * @see #getIArithForm()
   * @generated
   */
  EReference getIArithForm_Rt();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.IArithForm#getRs <em>Rs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rs</em>'.
   * @see de.unisb.prog.mips.asm.mips.IArithForm#getRs()
   * @see #getIArithForm()
   * @generated
   */
  EReference getIArithForm_Rs();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.IArithForm#getImm <em>Imm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Imm</em>'.
   * @see de.unisb.prog.mips.asm.mips.IArithForm#getImm()
   * @see #getIArithForm()
   * @generated
   */
  EAttribute getIArithForm_Imm();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.IExpForm <em>IExp Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IExp Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.IExpForm
   * @generated
   */
  EClass getIExpForm();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.IExpForm#getRt <em>Rt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rt</em>'.
   * @see de.unisb.prog.mips.asm.mips.IExpForm#getRt()
   * @see #getIExpForm()
   * @generated
   */
  EReference getIExpForm_Rt();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.IExpForm#getImm <em>Imm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Imm</em>'.
   * @see de.unisb.prog.mips.asm.mips.IExpForm#getImm()
   * @see #getIExpForm()
   * @generated
   */
  EAttribute getIExpForm_Imm();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.IExpForm#getRs <em>Rs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rs</em>'.
   * @see de.unisb.prog.mips.asm.mips.IExpForm#getRs()
   * @see #getIExpForm()
   * @generated
   */
  EReference getIExpForm_Rs();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.ILabelForm <em>ILabel Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ILabel Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.ILabelForm
   * @generated
   */
  EClass getILabelForm();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.ILabelForm#getReg <em>Reg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Reg</em>'.
   * @see de.unisb.prog.mips.asm.mips.ILabelForm#getReg()
   * @see #getILabelForm()
   * @generated
   */
  EReference getILabelForm_Reg();

  /**
   * Returns the meta object for the reference '{@link de.unisb.prog.mips.asm.mips.ILabelForm#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.ILabelForm#getLabel()
   * @see #getILabelForm()
   * @generated
   */
  EReference getILabelForm_Label();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.IBr2Form <em>IBr2 Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IBr2 Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.IBr2Form
   * @generated
   */
  EClass getIBr2Form();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.IBr2Form#getRt <em>Rt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rt</em>'.
   * @see de.unisb.prog.mips.asm.mips.IBr2Form#getRt()
   * @see #getIBr2Form()
   * @generated
   */
  EReference getIBr2Form_Rt();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.IBr2Form#getRs <em>Rs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rs</em>'.
   * @see de.unisb.prog.mips.asm.mips.IBr2Form#getRs()
   * @see #getIBr2Form()
   * @generated
   */
  EReference getIBr2Form_Rs();

  /**
   * Returns the meta object for the reference '{@link de.unisb.prog.mips.asm.mips.IBr2Form#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.IBr2Form#getLabel()
   * @see #getIBr2Form()
   * @generated
   */
  EReference getIBr2Form_Label();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.BExpForm <em>BExp Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>BExp Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.BExpForm
   * @generated
   */
  EClass getBExpForm();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.BExpForm#getAddr <em>Addr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Addr</em>'.
   * @see de.unisb.prog.mips.asm.mips.BExpForm#getAddr()
   * @see #getBExpForm()
   * @generated
   */
  EAttribute getBExpForm_Addr();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.BLabelForm <em>BLabel Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>BLabel Form</em>'.
   * @see de.unisb.prog.mips.asm.mips.BLabelForm
   * @generated
   */
  EClass getBLabelForm();

  /**
   * Returns the meta object for the reference '{@link de.unisb.prog.mips.asm.mips.BLabelForm#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.BLabelForm#getLabel()
   * @see #getBLabelForm()
   * @generated
   */
  EReference getBLabelForm_Label();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.SpecialInsn <em>Special Insn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Special Insn</em>'.
   * @see de.unisb.prog.mips.asm.mips.SpecialInsn
   * @generated
   */
  EClass getSpecialInsn();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.SpecialInsn#getInsn <em>Insn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Insn</em>'.
   * @see de.unisb.prog.mips.asm.mips.SpecialInsn#getInsn()
   * @see #getSpecialInsn()
   * @generated
   */
  EReference getSpecialInsn_Insn();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.LoadConstant <em>Load Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Load Constant</em>'.
   * @see de.unisb.prog.mips.asm.mips.LoadConstant
   * @generated
   */
  EClass getLoadConstant();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.LoadConstant#getRt <em>Rt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rt</em>'.
   * @see de.unisb.prog.mips.asm.mips.LoadConstant#getRt()
   * @see #getLoadConstant()
   * @generated
   */
  EReference getLoadConstant_Rt();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.LoadConstant#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see de.unisb.prog.mips.asm.mips.LoadConstant#getVal()
   * @see #getLoadConstant()
   * @generated
   */
  EAttribute getLoadConstant_Val();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.LoadAddress <em>Load Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Load Address</em>'.
   * @see de.unisb.prog.mips.asm.mips.LoadAddress
   * @generated
   */
  EClass getLoadAddress();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.LoadAddress#getRt <em>Rt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rt</em>'.
   * @see de.unisb.prog.mips.asm.mips.LoadAddress#getRt()
   * @see #getLoadAddress()
   * @generated
   */
  EReference getLoadAddress_Rt();

  /**
   * Returns the meta object for the reference '{@link de.unisb.prog.mips.asm.mips.LoadAddress#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.LoadAddress#getLabel()
   * @see #getLoadAddress()
   * @generated
   */
  EReference getLoadAddress_Label();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Reg <em>Reg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reg</em>'.
   * @see de.unisb.prog.mips.asm.mips.Reg
   * @generated
   */
  EClass getReg();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Reg#getNum <em>Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Num</em>'.
   * @see de.unisb.prog.mips.asm.mips.Reg#getNum()
   * @see #getReg()
   * @generated
   */
  EAttribute getReg_Num();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Reg#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.unisb.prog.mips.asm.mips.Reg#getName()
   * @see #getReg()
   * @generated
   */
  EAttribute getReg_Name();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.DataSegment <em>Data Segment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Segment</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataSegment
   * @generated
   */
  EClass getDataSegment();

  /**
   * Returns the meta object for the containment reference list '{@link de.unisb.prog.mips.asm.mips.DataSegment#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataSegment#getItems()
   * @see #getDataSegment()
   * @generated
   */
  EReference getDataSegment_Items();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.DataItem <em>Data Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Item</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataItem
   * @generated
   */
  EClass getDataItem();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.DataItem#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataItem#getLabel()
   * @see #getDataItem()
   * @generated
   */
  EReference getDataItem_Label();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.DataItem#getData <em>Data</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataItem#getData()
   * @see #getDataItem()
   * @generated
   */
  EReference getDataItem_Data();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.DataDecl <em>Data Decl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Decl</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataDecl
   * @generated
   */
  EClass getDataDecl();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.DataDecl#getItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Item</em>'.
   * @see de.unisb.prog.mips.asm.mips.DataDecl#getItem()
   * @see #getDataDecl()
   * @generated
   */
  EReference getDataDecl_Item();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Label <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Label</em>'.
   * @see de.unisb.prog.mips.asm.mips.Label
   * @generated
   */
  EClass getLabel();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Label#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.unisb.prog.mips.asm.mips.Label#getName()
   * @see #getLabel()
   * @generated
   */
  EAttribute getLabel_Name();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Align <em>Align</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Align</em>'.
   * @see de.unisb.prog.mips.asm.mips.Align
   * @generated
   */
  EClass getAlign();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Align#getAlign <em>Align</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Align</em>'.
   * @see de.unisb.prog.mips.asm.mips.Align#getAlign()
   * @see #getAlign()
   * @generated
   */
  EAttribute getAlign_Align();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Space <em>Space</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Space</em>'.
   * @see de.unisb.prog.mips.asm.mips.Space
   * @generated
   */
  EClass getSpace();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Space#getBytes <em>Bytes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bytes</em>'.
   * @see de.unisb.prog.mips.asm.mips.Space#getBytes()
   * @see #getSpace()
   * @generated
   */
  EAttribute getSpace_Bytes();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Word <em>Word</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Word</em>'.
   * @see de.unisb.prog.mips.asm.mips.Word
   * @generated
   */
  EClass getWord();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.Word#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see de.unisb.prog.mips.asm.mips.Word#getVal()
   * @see #getWord()
   * @generated
   */
  EReference getWord_Val();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Half <em>Half</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Half</em>'.
   * @see de.unisb.prog.mips.asm.mips.Half
   * @generated
   */
  EClass getHalf();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.Half#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see de.unisb.prog.mips.asm.mips.Half#getVal()
   * @see #getHalf()
   * @generated
   */
  EReference getHalf_Val();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Byte <em>Byte</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Byte</em>'.
   * @see de.unisb.prog.mips.asm.mips.Byte
   * @generated
   */
  EClass getByte();

  /**
   * Returns the meta object for the containment reference '{@link de.unisb.prog.mips.asm.mips.Byte#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see de.unisb.prog.mips.asm.mips.Byte#getVal()
   * @see #getByte()
   * @generated
   */
  EReference getByte_Val();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.Str <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Str</em>'.
   * @see de.unisb.prog.mips.asm.mips.Str
   * @generated
   */
  EClass getStr();

  /**
   * Returns the meta object for the attribute '{@link de.unisb.prog.mips.asm.mips.Str#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see de.unisb.prog.mips.asm.mips.Str#getVal()
   * @see #getStr()
   * @generated
   */
  EAttribute getStr_Val();

  /**
   * Returns the meta object for class '{@link de.unisb.prog.mips.asm.mips.IntList <em>Int List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Int List</em>'.
   * @see de.unisb.prog.mips.asm.mips.IntList
   * @generated
   */
  EClass getIntList();

  /**
   * Returns the meta object for the attribute list '{@link de.unisb.prog.mips.asm.mips.IntList#getVals <em>Vals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Vals</em>'.
   * @see de.unisb.prog.mips.asm.mips.IntList#getVals()
   * @see #getIntList()
   * @generated
   */
  EAttribute getIntList_Vals();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MipsFactory getMipsFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.AsmImpl <em>Asm</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.AsmImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getAsm()
     * @generated
     */
    EClass ASM = eINSTANCE.getAsm();

    /**
     * The meta object literal for the '<em><b>Elem</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASM__ELEM = eINSTANCE.getAsm_Elem();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.TextSegmentImpl <em>Text Segment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.TextSegmentImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getTextSegment()
     * @generated
     */
    EClass TEXT_SEGMENT = eINSTANCE.getTextSegment();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEXT_SEGMENT__ITEMS = eINSTANCE.getTextSegment_Items();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.TextItemImpl <em>Text Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.TextItemImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getTextItem()
     * @generated
     */
    EClass TEXT_ITEM = eINSTANCE.getTextItem();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEXT_ITEM__LABEL = eINSTANCE.getTextItem_Label();

    /**
     * The meta object literal for the '<em><b>Item</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEXT_ITEM__ITEM = eINSTANCE.getTextItem_Item();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.InstructionImpl <em>Instruction</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.InstructionImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getInstruction()
     * @generated
     */
    EClass INSTRUCTION = eINSTANCE.getInstruction();

    /**
     * The meta object literal for the '<em><b>Opcode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSTRUCTION__OPCODE = eINSTANCE.getInstruction_Opcode();

    /**
     * The meta object literal for the '<em><b>Form</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INSTRUCTION__FORM = eINSTANCE.getInstruction_Form();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.RFormImpl <em>RForm</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.RFormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getRForm()
     * @generated
     */
    EClass RFORM = eINSTANCE.getRForm();

    /**
     * The meta object literal for the '<em><b>Rt</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RFORM__RT = eINSTANCE.getRForm_Rt();

    /**
     * The meta object literal for the '<em><b>Rs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RFORM__RS = eINSTANCE.getRForm_Rs();

    /**
     * The meta object literal for the '<em><b>Rd</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RFORM__RD = eINSTANCE.getRForm_Rd();

    /**
     * The meta object literal for the '<em><b>Shamt</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RFORM__SHAMT = eINSTANCE.getRForm_Shamt();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.IArithFormImpl <em>IArith Form</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.IArithFormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIArithForm()
     * @generated
     */
    EClass IARITH_FORM = eINSTANCE.getIArithForm();

    /**
     * The meta object literal for the '<em><b>Rt</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IARITH_FORM__RT = eINSTANCE.getIArithForm_Rt();

    /**
     * The meta object literal for the '<em><b>Rs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IARITH_FORM__RS = eINSTANCE.getIArithForm_Rs();

    /**
     * The meta object literal for the '<em><b>Imm</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IARITH_FORM__IMM = eINSTANCE.getIArithForm_Imm();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.IExpFormImpl <em>IExp Form</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.IExpFormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIExpForm()
     * @generated
     */
    EClass IEXP_FORM = eINSTANCE.getIExpForm();

    /**
     * The meta object literal for the '<em><b>Rt</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IEXP_FORM__RT = eINSTANCE.getIExpForm_Rt();

    /**
     * The meta object literal for the '<em><b>Imm</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IEXP_FORM__IMM = eINSTANCE.getIExpForm_Imm();

    /**
     * The meta object literal for the '<em><b>Rs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IEXP_FORM__RS = eINSTANCE.getIExpForm_Rs();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.ILabelFormImpl <em>ILabel Form</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.ILabelFormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getILabelForm()
     * @generated
     */
    EClass ILABEL_FORM = eINSTANCE.getILabelForm();

    /**
     * The meta object literal for the '<em><b>Reg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ILABEL_FORM__REG = eINSTANCE.getILabelForm_Reg();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ILABEL_FORM__LABEL = eINSTANCE.getILabelForm_Label();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl <em>IBr2 Form</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIBr2Form()
     * @generated
     */
    EClass IBR2_FORM = eINSTANCE.getIBr2Form();

    /**
     * The meta object literal for the '<em><b>Rt</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IBR2_FORM__RT = eINSTANCE.getIBr2Form_Rt();

    /**
     * The meta object literal for the '<em><b>Rs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IBR2_FORM__RS = eINSTANCE.getIBr2Form_Rs();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IBR2_FORM__LABEL = eINSTANCE.getIBr2Form_Label();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.BExpFormImpl <em>BExp Form</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.BExpFormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getBExpForm()
     * @generated
     */
    EClass BEXP_FORM = eINSTANCE.getBExpForm();

    /**
     * The meta object literal for the '<em><b>Addr</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BEXP_FORM__ADDR = eINSTANCE.getBExpForm_Addr();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.BLabelFormImpl <em>BLabel Form</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.BLabelFormImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getBLabelForm()
     * @generated
     */
    EClass BLABEL_FORM = eINSTANCE.getBLabelForm();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLABEL_FORM__LABEL = eINSTANCE.getBLabelForm_Label();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.SpecialInsnImpl <em>Special Insn</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.SpecialInsnImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getSpecialInsn()
     * @generated
     */
    EClass SPECIAL_INSN = eINSTANCE.getSpecialInsn();

    /**
     * The meta object literal for the '<em><b>Insn</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SPECIAL_INSN__INSN = eINSTANCE.getSpecialInsn_Insn();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.LoadConstantImpl <em>Load Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.LoadConstantImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getLoadConstant()
     * @generated
     */
    EClass LOAD_CONSTANT = eINSTANCE.getLoadConstant();

    /**
     * The meta object literal for the '<em><b>Rt</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOAD_CONSTANT__RT = eINSTANCE.getLoadConstant_Rt();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOAD_CONSTANT__VAL = eINSTANCE.getLoadConstant_Val();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.LoadAddressImpl <em>Load Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.LoadAddressImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getLoadAddress()
     * @generated
     */
    EClass LOAD_ADDRESS = eINSTANCE.getLoadAddress();

    /**
     * The meta object literal for the '<em><b>Rt</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOAD_ADDRESS__RT = eINSTANCE.getLoadAddress_Rt();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOAD_ADDRESS__LABEL = eINSTANCE.getLoadAddress_Label();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.RegImpl <em>Reg</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.RegImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getReg()
     * @generated
     */
    EClass REG = eINSTANCE.getReg();

    /**
     * The meta object literal for the '<em><b>Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REG__NUM = eINSTANCE.getReg_Num();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REG__NAME = eINSTANCE.getReg_Name();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.DataSegmentImpl <em>Data Segment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.DataSegmentImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getDataSegment()
     * @generated
     */
    EClass DATA_SEGMENT = eINSTANCE.getDataSegment();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_SEGMENT__ITEMS = eINSTANCE.getDataSegment_Items();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.DataItemImpl <em>Data Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.DataItemImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getDataItem()
     * @generated
     */
    EClass DATA_ITEM = eINSTANCE.getDataItem();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_ITEM__LABEL = eINSTANCE.getDataItem_Label();

    /**
     * The meta object literal for the '<em><b>Data</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_ITEM__DATA = eINSTANCE.getDataItem_Data();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.DataDeclImpl <em>Data Decl</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.DataDeclImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getDataDecl()
     * @generated
     */
    EClass DATA_DECL = eINSTANCE.getDataDecl();

    /**
     * The meta object literal for the '<em><b>Item</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_DECL__ITEM = eINSTANCE.getDataDecl_Item();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.LabelImpl <em>Label</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.LabelImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getLabel()
     * @generated
     */
    EClass LABEL = eINSTANCE.getLabel();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LABEL__NAME = eINSTANCE.getLabel_Name();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.AlignImpl <em>Align</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.AlignImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getAlign()
     * @generated
     */
    EClass ALIGN = eINSTANCE.getAlign();

    /**
     * The meta object literal for the '<em><b>Align</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ALIGN__ALIGN = eINSTANCE.getAlign_Align();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.SpaceImpl <em>Space</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.SpaceImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getSpace()
     * @generated
     */
    EClass SPACE = eINSTANCE.getSpace();

    /**
     * The meta object literal for the '<em><b>Bytes</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SPACE__BYTES = eINSTANCE.getSpace_Bytes();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.WordImpl <em>Word</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.WordImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getWord()
     * @generated
     */
    EClass WORD = eINSTANCE.getWord();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WORD__VAL = eINSTANCE.getWord_Val();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.HalfImpl <em>Half</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.HalfImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getHalf()
     * @generated
     */
    EClass HALF = eINSTANCE.getHalf();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference HALF__VAL = eINSTANCE.getHalf_Val();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.ByteImpl <em>Byte</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.ByteImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getByte()
     * @generated
     */
    EClass BYTE = eINSTANCE.getByte();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BYTE__VAL = eINSTANCE.getByte_Val();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.StrImpl <em>Str</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.StrImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getStr()
     * @generated
     */
    EClass STR = eINSTANCE.getStr();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STR__VAL = eINSTANCE.getStr_Val();

    /**
     * The meta object literal for the '{@link de.unisb.prog.mips.asm.mips.impl.IntListImpl <em>Int List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.unisb.prog.mips.asm.mips.impl.IntListImpl
     * @see de.unisb.prog.mips.asm.mips.impl.MipsPackageImpl#getIntList()
     * @generated
     */
    EClass INT_LIST = eINSTANCE.getIntList();

    /**
     * The meta object literal for the '<em><b>Vals</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INT_LIST__VALS = eINSTANCE.getIntList_Vals();

  }

} //MipsPackage
