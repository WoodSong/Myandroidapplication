package com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.complextype.address

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.company.mysapbtpsdkproject_mul_offline.R
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.ScreenType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.getEntityScreenInfo
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screenTitle
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.DeleteEntityWithConfirmation
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.LeaveEditorWithConfirmation
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreen
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.OperationScreenSettings
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.screens.getSelectedItemActionsList
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ComplexTypeViewModel
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.EntityUpdateOperationType
import com.company.mysapbtpsdkproject_mul_offline.ui.odata.viewmodel.ODataViewModel
import com.sap.cloud.mobile.fiori.compose.avatar.model.FioriAvatarConstruct
import com.sap.cloud.mobile.fiori.compose.avatar.model.FioriAvatarData
import com.sap.cloud.mobile.fiori.compose.avatar.model.FioriAvatarShape
import com.sap.cloud.mobile.fiori.compose.avatar.model.FioriAvatarType
import com.sap.cloud.mobile.fiori.compose.common.FioriImage
import com.sap.cloud.mobile.fiori.compose.objectcell.model.FioriObjectCellData
import com.sap.cloud.mobile.fiori.compose.objectcell.model.IconStackElement
import com.sap.cloud.mobile.fiori.compose.objectcell.ui.FioriObjectCell
import com.sap.cloud.mobile.fiori.compose.objectcell.ui.FioriObjectCellDefaults
import com.sap.cloud.mobile.fiori.compose.theme.fioriHorizonAttributes
import com.sap.cloud.mobile.kotlin.odata.ComplexValue
import com.sap.cloud.mobile.onboarding.compose.screens.LoadingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

//TODO: pull down screen to refresh
//https://github.com/aakarshrestha/compose-swipe-to-refresh
//https://google.github.io/accompanist/swiperefresh/
val AddressComplexTypeListScreen:
    @Composable
        (
        navigateToHome: () -> Unit,
        navigateUp: () -> Unit,
        viewModel: ODataViewModel<ComplexValue>,
        isExpandScreen: Boolean,
    ) -> Unit =
{ navigateToHome, navigateUp, odataViewModel, isExpandedScreen ->
    val viewModel = odataViewModel as ComplexTypeViewModel
    val entities = viewModel.pagingDataState.value.collectAsLazyPagingItems()
    val uiState by viewModel.odataUIState.collectAsState()

    val listState: LazyListState = rememberLazyListState()

    val isInCreateOrUpdate = remember {
        derivedStateOf {
            isExpandedScreen &&
                uiState.entityOperationType in setOf(
                    EntityOperationType.CREATE,
                    EntityUpdateOperationType.UPDATE_FROM_LIST,
                    EntityUpdateOperationType.UPDATE_FROM_DETAIL
        ) }
    }

    // List screen handle navigation popBackStack, need to implement a debouncing mechanism
    // to prevent the second back press from being processed too quickly
    val backPressFlow = remember { MutableStateFlow(0L) }
    val isLeave = remember { mutableStateOf(false) }
    val navUp = remember {
        {
            isLeave.value = true
            backPressFlow.value = System.currentTimeMillis()
        }
    }

    LaunchedEffect(Unit) {
        launch {
            backPressFlow
                .debounce(200L) // Adjust debounce time as needed
                .collectLatest {
                    if(isLeave.value) {
                        navigateUp.invoke()
                    }
                }
        }
    }

    BackHandler {
        navUp.invoke()
    }

    // Delete confirmation dialog
    val showDelConfirmDlg = remember {
        mutableStateOf(false)
    }

    DeleteEntityWithConfirmation(viewModel, showDelConfirmDlg)

    // Leave confirmation callback function
    val showLeaveConfirmDlg = remember {
        mutableStateOf(false)
    }

    // Leave dialog confirmation callback
    var onLeaveConfirmed by remember {
        //by default navigateUp, but may be changed to onClickAction in expanded screen mode
        mutableStateOf(navigateUp)
    }

    // app bar navigate up button callback
    val onNavigateUp = remember {
        {
            if(isInCreateOrUpdate.value) {
                showLeaveConfirmDlg.value = isInCreateOrUpdate.value
                onLeaveConfirmed = navUp
            }
            else {
                navUp.invoke()
            }
        }
    }

    // list item click callback
    val onClickChange: (ComplexValue) -> Unit = remember {
        { entity ->
            // in expanded screen mode, do nothing while click on current entity
            if (isInCreateOrUpdate.value) {
                if( entity != uiState.masterEntity ) {
                    showLeaveConfirmDlg.value = true
                    onLeaveConfirmed = { viewModel.onClickAction(entity) }
                }
            } else viewModel.onClickAction(entity)
        }
    }

    LeaveEditorWithConfirmation(showLeaveConfirmDlg) {
        viewModel.exitEditor()
        onLeaveConfirmed.invoke()
    }

    val actionItems =
        if (isExpandedScreen) listOf()
        else getSelectedItemActionsList(
            navigateToHome,
            viewModel,
            showDelConfirmDlg
        )

    OperationScreen(
        screenSettings = OperationScreenSettings(
            title = screenTitle(getEntityScreenInfo(viewModel.complexType), ScreenType.List),
            navigateUp = onNavigateUp,
            actionItems = actionItems,
            floatingActionClick = if (uiState.entityOperationType != EntityOperationType.DETAIL) {
                  viewModel.onFloatingAdd()
              } else null,
            floatingActionIcon = Icons.Filled.Add
        ),
        modifier = Modifier,
        viewModel = viewModel
    ) {
        if (entities.loadState.refresh == LoadState.Loading) {
            LoadingItem()
        } else {
            LazyColumn(state = listState) {
                items(
                    count = entities.itemCount,
                ) { index ->
                    val entity = entities[index] ?: return@items
                    val selected = uiState.selectedItems.contains(entity)
                    val avatar = FioriAvatarConstruct(
                        hasBadge = false,
                        type = FioriAvatarType.SINGLE,
                        avatarList = listOf(
                            if (!selected) {
                                FioriAvatarData(
                                    text = viewModel.getAvatarText(entity).uppercase(),
                                    textColor = MaterialTheme.fioriHorizonAttributes.SapFioriColorBaseText
                                )
                            } else FioriAvatarData(
                                FioriImage(resId = R.drawable.ic_sap_icon_done),
                                color = MaterialTheme.fioriHorizonAttributes.SapFioriColorHeaderCaption,
                                size = 40.dp,
                            )
                        ),
                        size = 40.dp,
                        shape = FioriAvatarShape.CIRCLE,
//                      backgroundColor = MaterialTheme.fioriHorizonAttributes.SapFioriColorS6
                    )
                    val objectCellData = FioriObjectCellData.Builder().apply {
                        setHeadline(viewModel.getEntityTitle(entity))
                        setIconStack(listOf(
                            IconStackElement(viewModel.getAvatarText(entity).uppercase()),
                        ))
                        setSubheadline("Subtitle goes here")
                        setFootnote("caption display")
                        setAvatar(avatar)
                    }.build()
                    objectCellData.setDisplayReadIndicator(false)

                    FioriObjectCell(
                        cellData = objectCellData,
                        colors = FioriObjectCellDefaults.colors(),
                        textStyles = FioriObjectCellDefaults.textStyles(),
                        styles = FioriObjectCellDefaults.styles(iconStackSize = 10.dp),
                        onClick = { onClickChange(entity) },
                        onLongPress = if (uiState.entityOperationType != EntityOperationType.DETAIL) {
                              { viewModel.onSelectAction(entity) }
                          } else null
                    )
                }
            }
        }
    }
}
