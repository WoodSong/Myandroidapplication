package com.company.mysapbtpsdkproject_mul_offline.ui.odata

import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.ComplexType
import com.sap.cloud.mobile.kotlin.odata.EntitySet
import com.sap.cloud.mobile.kotlin.odata.EntityType
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata.EntityTypes

enum class EntityMetaData(
    val entityType: EntityType,
    val orderByProperty: Property?,
    val entitySet: EntitySet? = null,
) {
    Customers(
        EntityTypes.customer,
        com.sap.cloud.android.odata.espmcontainer.Customer.city,
        ESPMContainerMetadata.EntitySets.customers,
        ),
    ProductCategories(
        EntityTypes.productCategory,
        com.sap.cloud.android.odata.espmcontainer.ProductCategory.categoryName,
        ESPMContainerMetadata.EntitySets.productCategories,
        ),
    ProductTexts(
        EntityTypes.productText,
        com.sap.cloud.android.odata.espmcontainer.ProductText.language,
        ESPMContainerMetadata.EntitySets.productTexts,
        ),
    Products(
        EntityTypes.product,
        com.sap.cloud.android.odata.espmcontainer.Product.category,
        ESPMContainerMetadata.EntitySets.products,
        ),
    PurchaseOrderHeaders(
        EntityTypes.purchaseOrderHeader,
        com.sap.cloud.android.odata.espmcontainer.PurchaseOrderHeader.currencyCode,
        ESPMContainerMetadata.EntitySets.purchaseOrderHeaders,
        ),
    PurchaseOrderItems(
        EntityTypes.purchaseOrderItem,
        com.sap.cloud.android.odata.espmcontainer.PurchaseOrderItem.currencyCode,
        ESPMContainerMetadata.EntitySets.purchaseOrderItems,
        ),
    SalesOrderHeaders(
        EntityTypes.salesOrderHeader,
        com.sap.cloud.android.odata.espmcontainer.SalesOrderHeader.createdAt,
        ESPMContainerMetadata.EntitySets.salesOrderHeaders,
        ),
    SalesOrderItems(
        EntityTypes.salesOrderItem,
        com.sap.cloud.android.odata.espmcontainer.SalesOrderItem.currencyCode,
        ESPMContainerMetadata.EntitySets.salesOrderItems,
        ),
    Stock(
        EntityTypes.stock,
        com.sap.cloud.android.odata.espmcontainer.Stock.lotSize,
        ESPMContainerMetadata.EntitySets.stock,
        ),
    Suppliers(
        EntityTypes.supplier,
        com.sap.cloud.android.odata.espmcontainer.Supplier.city,
        ESPMContainerMetadata.EntitySets.suppliers,
        ),
}

enum class ComplexTypeMetaData(
    val complexType: ComplexType,
    val orderByProperty: Property?,
) {
    Address(
        ESPMContainerMetadata.ComplexTypes.address,
        com.sap.cloud.android.odata.espmcontainer.Address.houseNumber,
        ),
}

fun getOrderByProperty(entityType: EntityType): Property? {
    return EntityMetaData.entries.first { it.entityType == entityType }.orderByProperty
}

fun getOrderByProperty(complexType: ComplexType): Property? {
    return ComplexTypeMetaData.entries.first { it.complexType == complexType }.orderByProperty
}

fun getKey(entityType: EntityType, entitySet: EntitySet? = null): String {
    return "${entitySet?.localName}_${entityType.localName}"
}

fun getKey(complexType: ComplexType): String {
    return "complex_${complexType.localName}"
}
