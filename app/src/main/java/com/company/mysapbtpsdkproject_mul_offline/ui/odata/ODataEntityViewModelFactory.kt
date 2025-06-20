package com.company.mysapbtpsdkproject_mul_offline.ui.odata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.customer.CustomerODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.productcategory.ProductCategoryODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.producttext.ProductTextODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.product.ProductODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.purchaseorderheader.PurchaseOrderHeaderODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.purchaseorderitem.PurchaseOrderItemODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.salesorderheader.SalesOrderHeaderODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.salesorderitem.SalesOrderItemODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.stock.StockODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.supplier.SupplierODataViewModel
import com.sap.cloud.mobile.kotlin.odata.EntitySet
import com.sap.cloud.mobile.kotlin.odata.EntityType
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata.EntityTypes

class ODataEntityViewModelFactory(
    private val application: Application,
    private val entityType: EntityType,
    private val entitySet: EntitySet?,
    private val orderByProperty: Property?,
    private val parent: EntityValue? = null,
    private val navigationPropertyName: String? = null,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (getKey(entityType, entitySet)) {
            getKey(EntityTypes.customer, ESPMContainerMetadata.EntitySets.customers) -> CustomerODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.productCategory, ESPMContainerMetadata.EntitySets.productCategories) -> ProductCategoryODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.productText, ESPMContainerMetadata.EntitySets.productTexts) -> ProductTextODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.product, ESPMContainerMetadata.EntitySets.products) -> ProductODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.purchaseOrderHeader, ESPMContainerMetadata.EntitySets.purchaseOrderHeaders) -> PurchaseOrderHeaderODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.purchaseOrderItem, ESPMContainerMetadata.EntitySets.purchaseOrderItems) -> PurchaseOrderItemODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.salesOrderHeader, ESPMContainerMetadata.EntitySets.salesOrderHeaders) -> SalesOrderHeaderODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.salesOrderItem, ESPMContainerMetadata.EntitySets.salesOrderItems) -> SalesOrderItemODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.stock, ESPMContainerMetadata.EntitySets.stock) -> StockODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            getKey(EntityTypes.supplier, ESPMContainerMetadata.EntitySets.suppliers) -> SupplierODataViewModel(
                application,
                orderByProperty,
                parent,
                navigationPropertyName
            ) as T
            else -> { throw UnsupportedOperationException() }
        }
    }
}
