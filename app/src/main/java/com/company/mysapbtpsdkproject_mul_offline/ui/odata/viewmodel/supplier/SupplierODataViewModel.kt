package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.supplier

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.Supplier

class SupplierODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.supplier,
    ESPMContainerMetadata.EntitySets.suppliers,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = Supplier.city,
            value = masterEntity.getOptionalValue(Supplier.city)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.country,
            value = masterEntity.getOptionalValue(Supplier.country)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.emailAddress,
            value = masterEntity.getOptionalValue(Supplier.emailAddress)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.houseNumber,
            value = masterEntity.getOptionalValue(Supplier.houseNumber)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.phoneNumber,
            value = masterEntity.getOptionalValue(Supplier.phoneNumber)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.postalCode,
            value = masterEntity.getOptionalValue(Supplier.postalCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.street,
            value = masterEntity.getOptionalValue(Supplier.street)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Supplier.supplierName,
            value = masterEntity.getOptionalValue(Supplier.supplierName)?.toString() ?: "",
        ))

        if (!isEdit) {
        // add the non-computed key properties to the list
        }

        return list.map { validateFieldState(it, it.value) }
    }

//    override fun getAvatarText(entity: EntityValue?): String {
//        val customer = entity as Customer?
//        return customer?.let { "${it.lastName?.first() ?: '?'}${it.firstName?.first() ?: '?'}" }
//            ?: "??"
//    }
}
