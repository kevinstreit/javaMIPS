/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.impl;

import de.unisb.prog.mips.asm.mips.MipsPackage;
import de.unisb.prog.mips.asm.mips.RForm;
import de.unisb.prog.mips.asm.mips.Reg;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RForm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.RFormImpl#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.RFormImpl#getRs <em>Rs</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.RFormImpl#getRd <em>Rd</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.RFormImpl#getShamt <em>Shamt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RFormImpl extends MinimalEObjectImpl.Container implements RForm
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
   * The cached value of the '{@link #getRs() <em>Rs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRs()
   * @generated
   * @ordered
   */
  protected Reg rs;

  /**
   * The cached value of the '{@link #getRd() <em>Rd</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRd()
   * @generated
   * @ordered
   */
  protected Reg rd;

  /**
   * The default value of the '{@link #getShamt() <em>Shamt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShamt()
   * @generated
   * @ordered
   */
  protected static final int SHAMT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getShamt() <em>Shamt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShamt()
   * @generated
   * @ordered
   */
  protected int shamt = SHAMT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RFormImpl()
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
    return MipsPackage.Literals.RFORM;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__RT, oldRt, newRt);
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
        msgs = ((InternalEObject)rt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.RFORM__RT, null, msgs);
      if (newRt != null)
        msgs = ((InternalEObject)newRt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.RFORM__RT, null, msgs);
      msgs = basicSetRt(newRt, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__RT, newRt, newRt));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__RS, oldRs, newRs);
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
        msgs = ((InternalEObject)rs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.RFORM__RS, null, msgs);
      if (newRs != null)
        msgs = ((InternalEObject)newRs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.RFORM__RS, null, msgs);
      msgs = basicSetRs(newRs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__RS, newRs, newRs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reg getRd()
  {
    return rd;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRd(Reg newRd, NotificationChain msgs)
  {
    Reg oldRd = rd;
    rd = newRd;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__RD, oldRd, newRd);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRd(Reg newRd)
  {
    if (newRd != rd)
    {
      NotificationChain msgs = null;
      if (rd != null)
        msgs = ((InternalEObject)rd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.RFORM__RD, null, msgs);
      if (newRd != null)
        msgs = ((InternalEObject)newRd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.RFORM__RD, null, msgs);
      msgs = basicSetRd(newRd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__RD, newRd, newRd));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getShamt()
  {
    return shamt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShamt(int newShamt)
  {
    int oldShamt = shamt;
    shamt = newShamt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.RFORM__SHAMT, oldShamt, shamt));
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
      case MipsPackage.RFORM__RT:
        return basicSetRt(null, msgs);
      case MipsPackage.RFORM__RS:
        return basicSetRs(null, msgs);
      case MipsPackage.RFORM__RD:
        return basicSetRd(null, msgs);
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
      case MipsPackage.RFORM__RT:
        return getRt();
      case MipsPackage.RFORM__RS:
        return getRs();
      case MipsPackage.RFORM__RD:
        return getRd();
      case MipsPackage.RFORM__SHAMT:
        return getShamt();
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
      case MipsPackage.RFORM__RT:
        setRt((Reg)newValue);
        return;
      case MipsPackage.RFORM__RS:
        setRs((Reg)newValue);
        return;
      case MipsPackage.RFORM__RD:
        setRd((Reg)newValue);
        return;
      case MipsPackage.RFORM__SHAMT:
        setShamt((Integer)newValue);
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
      case MipsPackage.RFORM__RT:
        setRt((Reg)null);
        return;
      case MipsPackage.RFORM__RS:
        setRs((Reg)null);
        return;
      case MipsPackage.RFORM__RD:
        setRd((Reg)null);
        return;
      case MipsPackage.RFORM__SHAMT:
        setShamt(SHAMT_EDEFAULT);
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
      case MipsPackage.RFORM__RT:
        return rt != null;
      case MipsPackage.RFORM__RS:
        return rs != null;
      case MipsPackage.RFORM__RD:
        return rd != null;
      case MipsPackage.RFORM__SHAMT:
        return shamt != SHAMT_EDEFAULT;
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
    result.append(" (shamt: ");
    result.append(shamt);
    result.append(')');
    return result.toString();
  }

} //RFormImpl
