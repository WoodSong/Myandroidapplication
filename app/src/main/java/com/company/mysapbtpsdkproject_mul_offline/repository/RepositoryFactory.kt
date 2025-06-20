package com.company.mysapbtpsdkproject_mul_offline.repository

import com.company.mysapbtpsdkproject_mul_offline.service.OfflineWorkerUtil
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata.EntityTypes
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.getKey
import com.sap.cloud.mobile.kotlin.odata.EntitySet
import com.sap.cloud.mobile.kotlin.odata.EntityType
import com.sap.cloud.mobile.kotlin.odata.Property

import java.util.WeakHashMap

/*
 * Repository factory to construct repository for an entity set
 */
object RepositoryFactory
/**
 * Construct a RepositoryFactory instance. There should only be one repository factory and used
 * throughout the life of the application to avoid caching entities multiple times.
 */
{
    private val repositories: WeakHashMap<String, Repository> = WeakHashMap()

    /**
     * Construct or return an existing repository for the specified entity set
     * @param entitySet - entity set for which the repository is to be returned
     * @param orderByProperty - if specified, collection will be sorted ascending with this property
     * @return a repository for the entity set
     */
    fun getRepository(entityType: EntityType, entitySet: EntitySet?, orderByProperty: Property?): Repository {
        val eSPMContainer = OfflineWorkerUtil.eSPMContainer
        val key = getKey(entityType, entitySet)
        var repository: Repository? = repositories[key]
        if (repository == null) {
            repository = when (key) {
                getKey(EntityTypes.customer, ESPMContainerMetadata.EntitySets.customers) ->
                    Repository(eSPMContainer, EntityTypes.customer, ESPMContainerMetadata.EntitySets.customers, orderByProperty)
                getKey(EntityTypes.productCategory, ESPMContainerMetadata.EntitySets.productCategories) ->
                    Repository(eSPMContainer, EntityTypes.productCategory, ESPMContainerMetadata.EntitySets.productCategories, orderByProperty)
                getKey(EntityTypes.productText, ESPMContainerMetadata.EntitySets.productTexts) ->
                    Repository(eSPMContainer, EntityTypes.productText, ESPMContainerMetadata.EntitySets.productTexts, orderByProperty)
                getKey(EntityTypes.product, ESPMContainerMetadata.EntitySets.products) ->
                    Repository(eSPMContainer, EntityTypes.product, ESPMContainerMetadata.EntitySets.products, orderByProperty)
                getKey(EntityTypes.purchaseOrderHeader, ESPMContainerMetadata.EntitySets.purchaseOrderHeaders) ->
                    Repository(eSPMContainer, EntityTypes.purchaseOrderHeader, ESPMContainerMetadata.EntitySets.purchaseOrderHeaders, orderByProperty)
                getKey(EntityTypes.purchaseOrderItem, ESPMContainerMetadata.EntitySets.purchaseOrderItems) ->
                    Repository(eSPMContainer, EntityTypes.purchaseOrderItem, ESPMContainerMetadata.EntitySets.purchaseOrderItems, orderByProperty)
                getKey(EntityTypes.salesOrderHeader, ESPMContainerMetadata.EntitySets.salesOrderHeaders) ->
                    Repository(eSPMContainer, EntityTypes.salesOrderHeader, ESPMContainerMetadata.EntitySets.salesOrderHeaders, orderByProperty)
                getKey(EntityTypes.salesOrderItem, ESPMContainerMetadata.EntitySets.salesOrderItems) ->
                    Repository(eSPMContainer, EntityTypes.salesOrderItem, ESPMContainerMetadata.EntitySets.salesOrderItems, orderByProperty)
                getKey(EntityTypes.stock, ESPMContainerMetadata.EntitySets.stock) ->
                    Repository(eSPMContainer, EntityTypes.stock, ESPMContainerMetadata.EntitySets.stock, orderByProperty)
                getKey(EntityTypes.supplier, ESPMContainerMetadata.EntitySets.suppliers) ->
                    Repository(eSPMContainer, EntityTypes.supplier, ESPMContainerMetadata.EntitySets.suppliers, orderByProperty)
                else -> throw AssertionError("Fatal error, entity set[$key] missing in generated code")
            }
            repositories[key] = repository
        }
        return repository
    }

    /**
     * Get rid of all cached repositories
     */
    fun reset() {
        repositories.clear()
    }
}
