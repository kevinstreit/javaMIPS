/**
 * <copyright>
 * </copyright>
 *
 */
package de.unisb.prog.mips.asm.mips.impl;

import de.unisb.prog.mips.asm.mips.Label;
import de.unisb.prog.mips.asm.mips.LoadAddress;
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
 * An implementation of the model object '<em><b>Load Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.LoadAddressImpl#getRt <em>Rt</em>}</li>
 *   <li>{@link de.unisb.prog.mips.asm.mips.impl.LoadAddressImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoadAddressImpl extends MinimalEObjectImpl.Container implements LoadAddress
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
  protected LoadAddressImpl()
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
    return MipsPackage.Literals.LOAD_ADDRESS;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MipsPackage.LOAD_ADDRESS__RT, oldRt, newRt);
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
        msgs = ((InternalEObject)rt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MipsPackage.LOAD_ADDRESS__RT, null, msgs);
      if (newRt != null)
        msgs = ((InternalEObject)newRt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MipsPackage.LOAD_ADDRESS__RT, null, msgs);
      msgs = basicSetRt(newRt, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.LOAD_ADDRESS__RT, newRt, newRt));
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MipsPackage.LOAD_ADDRESS__LABEL, oldLabel, label));
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
      eNotify(new ENotificationImpl(this, Notification.SET, MipsPackage.LOAD_ADDRESS__LABEL, oldLabel, label));
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
      case MipsPackage.LOAD_ADDRESS__RT:
        return basicSetRt(null, msgs);
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
      case MipsPackage.LOAD_ADDRESS__RT:
        return getRt();
      case MipsPackage.LOAD_ADDRESS__LABEL:
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
      case MipsPackage.LOAD_ADDRESS__RT:
        setRt((Reg)newValue);
        return;
      case MipsPackage.LOAD_ADDRESS__LABEL:
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
      case MipsPackage.LOAD_ADDRESS__RT:
        setRt((Reg)null);
        return;
      case MipsPackage.LOAD_ADDRESS__LABEL:
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
      case MipsPackage.LOAD_ADDRESS__RT:
        return rt != null;
      case MipsPackage.LOAD_ADDRESS__LABEL:
        return label != null;
    }
    return super.eIsSet(featureID);
  }

} //LoadAddressImpl
