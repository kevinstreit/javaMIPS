/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.util;

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
import de.unisb.prog.mips.asm.mips.MipsPackage;
import de.unisb.prog.mips.asm.mips.RForm;
import de.unisb.prog.mips.asm.mips.Reg;
import de.unisb.prog.mips.asm.mips.Space;
import de.unisb.prog.mips.asm.mips.SpecialInsn;
import de.unisb.prog.mips.asm.mips.Str;
import de.unisb.prog.mips.asm.mips.TextItem;
import de.unisb.prog.mips.asm.mips.TextSegment;
import de.unisb.prog.mips.asm.mips.Word;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.unisb.prog.mips.asm.mips.MipsPackage
 * @generated
 */
public class MipsAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MipsPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MipsAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = MipsPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MipsSwitch<Adapter> modelSwitch =
    new MipsSwitch<Adapter>()
    {
      @Override
      public Adapter caseAsm(Asm object)
      {
        return createAsmAdapter();
      }
      @Override
      public Adapter caseTextSegment(TextSegment object)
      {
        return createTextSegmentAdapter();
      }
      @Override
      public Adapter caseTextItem(TextItem object)
      {
        return createTextItemAdapter();
      }
      @Override
      public Adapter caseInstruction(Instruction object)
      {
        return createInstructionAdapter();
      }
      @Override
      public Adapter caseRForm(RForm object)
      {
        return createRFormAdapter();
      }
      @Override
      public Adapter caseIArithForm(IArithForm object)
      {
        return createIArithFormAdapter();
      }
      @Override
      public Adapter caseIExpForm(IExpForm object)
      {
        return createIExpFormAdapter();
      }
      @Override
      public Adapter caseILabelForm(ILabelForm object)
      {
        return createILabelFormAdapter();
      }
      @Override
      public Adapter caseIBr2Form(IBr2Form object)
      {
        return createIBr2FormAdapter();
      }
      @Override
      public Adapter caseBExpForm(BExpForm object)
      {
        return createBExpFormAdapter();
      }
      @Override
      public Adapter caseBLabelForm(BLabelForm object)
      {
        return createBLabelFormAdapter();
      }
      @Override
      public Adapter caseSpecialInsn(SpecialInsn object)
      {
        return createSpecialInsnAdapter();
      }
      @Override
      public Adapter caseLoadConstant(LoadConstant object)
      {
        return createLoadConstantAdapter();
      }
      @Override
      public Adapter caseLoadAddress(LoadAddress object)
      {
        return createLoadAddressAdapter();
      }
      @Override
      public Adapter caseReg(Reg object)
      {
        return createRegAdapter();
      }
      @Override
      public Adapter caseDataSegment(DataSegment object)
      {
        return createDataSegmentAdapter();
      }
      @Override
      public Adapter caseDataItem(DataItem object)
      {
        return createDataItemAdapter();
      }
      @Override
      public Adapter caseDataDecl(DataDecl object)
      {
        return createDataDeclAdapter();
      }
      @Override
      public Adapter caseLabel(Label object)
      {
        return createLabelAdapter();
      }
      @Override
      public Adapter caseAlign(Align object)
      {
        return createAlignAdapter();
      }
      @Override
      public Adapter caseSpace(Space object)
      {
        return createSpaceAdapter();
      }
      @Override
      public Adapter caseWord(Word object)
      {
        return createWordAdapter();
      }
      @Override
      public Adapter caseHalf(Half object)
      {
        return createHalfAdapter();
      }
      @Override
      public Adapter caseByte(de.unisb.prog.mips.asm.mips.Byte object)
      {
        return createByteAdapter();
      }
      @Override
      public Adapter caseStr(Str object)
      {
        return createStrAdapter();
      }
      @Override
      public Adapter caseIntList(IntList object)
      {
        return createIntListAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Asm <em>Asm</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Asm
   * @generated
   */
  public Adapter createAsmAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.TextSegment <em>Text Segment</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.TextSegment
   * @generated
   */
  public Adapter createTextSegmentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.TextItem <em>Text Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.TextItem
   * @generated
   */
  public Adapter createTextItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Instruction <em>Instruction</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Instruction
   * @generated
   */
  public Adapter createInstructionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.RForm <em>RForm</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.RForm
   * @generated
   */
  public Adapter createRFormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.IArithForm <em>IArith Form</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.IArithForm
   * @generated
   */
  public Adapter createIArithFormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.IExpForm <em>IExp Form</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.IExpForm
   * @generated
   */
  public Adapter createIExpFormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.ILabelForm <em>ILabel Form</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.ILabelForm
   * @generated
   */
  public Adapter createILabelFormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.IBr2Form <em>IBr2 Form</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.IBr2Form
   * @generated
   */
  public Adapter createIBr2FormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.BExpForm <em>BExp Form</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.BExpForm
   * @generated
   */
  public Adapter createBExpFormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.BLabelForm <em>BLabel Form</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.BLabelForm
   * @generated
   */
  public Adapter createBLabelFormAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.SpecialInsn <em>Special Insn</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.SpecialInsn
   * @generated
   */
  public Adapter createSpecialInsnAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.LoadConstant <em>Load Constant</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.LoadConstant
   * @generated
   */
  public Adapter createLoadConstantAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.LoadAddress <em>Load Address</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.LoadAddress
   * @generated
   */
  public Adapter createLoadAddressAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Reg <em>Reg</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Reg
   * @generated
   */
  public Adapter createRegAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.DataSegment <em>Data Segment</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.DataSegment
   * @generated
   */
  public Adapter createDataSegmentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.DataItem <em>Data Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.DataItem
   * @generated
   */
  public Adapter createDataItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.DataDecl <em>Data Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.DataDecl
   * @generated
   */
  public Adapter createDataDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Label <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Label
   * @generated
   */
  public Adapter createLabelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Align <em>Align</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Align
   * @generated
   */
  public Adapter createAlignAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Space <em>Space</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Space
   * @generated
   */
  public Adapter createSpaceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Word <em>Word</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Word
   * @generated
   */
  public Adapter createWordAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Half <em>Half</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Half
   * @generated
   */
  public Adapter createHalfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Byte <em>Byte</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Byte
   * @generated
   */
  public Adapter createByteAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.Str <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.Str
   * @generated
   */
  public Adapter createStrAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.unisb.prog.mips.asm.mips.IntList <em>Int List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.unisb.prog.mips.asm.mips.IntList
   * @generated
   */
  public Adapter createIntListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //MipsAdapterFactory
