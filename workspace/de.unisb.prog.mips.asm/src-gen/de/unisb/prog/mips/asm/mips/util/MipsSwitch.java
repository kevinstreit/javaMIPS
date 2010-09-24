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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.unisb.prog.mips.asm.mips.MipsPackage
 * @generated
 */
public class MipsSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MipsPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MipsSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = MipsPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case MipsPackage.ASM:
      {
        Asm asm = (Asm)theEObject;
        T result = caseAsm(asm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.TEXT_SEGMENT:
      {
        TextSegment textSegment = (TextSegment)theEObject;
        T result = caseTextSegment(textSegment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.TEXT_ITEM:
      {
        TextItem textItem = (TextItem)theEObject;
        T result = caseTextItem(textItem);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.INSTRUCTION:
      {
        Instruction instruction = (Instruction)theEObject;
        T result = caseInstruction(instruction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.RFORM:
      {
        RForm rForm = (RForm)theEObject;
        T result = caseRForm(rForm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.IARITH_FORM:
      {
        IArithForm iArithForm = (IArithForm)theEObject;
        T result = caseIArithForm(iArithForm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.IEXP_FORM:
      {
        IExpForm iExpForm = (IExpForm)theEObject;
        T result = caseIExpForm(iExpForm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.ILABEL_FORM:
      {
        ILabelForm iLabelForm = (ILabelForm)theEObject;
        T result = caseILabelForm(iLabelForm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.IBR2_FORM:
      {
        IBr2Form iBr2Form = (IBr2Form)theEObject;
        T result = caseIBr2Form(iBr2Form);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.BEXP_FORM:
      {
        BExpForm bExpForm = (BExpForm)theEObject;
        T result = caseBExpForm(bExpForm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.BLABEL_FORM:
      {
        BLabelForm bLabelForm = (BLabelForm)theEObject;
        T result = caseBLabelForm(bLabelForm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.SPECIAL_INSN:
      {
        SpecialInsn specialInsn = (SpecialInsn)theEObject;
        T result = caseSpecialInsn(specialInsn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.LOAD_CONSTANT:
      {
        LoadConstant loadConstant = (LoadConstant)theEObject;
        T result = caseLoadConstant(loadConstant);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.LOAD_ADDRESS:
      {
        LoadAddress loadAddress = (LoadAddress)theEObject;
        T result = caseLoadAddress(loadAddress);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.REG:
      {
        Reg reg = (Reg)theEObject;
        T result = caseReg(reg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.DATA_SEGMENT:
      {
        DataSegment dataSegment = (DataSegment)theEObject;
        T result = caseDataSegment(dataSegment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.DATA_ITEM:
      {
        DataItem dataItem = (DataItem)theEObject;
        T result = caseDataItem(dataItem);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.DATA_DECL:
      {
        DataDecl dataDecl = (DataDecl)theEObject;
        T result = caseDataDecl(dataDecl);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.LABEL:
      {
        Label label = (Label)theEObject;
        T result = caseLabel(label);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.ALIGN:
      {
        Align align = (Align)theEObject;
        T result = caseAlign(align);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.SPACE:
      {
        Space space = (Space)theEObject;
        T result = caseSpace(space);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.WORD:
      {
        Word word = (Word)theEObject;
        T result = caseWord(word);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.HALF:
      {
        Half half = (Half)theEObject;
        T result = caseHalf(half);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.BYTE:
      {
        de.unisb.prog.mips.asm.mips.Byte byte_ = (de.unisb.prog.mips.asm.mips.Byte)theEObject;
        T result = caseByte(byte_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.STR:
      {
        Str str = (Str)theEObject;
        T result = caseStr(str);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MipsPackage.INT_LIST:
      {
        IntList intList = (IntList)theEObject;
        T result = caseIntList(intList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Asm</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Asm</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAsm(Asm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Text Segment</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Segment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTextSegment(TextSegment object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Text Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTextItem(TextItem object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Instruction</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Instruction</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInstruction(Instruction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>RForm</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>RForm</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRForm(RForm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IArith Form</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IArith Form</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIArithForm(IArithForm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IExp Form</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IExp Form</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIExpForm(IExpForm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ILabel Form</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ILabel Form</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseILabelForm(ILabelForm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IBr2 Form</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IBr2 Form</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIBr2Form(IBr2Form object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>BExp Form</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>BExp Form</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBExpForm(BExpForm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>BLabel Form</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>BLabel Form</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBLabelForm(BLabelForm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Special Insn</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Special Insn</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSpecialInsn(SpecialInsn object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Load Constant</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Load Constant</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLoadConstant(LoadConstant object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Load Address</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Load Address</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLoadAddress(LoadAddress object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reg</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReg(Reg object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Segment</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Segment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataSegment(DataSegment object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataItem(DataItem object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Decl</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Decl</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataDecl(DataDecl object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Label</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLabel(Label object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Align</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Align</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlign(Align object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Space</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Space</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSpace(Space object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Word</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Word</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWord(Word object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Half</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Half</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHalf(Half object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Byte</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Byte</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseByte(de.unisb.prog.mips.asm.mips.Byte object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Str</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Str</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStr(Str object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntList(IntList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object)
  {
    return null;
  }

} //MipsSwitch
