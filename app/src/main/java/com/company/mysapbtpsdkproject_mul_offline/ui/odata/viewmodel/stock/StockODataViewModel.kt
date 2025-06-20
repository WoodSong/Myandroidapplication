package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.stock

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.Stock

class StockODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.stock,
    ESPMContainerMetadata.EntitySets.stock,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = Stock.lotSize,
            value = masterEntity.getOptionalValue(Stock.lotSize)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Stock.minStock,
            value = masterEntity.getOptionalValue(Stock.minStock)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Stock.quantity,
            value = masterEntity.getOptionalValue(Stock.quantity)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Stock.quantityLessMin,
            value = masterEntity.getOptionalValue(Stock.quantityLessMin)?.toString() ?: "",
        ))

        if (!isEdit) {
        // add the non-computed key properties to the list
            list.add( FieldUIState(
                property = Stock.productID,
                value = masterEntity.getOptionalValue(Stock.productID)?.toString() ?: "",
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
