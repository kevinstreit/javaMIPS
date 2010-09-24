/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.impl;

import de.unisb.prog.mips.asm.mips.MipsPackage;
import de.unisb.prog.mips.asm.mips.SpecialInsn;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Special Insn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.SpecialInsnImpl#getInsn <em>Insn</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecialInsnImpl extends MinimalEObjectImpl.Container implements SpecialInsn
{
  /**
   * The cached value of the '{@link #getInsn() <em>Insn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInsn()
   * @generated
   * @ordered
   */
  protected EObject insn;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SpecialInsnImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return MipsPackage.Literals.SPECIAL_INSN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getInsn()
  {
    return insn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInsn(EObject newInsn, NotificationChain msgs)
  {
    EObject oldInsn = insn;
    insn = newInsn;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.SPECIAL_INSN__INSN, oldInsn, newInsn);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInsn(EObject newInsn)
  {
    if (newInsn != insn)
    {
      NotificationChain msgs = null;
      if (insn != null)
        msgs = ((InternalEObject)insn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.SPECIAL_INSN__INSN, null, msgs);
      if (newInsn != null)
        msgs = ((InternalEObject)newInsn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.SPECIAL_INSN__INSN, null, msgs);
      msgs = basicSetInsn(newInsn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.SPECIAL_INSN__INSN, newInsn, newInsn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MipsPackage.SPECIAL_INSN__INSN:
        return basicSetInsn(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MipsPackage.SPECIAL_INSN__INSN:
        return getInsn();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MipsPackage.SPECIAL_INSN__INSN:
        setInsn((EObject)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case MipsPackage.SPECIAL_INSN__INSN:
        setInsn((EObject)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MipsPackage.SPECIAL_INSN__INSN:
        return insn != null;
    }
    return super.eIsSet(featureID);
  }

} //SpecialInsnImpl
