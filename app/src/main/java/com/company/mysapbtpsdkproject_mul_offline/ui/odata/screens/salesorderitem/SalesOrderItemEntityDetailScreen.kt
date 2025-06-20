package com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.salesorderitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.mysapbtpsdkproject_mul_offline.R
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.*
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.*
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.*
import com.sap.cloud.mobile.fiori.compose.keyvaluecell.model.FioriKeyValueCellContent
import com.sap.cloud.mobile.fiori.compose.keyvaluecell.ui.FioriKeyValueCell
import com.sap.cloud.mobile.fiori.compose.objectheader.model.*
import com.sap.cloud.mobile.fiori.compose.objectheader.ui.FioriObjectHeader
import com.sap.cloud.mobile.kotlin.odata.EntityValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreenSettings
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.defaultObjectHeaderData
import com.sap.cloud.mobile.fiori.compose.theme.fioriHorizonAttributes
import com.sap.cloud.android.odata.espmcontainer.SalesOrderItem

val SalesOrderItemEntityDetailScreen: @Composable (
    onNavigateProperty: (EntityValue, Property, IEntityOperationType) -> Unit,
    navigateUp: (() -> Unit)?,
    viewModel: ODataViewModel<EntityValue>,
    isExpandedScreen: Boolean
) -> Unit = { onNavigateProperty, navigateUp, odataViewModel, isExpandedScreen ->
    val viewModel = odataViewModel as EntityViewModel
    val uiState by viewModel.odataUIState.collectAsState()
    val imageByteArray by viewModel.loadMasterEntityMedia().collectAsState(initial = null)

    val deleteConfirm = remember {
        mutableStateOf(false)
    }

    DeleteEntityWithConfirmation(viewModel, deleteConfirm)

    OperationScreen(
        screenSettings = OperationScreenSettings(
            title = screenTitle(getEntityScreenInfo(viewModel.entityType, viewModel.entitySet), ScreenType.Details),
            actionItems = uiState.masterEntity?.let { listOf(
                ActionItem(
                    nameRes = R.string.menu_update,
                    iconRes = R.drawable.ic_sap_icon_edit,
                    overflowMode = OverflowMode.IF_NECESSARY,
                    doAction = viewModel::onEditAction
                ),
                ActionItem(
                    nameRes = R.string.menu_delete,
                    iconRes = R.drawable.ic_sap_icon_delete,
                    overflowMode = OverflowMode.IF_NECESSARY,
                    doAction = { deleteConfirm.value = true }
                ),
            )} ?: emptyList(),
            navigateUp = if (isExpandedScreen) null else navigateUp,
        ),
        modifier = Modifier,
        viewModel = viewModel
    ) {
        Column {
            val entity = uiState.masterEntity
            if (entity != null) {
                FioriObjectHeader(
                    modifier = Modifier.fillMaxWidth(),
                    primaryPage = defaultObjectHeaderData(
                        title = viewModel.getEntityTitle(entity),
                        imageByteArray = imageByteArray,
                        imageChars = viewModel.getAvatarText(entity)
                    ),
                    statusLayout = FioriObjectHeaderStatusLayout.Inline
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, end = 12.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.itemNumber.name,
                                value = entity.getOptionalValue(SalesOrderItem.itemNumber)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.salesOrderID.name,
                                value = entity.getOptionalValue(SalesOrderItem.salesOrderID)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.currencyCode.name,
                                value = entity.getOptionalValue(SalesOrderItem.currencyCode)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.deliveryDate.name,
                                value = entity.getOptionalValue(SalesOrderItem.deliveryDate)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.grossAmount.name,
                                value = entity.getOptionalValue(SalesOrderItem.grossAmount)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.netAmount.name,
                                value = entity.getOptionalValue(SalesOrderItem.netAmount)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.productID.name,
                                value = entity.getOptionalValue(SalesOrderItem.productID)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.quantity.name,
                                value = entity.getOptionalValue(SalesOrderItem.quantity)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.quantityUnit.name,
                                value = entity.getOptionalValue(SalesOrderItem.quantityUnit)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = SalesOrderItem.taxAmount.name,
                                value = entity.getOptionalValue(SalesOrderItem.taxAmount)?.toString() ?: ""
                            )
                        )

                    TextButton(onClick = {
                        onNavigateProperty(entity, SalesOrderItem.product, EntityOperationType.DETAIL)
                    }) {
                        Text("Product", color = MaterialTheme.fioriHorizonAttributes.SapFioriColorT6)
                    }

                    TextButton(onClick = {
                        onNavigateProperty(entity, SalesOrderItem.header, EntityOperationType.DETAIL)
                    }) {
                        Text("Header", color = MaterialTheme.fioriHorizonAttributes.SapFioriColorT6)
                    }

                }
            }
        }
    }
}

