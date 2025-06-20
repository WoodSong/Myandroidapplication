package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.product

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.Product

class ProductODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.product,
    ESPMContainerMetadata.EntitySets.products,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = Product.category,
            value = masterEntity.getOptionalValue(Product.category)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.categoryName,
            value = masterEntity.getOptionalValue(Product.categoryName)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.currencyCode,
            value = masterEntity.getOptionalValue(Product.currencyCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.dimensionDepth,
            value = masterEntity.getOptionalValue(Product.dimensionDepth)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.dimensionHeight,
            value = masterEntity.getOptionalValue(Product.dimensionHeight)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.dimensionUnit,
            value = masterEntity.getOptionalValue(Product.dimensionUnit)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.dimensionWidth,
            value = masterEntity.getOptionalValue(Product.dimensionWidth)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.longDescription,
            value = masterEntity.getOptionalValue(Product.longDescription)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.name,
            value = masterEntity.getOptionalValue(Product.name)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.pictureUrl,
            value = masterEntity.getOptionalValue(Product.pictureUrl)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.price,
            value = masterEntity.getOptionalValue(Product.price)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.quantityUnit,
            value = masterEntity.getOptionalValue(Product.quantityUnit)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.shortDescription,
            value = masterEntity.getOptionalValue(Product.shortDescription)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.supplierID,
            value = masterEntity.getOptionalValue(Product.supplierID)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.weight,
            value = masterEntity.getOptionalValue(Product.weight)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Product.weightUnit,
            value = masterEntity.getOptionalValue(Product.weightUnit)?.toString() ?: "",
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
