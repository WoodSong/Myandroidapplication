package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.productcategory

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.ProductCategory

class ProductCategoryODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.productCategory,
    ESPMContainerMetadata.EntitySets.productCategories,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = ProductCategory.categoryName,
            value = masterEntity.getOptionalValue(ProductCategory.categoryName)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductCategory.mainCategory,
            value = masterEntity.getOptionalValue(ProductCategory.mainCategory)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductCategory.mainCategoryName,
            value = masterEntity.getOptionalValue(ProductCategory.mainCategoryName)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = ProductCategory.numberOfProducts,
            value = masterEntity.getOptionalValue(ProductCategory.numberOfProducts)?.toString() ?: "",
        ))

        if (!isEdit) {
        // add the non-computed key properties to the list
            list.add( FieldUIState(
                property = ProductCategory.category,
                value = masterEntity.getOptionalValue(ProductCategory.category)?.toString() ?: "",
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
