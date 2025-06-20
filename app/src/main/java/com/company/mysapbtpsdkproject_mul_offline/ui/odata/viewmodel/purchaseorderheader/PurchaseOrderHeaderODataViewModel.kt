package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.purchaseorderheader

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.PurchaseOrderHeader

class PurchaseOrderHeaderODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.purchaseOrderHeader,
    ESPMContainerMetadata.EntitySets.purchaseOrderHeaders,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = PurchaseOrderHeader.currencyCode,
            value = masterEntity.getOptionalValue(PurchaseOrderHeader.currencyCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderHeader.grossAmount,
            value = masterEntity.getOptionalValue(PurchaseOrderHeader.grossAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderHeader.netAmount,
            value = masterEntity.getOptionalValue(PurchaseOrderHeader.netAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderHeader.supplierID,
            value = masterEntity.getOptionalValue(PurchaseOrderHeader.supplierID)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderHeader.taxAmount,
            value = masterEntity.getOptionalValue(PurchaseOrderHeader.taxAmount)?.toString() ?: "",
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
