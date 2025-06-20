package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.salesorderheader

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.SalesOrderHeader

class SalesOrderHeaderODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.salesOrderHeader,
    ESPMContainerMetadata.EntitySets.salesOrderHeaders,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = SalesOrderHeader.createdAt,
            value = masterEntity.getOptionalValue(SalesOrderHeader.createdAt)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.currencyCode,
            value = masterEntity.getOptionalValue(SalesOrderHeader.currencyCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.customerID,
            value = masterEntity.getOptionalValue(SalesOrderHeader.customerID)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.grossAmount,
            value = masterEntity.getOptionalValue(SalesOrderHeader.grossAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.lifeCycleStatus,
            value = masterEntity.getOptionalValue(SalesOrderHeader.lifeCycleStatus)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.lifeCycleStatusName,
            value = masterEntity.getOptionalValue(SalesOrderHeader.lifeCycleStatusName)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.netAmount,
            value = masterEntity.getOptionalValue(SalesOrderHeader.netAmount)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = SalesOrderHeader.taxAmount,
            value = masterEntity.getOptionalValue(SalesOrderHeader.taxAmount)?.toString() ?: "",
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
