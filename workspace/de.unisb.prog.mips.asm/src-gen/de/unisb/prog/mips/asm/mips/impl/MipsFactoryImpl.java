/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.impl;

import de.unisb.prog.mips.asm.mips.Align;
import de.unisb.prog.mips.asm.mips.Asm;
import de.unisb.prog.mips.asm.mips.BExpForm;
import de.unisb.prog.mips.asm.mips.BLabelForm;
import de.unisb.prog.mips.asm.mips.DataDecl;
import de.unisb.prog.mips.asm.mips.DataItem;
import de.unisb.prog.mips.asm.mips.DataSegment;
import de.unisb.prog.mips.asm.mips.Half;
import de.unisb.prog.mips.asm.mips.IArithForm;
import de.unisb.prog.mips.asm.mips.IBr2Form;
import de.unisb.prog.mips.asm.mips.IExpForm;
import de.unisb.prog.mips.asm.mips.ILabelForm;
import de.unisb.prog.mips.asm.mips.Instruction;
import de.unisb.prog.mips.asm.mips.IntList;
import de.unisb.prog.mips.asm.mips.Label;
import de.unisb.prog.mips.asm.mips.LoadAddress;
import de.unisb.prog.mips.asm.mips.LoadConstant;
import de.unisb.prog.mips.asm.mips.MipsFactory;
import de.unisb.prog.mips.asm.mips.MipsPackage;
import de.unisb.prog.mips.asm.mips.RForm;
import de.unisb.prog.mips.asm.mips.Reg;
import de.unisb.prog.mips.asm.mips.Space;
import de.unisb.prog.mips.asm.mips.SpecialInsn;
import de.unisb.prog.mips.asm.mips.Str;
import de.unisb.prog.mips.asm.mips.TextItem;
import de.unisb.prog.mips.asm.mips.TextSegment;
import de.unisb.prog.mips.asm.mips.Word;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MipsFactoryImpl extends EFactoryImpl implements MipsFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MipsFactory init()
  {
    try
    {
      MipsFactory theMipsFactory = (MipsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.unisb.de/prog/mips/asm/Mips"); 
      if (theMipsFactory != null)
      {
        return theMipsFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MipsFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MipsFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case MipsPackage.ASM: return createAsm();
      case MipsPackage.TEXT_SEGMENT: return createTextSegment();
      case MipsPackage.TEXT_ITEM: return createTextItem();
      case MipsPackage.INSTRUCTION: return createInstruction();
      case MipsPackage.RFORM: return createRForm();
      case MipsPackage.IARITH_FORM: return createIArithForm();
      case MipsPackage.IEXP_FORM: return createIExpForm();
      case MipsPackage.ILABEL_FORM: return createILabelForm();
      case MipsPackage.IBR2_FORM: return createIBr2Form();
      case MipsPackage.BEXP_FORM: return createBExpForm();
      case MipsPackage.BLABEL_FORM: return createBLabelForm();
      case MipsPackage.SPECIAL_INSN: return createSpecialInsn();
      case MipsPackage.LOAD_CONSTANT: return createLoadConstant();
      case MipsPackage.LOAD_ADDRESS: return createLoadAddress();
      case MipsPackage.REG: return createReg();
      case MipsPackage.DATA_SEGMENT: return createDataSegment();
      case MipsPackage.DATA_ITEM: return createDataItem();
      case MipsPackage.DATA_DECL: return createDataDecl();
      case MipsPackage.LABEL: return createLabel();
      case MipsPackage.ALIGN: return createAlign();
      case MipsPackage.SPACE: return createSpace();
      case MipsPackage.WORD: return createWord();
      case MipsPackage.HALF: return createHalf();
      case MipsPackage.BYTE: return createByte();
      case MipsPackage.STR: return createStr();
      case MipsPackage.INT_LIST: return createIntList();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Asm createAsm()
  {
    AsmImpl asm = new AsmImpl();
    return asm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TextSegment createTextSegment()
  {
    TextSegmentImpl textSegment = new TextSegmentImpl();
    return textSegment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TextItem createTextItem()
  {
    TextItemImpl textItem = new TextItemImpl();
    return textItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Instruction createInstruction()
  {
    InstructionImpl instruction = new InstructionImpl();
    return instruction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RForm createRForm()
  {
    RFormImpl rForm = new RFormImpl();
    return rForm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IArithForm createIArithForm()
  {
    IArithFormImpl iArithForm = new IArithFormImpl();
    return iArithForm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IExpForm createIExpForm()
  {
    IExpFormImpl iExpForm = new IExpFormImpl();
    return iExpForm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ILabelForm createILabelForm()
  {
    ILabelFormImpl iLabelForm = new ILabelFormImpl();
    return iLabelForm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IBr2Form createIBr2Form()
  {
    IBr2FormImpl iBr2Form = new IBr2FormImpl();
    return iBr2Form;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BExpForm createBExpForm()
  {
    BExpFormImpl bExpForm = new BExpFormImpl();
    return bExpForm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BLabelForm createBLabelForm()
  {
    BLabelFormImpl bLabelForm = new BLabelFormImpl();
    return bLabelForm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SpecialInsn createSpecialInsn()
  {
    SpecialInsnImpl specialInsn = new SpecialInsnImpl();
    return specialInsn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LoadConstant createLoadConstant()
  {
    LoadConstantImpl loadConstant = new LoadConstantImpl();
    return loadConstant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LoadAddress createLoadAddress()
  {
    LoadAddressImpl loadAddress = new LoadAddressImpl();
    return loadAddress;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reg createReg()
  {
    RegImpl reg = new RegImpl();
    return reg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataSegment createDataSegment()
  {
    DataSegmentImpl dataSegment = new DataSegmentImpl();
    return dataSegment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataItem createDataItem()
  {
    DataItemImpl dataItem = new DataItemImpl();
    return dataItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataDecl createDataDecl()
  {
    DataDeclImpl dataDecl = new DataDeclImpl();
    return dataDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Label createLabel()
  {
    LabelImpl label = new LabelImpl();
    return label;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Align createAlign()
  {
    AlignImpl align = new AlignImpl();
    return align;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Space createSpace()
  {
    SpaceImpl space = new SpaceImpl();
    return space;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Word createWord()
  {
    WordImpl word = new WordImpl();
    return word;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Half createHalf()
  {
    HalfImpl half = new HalfImpl();
    return half;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public de.unisb.prog.mips.asm.mips.Byte createByte()
  {
    ByteImpl byte_ = new ByteImpl();
    return byte_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Str createStr()
  {
    StrImpl str = new StrImpl();
    return str;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntList createIntList()
  {
    IntListImpl intList = new IntListImpl();
    return intList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MipsPackage getMipsPackage()
  {
    return (MipsPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MipsPackage getPackage()
  {
    return MipsPackage.eINSTANCE;
  }

} //MipsFactoryImpl
