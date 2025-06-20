package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.purchaseorderitem

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.PurchaseOrderItem

class PurchaseOrderItemODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.purchaseOrderItem,
    ESPMContainerMetadata.EntitySets.purchaseOrderItems,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = PurchaseOrderItem.currencyCode,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.currencyCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderItem.grossAmount,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.grossAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderItem.netAmount,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.netAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderItem.productID,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.productID)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderItem.quantity,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.quantity)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderItem.quantityUnit,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.quantityUnit)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = PurchaseOrderItem.taxAmount,
            value = masterEntity.getOptionalValue(PurchaseOrderItem.taxAmount)?.toString() ?: "",
        ))

        if (!isEdit) {
        // add the non-computed key properties to the list
            list.add( FieldUIState(
                property = PurchaseOrderItem.itemNumber,
                value = masterEntity.getOptionalValue(PurchaseOrderItem.itemNumber)?.toString() ?: "",
            ))
            list.add( FieldUIState(
                property = PurchaseOrderItem.purchaseOrderID,
                value = masterEntity.getOptionalValue(PurchaseOrderItem.purchaseOrderID)?.toString() ?: "",
            ))
        }

        return list.map { validateFieldState(it, it.value) }
    }

//    override fun getAvatarText(entity: EntityValue?): String {
//        val customer = entity as Customer?
//        return customer?.let { "${it.lastName?.first() ?: '?'}${it.firstName?.first() ?: '?'}" }
//            ?: "??"
//    }
}
