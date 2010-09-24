/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.impl;

import de.unisb.prog.mips.asm.mips.IExpForm;
import de.unisb.prog.mips.asm.mips.MipsPackage;
import de.unisb.prog.mips.asm.mips.Reg;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IExp Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.IExpFormImpl#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.IExpFormImpl#getImm <em>Imm</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.IExpFormImpl#getRs <em>Rs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IExpFormImpl extends MinimalEObjectImpl.Container implements IExpForm
{
  /**
   * The cached value of the '{@link #getRt() <em>Rt</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRt()
   * @generated
   * @ordered
   */
  protected Reg rt;

  /**
   * The default value of the '{@link #getImm() <em>Imm</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImm()
   * @generated
   * @ordered
   */
  protected static final int IMM_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getImm() <em>Imm</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImm()
   * @generated
   * @ordered
   */
  protected int imm = IMM_EDEFAULT;

  /**
   * The cached value of the '{@link #getRs() <em>Rs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRs()
   * @generated
   * @ordered
   */
  protected Reg rs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IExpFormImpl()
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
    return MipsPackage.Literals.IEXP_FORM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reg getRt()
  {
    return rt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRt(Reg newRt, NotificationChain msgs)
  {
    Reg oldRt = rt;
    rt = newRt;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.IEXP_FORM__RT, oldRt, newRt);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRt(Reg newRt)
  {
    if (newRt != rt)
    {
      NotificationChain msgs = null;
      if (rt != null)
        msgs = ((InternalEObject)rt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IEXP_FORM__RT, null, msgs);
      if (newRt != null)
        msgs = ((InternalEObject)newRt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IEXP_FORM__RT, null, msgs);
      msgs = basicSetRt(newRt, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.IEXP_FORM__RT, newRt, newRt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getImm()
  {
    return imm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImm(int newImm)
  {
    int oldImm = imm;
    imm = newImm;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.IEXP_FORM__IMM, oldImm, imm));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reg getRs()
  {
    return rs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRs(Reg newRs, NotificationChain msgs)
  {
    Reg oldRs = rs;
    rs = newRs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.IEXP_FORM__RS, oldRs, newRs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRs(Reg newRs)
  {
    if (newRs != rs)
    {
      NotificationChain msgs = null;
      if (rs != null)
        msgs = ((InternalEObject)rs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IEXP_FORM__RS, null, msgs);
      if (newRs != null)
        msgs = ((InternalEObject)newRs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IEXP_FORM__RS, null, msgs);
      msgs = basicSetRs(newRs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.IEXP_FORM__RS, newRs, newRs));
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
      case MipsPackage.IEXP_FORM__RT:
        return basicSetRt(null, msgs);
      case MipsPackage.IEXP_FORM__RS:
        return basicSetRs(null, msgs);
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
      case MipsPackage.IEXP_FORM__RT:
        return getRt();
      case MipsPackage.IEXP_FORM__IMM:
        return getImm();
      case MipsPackage.IEXP_FORM__RS:
        return getRs();
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
      case MipsPackage.IEXP_FORM__RT:
        setRt((Reg)newValue);
        return;
      case MipsPackage.IEXP_FORM__IMM:
        setImm((Integer)newValue);
        return;
      case MipsPackage.IEXP_FORM__RS:
        setRs((Reg)newValue);
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
      case MipsPackage.IEXP_FORM__RT:
        setRt((Reg)null);
        return;
      case MipsPackage.IEXP_FORM__IMM:
        setImm(IMM_EDEFAULT);
        return;
      case MipsPackage.IEXP_FORM__RS:
        setRs((Reg)null);
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
      case MipsPackage.IEXP_FORM__RT:
        return rt != null;
      case MipsPackage.IEXP_FORM__IMM:
        return imm != IMM_EDEFAULT;
      case MipsPackage.IEXP_FORM__RS:
        return rs != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (imm: ");
    result.append(imm);
    result.append(')');
    return result.toString();
  }

} //IExpFormImpl
