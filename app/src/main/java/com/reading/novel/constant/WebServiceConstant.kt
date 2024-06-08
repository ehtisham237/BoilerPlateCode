package com.reading.novel.constant

import com.enbridgegas.meterapp.BuildConfig

object WebServiceConstant {

    val API_BASE_URL: String = BuildConfig.API_BASE_URL

    /***************************Server Endpoints**********************************************/

    //Contract Endpoint
    const val CONTRACT_INFO_URL = "CPI/http/contract"
    const val PAP_URL = "CPI/http/pap"
    const val YEARLY_READING_URL = "CPI/http/yearlyreading"
    const val SUBMIT_READING_URL = "CPI/http/reading"
    const val METER_BILL_URL = "CPI/http/meterbill"
    const val MFA_STATUS_URL = "CPI/http/mfa_status"
    const val MFA_ACTIVE_URL = "CPI/http/mfa_active"
    const val MFA_DEACTIVATE_URL = "CPI/http/mfa_deactive"
    const val USER_CONTRACT_URL = "CPI/http/user_contracts"
    const val METER_APP_READING_URL = "CPI/http/meterappreading"

    const val IMAGE_ANALYZE_URL =
        "https://enbzcc-upo-de-ais-egmaapp.cognitiveservices.azure.com/computervision/imageanalysis:analyze?api-version=2023-04-01-preview&features=read&language=en&gender-neutral-caption=False"
}