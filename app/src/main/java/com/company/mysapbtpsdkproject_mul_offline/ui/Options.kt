package com.company.mysapbtpsdkproject_mul_offline.ui

import com.company.mysapbtpsdkproject_mul_offline.R
import com.sap.cloud.mobile.flows.compose.ext.FlowOptions
import com.sap.cloud.mobile.flows.compose.ext.OAuthOption
import com.sap.cloud.mobile.onboarding.compose.settings.CustomScreenSettings
import com.sap.cloud.mobile.onboarding.compose.settings.LaunchScreenContentSettings
import com.sap.cloud.mobile.onboarding.compose.settings.LaunchScreenSettings
import com.sap.cloud.mobile.onboarding.compose.settings.QRCodeReaderScreenSettings
//This file will be replaced by code generation, do not modify it
//██╗  ██╗ █████╗ ███╗   ██╗██████╗ ███████╗     ██████╗ ███████╗███████╗██╗██╗██╗
//██║  ██║██╔══██╗████╗  ██║██╔══██╗██╔════╝    ██╔═══██╗██╔════╝██╔════╝██║██║██║
//███████║███████║██╔██╗ ██║██║  ██║███████╗    ██║   ██║█████╗  █████╗  ██║██║██║
//██╔══██║██╔══██║██║╚██╗██║██║  ██║╚════██║    ██║   ██║██╔══╝  ██╔══╝  ╚═╝╚═╝╚═╝
//██║  ██║██║  ██║██║ ╚████║██████╔╝███████║    ╚██████╔╝██║     ██║     ██╗██╗██╗
//╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝ ╚══════╝     ╚═════╝ ╚═╝     ╚═╝     ╚═╝╚═╝╚═╝

class Options {

    companion object {
        val flowOptions = FlowOptions(
  //      oAuthAuthenticationOption = OAuth2WebOption.WEB_VIEW,
          useDefaultEulaScreen = false,
          screenSettings = CustomScreenSettings(
               launchScreenSettings = LaunchScreenSettings(
                   titleResId = R.string.application_name,
                   contentSettings = LaunchScreenContentSettings(
                       contentImage = R.drawable.ic_sap_icon_sdk_transparent,
                       title = R.string.launch_screen_content_title,
                       content = R.string.launch_screen_content_body,
                   ),
                   bottomPrivacyUrl = "http://www.sap.com"
               ),
               qrCodeReaderScreenSettings = QRCodeReaderScreenSettings(
                   scanInternal = 50L
               )
           ),
          fullScreen = false
      )
    }
}