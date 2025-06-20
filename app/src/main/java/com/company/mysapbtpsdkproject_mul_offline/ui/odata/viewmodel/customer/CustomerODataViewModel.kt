package com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.customer

import android.app.Application
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.FieldUIState
import com.sap.cloud.android.odata.espmcontainer.ESPMContainerMetadata
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.mobile.kotlin.odata.StructureBase

import com.sap.cloud.android.odata.espmcontainer.Customer

class CustomerODataViewModel(
    application: Application,
    orderByProperty: Property?,
    parent: EntityValue? = null,
    navigationPropertyName: String? = null,
) : EntityViewModel(
    application,
    ESPMContainerMetadata.EntityTypes.customer,
    ESPMContainerMetadata.EntitySets.customers,
    orderByProperty,
    parent,
    navigationPropertyName
) {
    override fun populateFiledStates(masterEntity: StructureBase, isEdit: Boolean): List<FieldUIState> {
        val list = mutableListOf<FieldUIState>()
        list.add(FieldUIState(
            property = Customer.city,
            value = masterEntity.getOptionalValue(Customer.city)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.country,
            value = masterEntity.getOptionalValue(Customer.country)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.dateOfBirth,
            value = masterEntity.getOptionalValue(Customer.dateOfBirth)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.emailAddress,
            value = masterEntity.getOptionalValue(Customer.emailAddress)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.firstName,
            value = masterEntity.getOptionalValue(Customer.firstName)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.houseNumber,
            value = masterEntity.getOptionalValue(Customer.houseNumber)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.lastName,
            value = masterEntity.getOptionalValue(Customer.lastName)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.phoneNumber,
            value = masterEntity.getOptionalValue(Customer.phoneNumber)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.postalCode,
            value = masterEntity.getOptionalValue(Customer.postalCode)?.toString() ?: "",
        ))
        list.add(FieldUIState(
            property = Customer.street,
            value = masterEntity.getOptionalValue(Customer.street)?.toString() ?: "",
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
