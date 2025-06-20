package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.complextype.address

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ComplexTypeViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.IEntityOperationType
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.Address

class AddressComplexTypeViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue,
    navigationPropertyName: String,
    operationType: IEntityOperationType,
) : ComplexTypeViewModel(
    application,
    ESPMContainerMetadata.ComplexTypes.address,
    orderByProperty,
    parent,
    navigationPropertyName,
    operationType,
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = Address.houseNumber,
            value = masterEntity.getOptionalValue(Address.houseNumber)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Address.street,
            value = masterEntity.getOptionalValue(Address.street)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Address.city,
            value = masterEntity.getOptionalValue(Address.city)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Address.country,
            value = masterEntity.getOptionalValue(Address.country)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Address.postalCode,
            value = masterEntity.getOptionalValue(Address.postalCode)?.toString() ?: "",
        ))

        if (!isEdit) {
        // add the non-computed key properties to the list
        }

        return list.map { validateFieldState(it, it.value) }
    }

}
