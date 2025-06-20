package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.salesorderitem

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.SalesOrderItem

class SalesOrderItemODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.salesOrderItem,
    ESPMContainerMetadata.EntitySets.salesOrderItems,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = SalesOrderItem.currencyCode,
            value = masterEntity.getOptionalValue(SalesOrderItem.currencyCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.deliveryDate,
            value = masterEntity.getOptionalValue(SalesOrderItem.deliveryDate)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.grossAmount,
            value = masterEntity.getOptionalValue(SalesOrderItem.grossAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.netAmount,
            value = masterEntity.getOptionalValue(SalesOrderItem.netAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.productID,
            value = masterEntity.getOptionalValue(SalesOrderItem.productID)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.quantity,
            value = masterEntity.getOptionalValue(SalesOrderItem.quantity)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.quantityUnit,
            value = masterEntity.getOptionalValue(SalesOrderItem.quantityUnit)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderItem.taxAmount,
            value = masterEntity.getOptionalValue(SalesOrderItem.taxAmount)?.toString() ?: "",
        ))

        if (!isEdit) {
        // add the non-computed key properties to the list
            list.add( FieldUIState(
                property = SalesOrderItem.itemNumber,
                value = masterEntity.getOptionalValue(SalesOrderItem.itemNumber)?.toString() ?: "",
            ))
            list.add( FieldUIState(
                property = SalesOrderItem.salesOrderID,
                value = masterEntity.getOptionalValue(SalesOrderItem.salesOrderID)?.toString() ?: "",
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
