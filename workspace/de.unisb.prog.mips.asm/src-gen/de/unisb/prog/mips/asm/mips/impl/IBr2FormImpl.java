/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.impl;

import de.unisb.prog.mips.asm.mips.IBr2Form;
import de.unisb.prog.mips.asm.mips.Label;
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
 * An implementation of the model object '<em><b>IBr2 Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl#getRs <em>Rs</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.IBr2FormImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IBr2FormImpl extends MinimalEObjectImpl.Container implements IBr2Form
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
   * The cached value of the '{@link #getLabel() <em>Label</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected Label label;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IBr2FormImpl()
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
    return MipsPackage.Literals.IBR2_FORM;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.IBR2_FORM__RT, oldRt, newRt);
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
        msgs = ((InternalEObject)rt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IBR2_FORM__RT, null, msgs);
      if (newRt != null)
        msgs = ((InternalEObject)newRt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IBR2_FORM__RT, null, msgs);
      msgs = basicSetRt(newRt, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.IBR2_FORM__RT, newRt, newRt));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.IBR2_FORM__RS, oldRs, newRs);
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
        msgs = ((InternalEObject)rs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IBR2_FORM__RS, null, msgs);
      if (newRs != null)
        msgs = ((InternalEObject)newRs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.IBR2_FORM__RS, null, msgs);
      msgs = basicSetRs(newRs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.IBR2_FORM__RS, newRs, newRs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Label getLabel()
  {
    if (label != null && label.eIsProxy())
    {
      InternalEObject oldLabel = (InternalEObject)label;
      label = (Label)eResolveProxy(oldLabel);
      if (label != oldLabel)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MipsPackage.IBR2_FORM__LABEL, oldLabel, label));
      }
    }
    return label;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Label basicGetLabel()
  {
    return label;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLabel(Label newLabel)
  {
    Label oldLabel = label;
    label = newLabel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.IBR2_FORM__LABEL, oldLabel, label));
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
      case MipsPackage.IBR2_FORM__RT:
        return basicSetRt(null, msgs);
      case MipsPackage.IBR2_FORM__RS:
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
      case MipsPackage.IBR2_FORM__RT:
        return getRt();
      case MipsPackage.IBR2_FORM__RS:
        return getRs();
      case MipsPackage.IBR2_FORM__LABEL:
        if (resolve) return getLabel();
        return basicGetLabel();
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
      case MipsPackage.IBR2_FORM__RT:
        setRt((Reg)newValue);
        return;
      case MipsPackage.IBR2_FORM__RS:
        setRs((Reg)newValue);
        return;
      case MipsPackage.IBR2_FORM__LABEL:
        setLabel((Label)newValue);
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
      case MipsPackage.IBR2_FORM__RT:
        setRt((Reg)null);
        return;
      case MipsPackage.IBR2_FORM__RS:
        setRs((Reg)null);
        return;
      case MipsPackage.IBR2_FORM__LABEL:
        setLabel((Label)null);
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
      case MipsPackage.IBR2_FORM__RT:
        return rt != null;
      case MipsPackage.IBR2_FORM__RS:
        return rs != null;
      case MipsPackage.IBR2_FORM__LABEL:
        return label != null;
    }
    return super.eIsSet(featureID);
  }

} //IBr2FormImpl
