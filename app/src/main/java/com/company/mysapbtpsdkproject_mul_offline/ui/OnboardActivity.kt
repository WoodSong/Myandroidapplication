package com.company.mysapbtpsdkproject_mul_offline.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.company.mysapbtpsdkproject_mul_offline.R
import com.company.mysapbtpsdkproject_mul_offline.app.SAPWizardApplication
import com.company.mysapbtpsdkproject_mul_offline.app.WizardFlowStateListener
import com.sap.cloud.mobile.flows.compose.core.FlowContext
import com.sap.cloud.mobile.flows.compose.core.FlowContextRegistry.flowContext
import com.sap.cloud.mobile.flows.compose.ext.FlowActionHandler
import com.sap.cloud.mobile.flows.compose.ext.FlowOptions
import com.sap.cloud.mobile.flows.compose.flows.FlowUtil
import com.sap.cloud.mobile.flows.compose.ui.FlowComposeTheme
import com.sap.cloud.mobile.foundation.authentication.OAuth2PasswordProcessor
import com.sap.cloud.mobile.foundation.model.AppConfig
import com.sap.cloud.mobile.foundation.model.OAuth
import com.sap.cloud.mobile.foundation.model.OAuthClientPassword
import com.sap.cloud.mobile.foundation.user.DeviceUserManager
import com.sap.cloud.mobile.onboarding.compose.screens.LoadingScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

/**
 * Activity that accepts navigation from app link or deep link,
 * and finishes cross context authentication.
 */
class OnboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlowComposeTheme {
                LoadingScreen(R.string.authentication_progress)
            }
        }

        val uri = intent.data
        if (uri != null) {
            lifecycleScope.launch(Dispatchers.Default) {
                val appConfig = AppConfig.createAppConfigFromString(uri.toString(), null)
                if (DeviceUserManager(this@OnboardActivity).getOnBoardedUsers().isNotEmpty()) {
                    consumeOAuth2Passcode(appConfig)
                    launchWelcomeActivity(this@OnboardActivity)
                } else {
                    val flowContext = FlowContext(
                        appConfig = appConfig,
                        flowStateListener = WizardFlowStateListener(application as SAPWizardApplication),
                        flowActionHandler = FlowActionHandler(),
                        flowOptions = FlowOptions()
                    )

                    FlowUtil.startFlow(this@OnboardActivity, flowContext) { resultCode, _ ->
                        if (resultCode == RESULT_CANCELED) {
                            launchWelcomeActivity(this@OnboardActivity)
                        } else if (resultCode == RESULT_OK) {
                            launchMainBusinessActivity(this@OnboardActivity)
                        }
                    }
                }
            }
        } else {
            launchWelcomeActivity(this@OnboardActivity)
        }
    }

    /**
     * Consumes the OAuth2 one-time passcode for safety.
     *
     * @param appConfig the AppConfig object
     */
    private fun consumeOAuth2Passcode(appConfig: AppConfig) {
        val auth = appConfig.primaryAuthenticationConfig
        if (auth is OAuth) {
            val oAuthConfig = auth.config
            val oAuthClient = flowContext.flowActionHandler.getEffectiveOAuthClient(oAuthConfig)
            if (oAuthClient is OAuthClientPassword) {
                val oAuth2Processor =
                    OAuth2PasswordProcessor(oAuthConfig, oAuthClient, OkHttpClient())
                oAuth2Processor.authenticate()
            }
        }
    }
}
