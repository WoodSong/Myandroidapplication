package com.company.mysapbtpsdkproject_mul_offline.ui.odata

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.company.mysapbtpsdkproject_mul_offline.R
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata.EntityTypes
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.customer.CustomerEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.customer.CustomerEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.customer.CustomerEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.customer.CustomerEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.productcategory.ProductCategoryEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.productcategory.ProductCategoryEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.productcategory.ProductCategoryEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.productcategory.ProductCategoryEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.producttext.ProductTextEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.producttext.ProductTextEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.producttext.ProductTextEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.producttext.ProductTextEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.product.ProductEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.product.ProductEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.product.ProductEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.product.ProductEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderheader.PurchaseOrderHeaderEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderheader.PurchaseOrderHeaderEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderheader.PurchaseOrderHeaderEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderheader.PurchaseOrderHeaderEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderitem.PurchaseOrderItemEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderitem.PurchaseOrderItemEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderitem.PurchaseOrderItemEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.purchaseorderitem.PurchaseOrderItemEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderheader.SalesOrderHeaderEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderheader.SalesOrderHeaderEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderheader.SalesOrderHeaderEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderheader.SalesOrderHeaderEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderitem.SalesOrderItemEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderitem.SalesOrderItemEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderitem.SalesOrderItemEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderitem.SalesOrderItemEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.stock.StockEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.stock.StockEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.stock.StockEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.stock.StockEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.supplier.SupplierEntitiesScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.supplier.SupplierEntityEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.supplier.SupplierEntityDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.supplier.SupplierEntitiesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address.AddressComplexTypeListScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address.AddressComplexTypeEditScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address.AddressComplexTypeDetailScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address.AddressComplexTypesExpandScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.IEntityOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ODataViewModel
import com.sap.cloud.mobile.kotlin.odata.ComplexType
import com.sap.cloud.mobile.kotlin.odata.ComplexValue
import com.sap.cloud.mobile.kotlin.odata.EntitySet
import com.sap.cloud.mobile.kotlin.odata.EntityType
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

interface IScreenInfo<T : StructureBase> {
    val setTitleId: Int
    val itemTitleId: Int
    val entityExpandScreen: @Composable (
        navigateToHome: () -> Unit,
        navigateUp: () -> Unit,
        onNavigateProperty: (StructureBase, Property, IEntityOperationType) -> Unit,
        viewModel: ODataViewModel<T>,
    ) -> Unit
    val entityListScreen: @Composable (
        navigateToHome: () -> Unit,
        navigateUp: () -> Unit,
        viewModel: ODataViewModel<T>,
        isExpandedScreen: Boolean
    ) -> Unit
    val entityEditScreen: @Composable (
        onNavigateProperty: (StructureBase, Property, IEntityOperationType) -> Unit,
        navigateUp: () -> Unit, viewModel: ODataViewModel<T>, isExpandedScreen: Boolean
    ) -> Unit
    val entityDetailScreen: @Composable (
        onNavigateProperty: (StructureBase, Property, IEntityOperationType) -> Unit, navigateUp: () -> Unit, viewModel: ODataViewModel<T>, isExpandedScreen: Boolean
    ) -> Unit
}

enum class ComplexScreenInfo(
    val complexType: ComplexType,
    override val setTitleId: Int,
    override val itemTitleId: Int,
    override val entityExpandScreen: @Composable (() -> Unit, () -> Unit, (ComplexValue, Property, IEntityOperationType) -> Unit, ODataViewModel<ComplexValue>) -> Unit,
    override val entityListScreen: @Composable (() -> Unit, () -> Unit, ODataViewModel<ComplexValue>, Boolean) -> Unit,
    override val entityEditScreen: @Composable (onNavigateProperty: (ComplexValue, Property, IEntityOperationType) -> Unit, () -> Unit, ODataViewModel<ComplexValue>, Boolean) -> Unit,
    override val entityDetailScreen: @Composable ((ComplexValue, Property, IEntityOperationType) -> Unit, () -> Unit, ODataViewModel<ComplexValue>, Boolean) -> Unit
) : IScreenInfo<ComplexValue> {
    Address(
        ESPMContainerMetadata.ComplexTypes.address,
        R.string.complex_type_address,
        R.string.complex_type_address_single,
        AddressComplexTypesExpandScreen,
        AddressComplexTypeListScreen,
        AddressComplexTypeEditScreen,
        AddressComplexTypeDetailScreen
    ),
}

