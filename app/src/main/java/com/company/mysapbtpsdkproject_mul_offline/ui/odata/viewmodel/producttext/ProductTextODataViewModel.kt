package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.producttext

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.ProductText

class ProductTextODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.productText,
    ESPMContainerMetadata.EntitySets.productTexts,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = ProductText.language,
            value = masterEntity.getOptionalValue(ProductText.language)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductText.longDescription,
            value = masterEntity.getOptionalValue(ProductText.longDescription)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductText.name,
            value = masterEntity.getOptionalValue(ProductText.name)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductText.productID,
            value = masterEntity.getOptionalValue(ProductText.productID)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductText.shortDescription,
            value = masterEntity.getOptionalValue(ProductText.shortDescription)?.toString() ?: "",
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
