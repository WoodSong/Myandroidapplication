package com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.mysapbtpsdkproject_mul_offline.R
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.ActionItem
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.OverflowMode
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.ScreenType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.getEntityScreenInfo
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screenTitle
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.LeaveEditorWithConfirmation
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreenSettings
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ComplexTypeViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityUpdateOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.IEntityOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ODataViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.DeleteEntityWithConfirmation
import com.sap.cloud.mobile.fiori.compose.text.model.FioriTextFieldContent
import com.sap.cloud.mobile.fiori.compose.text.ui.FioriSimpleTextField
import com.sap.cloud.mobile.fiori.compose.theme.fioriHorizonAttributes
import com.sap.cloud.mobile.kotlin.odata.ComplexValue
import com.sap.cloud.mobile.kotlin.odata.Property
import com.sap.cloud.android.odata.espmcontainer.Address

val AddressComplexTypeEditScreen: @Composable (
    onNavigateProperty: (ComplexValue, Property, IEntityOperationType) -> Unit,
    navigateUp: (() -> Unit)?,
    viewModel: ODataViewModel<ComplexValue>,
    isExpandedScreen: Boolean
) -> Unit = { _, navigateUp, odataViewModel, isExpandedScreen ->
    val viewModel = odataViewModel as ComplexTypeViewModel
    val odataUIState by viewModel.odataUIState.collectAsState()
    val masterEntity = odataUIState.masterEntity!!
    val fieldStates = odataUIState.editorFiledStates
    val isCreation = odataUIState.entityOperationType == EntityOperationType.CREATE
    val isNavigateUp = remember {
        mutableStateOf(false)
    }

    val deleteConfirm = remember {
        mutableStateOf(false)
    }

    DeleteEntityWithConfirmation(viewModel, deleteConfirm)

    if (isNavigateUp.value) {
        LeaveEditorWithConfirmation(isNavigateUp, navigateUp!!)
    }

    BackHandler(!isExpandedScreen) {
        isNavigateUp.value = true
    }

    OperationScreen(
        screenSettings = OperationScreenSettings(
            title = screenTitle(
                getEntityScreenInfo(viewModel.complexType),
                if (isCreation) ScreenType.Create else ScreenType.Update
            ),
            navigateUp = if (isExpandedScreen) null else ({ isNavigateUp.value = true }),
            actionItems = listOf(
                ActionItem(
                    nameRes = R.string.save,
                    iconRes = R.drawable.ic_sap_icon_done,
                    overflowMode = OverflowMode.IF_NECESSARY,
                    enabled = fieldStates.none { it.isError },
                    doAction = {
                        viewModel.onSaveAction(
                            masterEntity,
                            fieldStates.map { Pair(it.property, it.value) })

                    }),
                ActionItem(
                    nameRes = R.string.menu_delete,
                    iconRes = R.drawable.ic_sap_icon_delete,
                    overflowMode = OverflowMode.IF_NECESSARY,
                    doAction = { deleteConfirm.value = true }
                )
            ),
        ),
        modifier = Modifier,
        viewModel = viewModel
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp)
        ) {
            itemsIndexed(odataUIState.editorFiledStates)
            { index, uiState ->
                FioriSimpleTextField(
                    value = uiState.value,
                    onValueChange = {
                        viewModel.updateFieldState(index, it)
                    },
                    content = FioriTextFieldContent(
                        label = uiState.property.name,
                        required = !uiState.property.isNullable,
                        errorMessage = uiState.errorMessage
                    ),
                    isError = uiState.isError,
                )
            }
        }
    }
}