enum class EntityScreenInfo(
    val entityType: EntityType,
    val entitySet: EntitySet?,
    override val setTitleId: Int,
    override val itemTitleId: Int,
    val iconId: Int,
        override val entityExpandScreen: @Composable (() -> Unit, () -> Unit, (EntityValue, Property, IEntityOperationType) -> Unit, ODataViewModel<EntityValue>) -> Unit,
        override val entityListScreen: @Composable (() -> Unit, () -> Unit, ODataViewModel<EntityValue>, Boolean) -> Unit,
        override val entityEditScreen: @Composable ((EntityValue, Property, IEntityOperationType) -> Unit, () -> Unit, ODataViewModel<EntityValue>, Boolean) -> Unit,
        override val entityDetailScreen: @Composable ((EntityValue, Property, IEntityOperationType) -> Unit, () -> Unit, ODataViewModel<EntityValue>, Boolean) -> Unit,
) : IScreenInfo<EntityValue> {
    Customers(
        EntityTypes.customer,
        ESPMContainerMetadata.EntitySets.customers,
        R.string.eset_customers,
        R.string.eset_customers_single,
        R.drawable.ic_sap_icon_product_filled_round,
        CustomerEntitiesExpandScreen,
        CustomerEntitiesScreen,
        CustomerEntityEditScreen,
        CustomerEntityDetailScreen
    ),
    ProductCategories(
        EntityTypes.productCategory,
        ESPMContainerMetadata.EntitySets.productCategories,
        R.string.eset_productcategories,
        R.string.eset_productcategories_single,
        R.drawable.ic_sap_icon_product_outlined,
        ProductCategoryEntitiesExpandScreen,
        ProductCategoryEntitiesScreen,
        ProductCategoryEntityEditScreen,
        ProductCategoryEntityDetailScreen
    ),
    ProductTexts(
        EntityTypes.productText,
        ESPMContainerMetadata.EntitySets.productTexts,
        R.string.eset_producttexts,
        R.string.eset_producttexts_single,
        R.drawable.ic_sap_icon_product_filled_round,
        ProductTextEntitiesExpandScreen,
        ProductTextEntitiesScreen,
        ProductTextEntityEditScreen,
        ProductTextEntityDetailScreen
    ),
    Products(
        EntityTypes.product,
        ESPMContainerMetadata.EntitySets.products,
        R.string.eset_products,
        R.string.eset_products_single,
        R.drawable.ic_sap_icon_product_outlined,
        ProductEntitiesExpandScreen,
        ProductEntitiesScreen,
        ProductEntityEditScreen,
        ProductEntityDetailScreen
    ),
    PurchaseOrderHeaders(
        EntityTypes.purchaseOrderHeader,
        ESPMContainerMetadata.EntitySets.purchaseOrderHeaders,
        R.string.eset_purchaseorderheaders,
        R.string.eset_purchaseorderheaders_single,
        R.drawable.ic_sap_icon_product_filled_round,
        PurchaseOrderHeaderEntitiesExpandScreen,
        PurchaseOrderHeaderEntitiesScreen,
        PurchaseOrderHeaderEntityEditScreen,
        PurchaseOrderHeaderEntityDetailScreen
    ),
    PurchaseOrderItems(
        EntityTypes.purchaseOrderItem,
        ESPMContainerMetadata.EntitySets.purchaseOrderItems,
        R.string.eset_purchaseorderitems,
        R.string.eset_purchaseorderitems_single,
        R.drawable.ic_sap_icon_product_outlined,
        PurchaseOrderItemEntitiesExpandScreen,
        PurchaseOrderItemEntitiesScreen,
        PurchaseOrderItemEntityEditScreen,
        PurchaseOrderItemEntityDetailScreen
    ),
    SalesOrderHeaders(
        EntityTypes.salesOrderHeader,
        ESPMContainerMetadata.EntitySets.salesOrderHeaders,
        R.string.eset_salesorderheaders,
        R.string.eset_salesorderheaders_single,
        R.drawable.ic_sap_icon_product_filled_round,
        SalesOrderHeaderEntitiesExpandScreen,
        SalesOrderHeaderEntitiesScreen,
        SalesOrderHeaderEntityEditScreen,
        SalesOrderHeaderEntityDetailScreen
    ),
    SalesOrderItems(
        EntityTypes.salesOrderItem,
        ESPMContainerMetadata.EntitySets.salesOrderItems,
        R.string.eset_salesorderitems,
        R.string.eset_salesorderitems_single,
        R.drawable.ic_sap_icon_product_outlined,
        SalesOrderItemEntitiesExpandScreen,
        SalesOrderItemEntitiesScreen,
        SalesOrderItemEntityEditScreen,
        SalesOrderItemEntityDetailScreen
    ),
    Stock(
        EntityTypes.stock,
        ESPMContainerMetadata.EntitySets.stock,
        R.string.eset_stock,
        R.string.eset_stock_single,
        R.drawable.ic_sap_icon_product_filled_round,
        StockEntitiesExpandScreen,
        StockEntitiesScreen,
        StockEntityEditScreen,
        StockEntityDetailScreen
    ),
    Suppliers(
        EntityTypes.supplier,
        ESPMContainerMetadata.EntitySets.suppliers,
        R.string.eset_suppliers,
        R.string.eset_suppliers_single,
        R.drawable.ic_sap_icon_product_outlined,
        SupplierEntitiesExpandScreen,
        SupplierEntitiesScreen,
        SupplierEntityEditScreen,
        SupplierEntityDetailScreen
    ),
}

fun getEntitySetScreenInfoList(): List<EntityScreenInfo> {
    val metadataMap = EntityMetaData.entries.associateBy { it.entityType }
    return EntityScreenInfo.entries.filter { metadataMap[it.entityType]?.entitySet != null }
}

// return screen info according to specified entity type and entity set
fun getEntityScreenInfo(entityType: EntityType, entitySet: EntitySet?): EntityScreenInfo =
    EntityScreenInfo.entries.first { getKey(entityType, entitySet) == getKey(it.entityType, it.entitySet) }

fun getEntityScreenInfo(complexType: ComplexType): ComplexScreenInfo {
    return ComplexScreenInfo.entries.first { it.complexType == complexType }
}

enum class ScreenType {
    List, Details, Update, Create, NavigatedList
}

@Composable
fun <T : StructureBase> screenTitle(screenInfo: IScreenInfo<T>, screenType: ScreenType): String {
    return when (screenType) {
        //TODO: navigated list title?
        ScreenType.List, ScreenType.NavigatedList -> stringResource(id = screenInfo.setTitleId)
        ScreenType.Details -> stringResource(id = screenInfo.itemTitleId)
        ScreenType.Update -> stringResource(id = R.string.title_update_fragment) + " ${
            stringResource(
                id = screenInfo.itemTitleId
            )
        }"
        ScreenType.Create -> stringResource(
            id = R.string.title_create_fragment,
            stringResource(id = screenInfo.itemTitleId)
        )
    }
}
