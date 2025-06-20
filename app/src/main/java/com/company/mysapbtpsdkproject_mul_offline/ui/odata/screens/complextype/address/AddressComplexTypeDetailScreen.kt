package com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.ScreenType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.getEntityScreenInfo
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screenTitle
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreenSettings
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.defaultObjectHeaderData
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ComplexTypeViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.IEntityOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ODataViewModel
import com.sap.cloud.mobile.fiori.compose.keyvaluecell.model.FioriKeyValueCellContent
import com.sap.cloud.mobile.fiori.compose.keyvaluecell.ui.FioriKeyValueCell
import com.sap.cloud.mobile.fiori.compose.objectheader.model.FioriObjectHeaderStatusLayout
import com.sap.cloud.mobile.fiori.compose.objectheader.ui.FioriObjectHeader
import com.sap.cloud.mobile.fiori.compose.theme.fioriHorizonAttributes
import com.sap.cloud.mobile.kotlin.odata.ComplexValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.android.odata.espmcontainer.Address

val AddressComplexTypeDetailScreen: @Composable (
    onNavigateProperty: (ComplexValue, Property, IEntityOperationType) -> Unit,
    navigateUp: (() -> Unit)?,
    viewModel: ODataViewModel<ComplexValue>,
    isExpandedScreen: Boolean
) -> Unit = { _, navigateUp, odataViewModel, isExpandedScreen ->
    val viewModel = odataViewModel as ComplexTypeViewModel
    val uiState by viewModel.odataUIState.collectAsState()

    OperationScreen(
        screenSettings = OperationScreenSettings(
            title = screenTitle(getEntityScreenInfo(viewModel.complexType), ScreenType.Details),
            actionItems = emptyList(),
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
                                key = Address.houseNumber.name,
                                value = entity.getOptionalValue(Address.houseNumber)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = Address.street.name,
                                value = entity.getOptionalValue(Address.street)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = Address.city.name,
                                value = entity.getOptionalValue(Address.city)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = Address.country.name,
                                value = entity.getOptionalValue(Address.country)?.toString() ?: ""
                            )
                        )

                    FioriKeyValueCell(
                        content = FioriKeyValueCellContent(
                                key = Address.postalCode.name,
                                value = entity.getOptionalValue(Address.postalCode)?.toString() ?: ""
                            )
                        )

                }
            }
        }
    }
}

