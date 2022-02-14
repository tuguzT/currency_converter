package io.github.tuguzT.currencyconverter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.github.tuguzT.currencyconverter.model.repository.PairConversionResult
import io.github.tuguzT.currencyconverter.model.repository.SupportedCode
import io.github.tuguzT.currencyconverter.model.repository.parse
import io.github.tuguzT.currencyconverter.repository.ExchangeRateAPI
import retrofit2.await

class MainActivityModel(private val exchangeRateAPI: ExchangeRateAPI) : ViewModel() {
    private var supportedCodes: List<SupportedCode>? = null

    var baseCode: SupportedCode? = null
    var targetCode: SupportedCode? = null

    suspend fun convert(amount: Double): PairConversionResult {
        val base = checkNotNull(baseCode).code
        val target = checkNotNull(targetCode).code
        return exchangeRateAPI.pairConversion(base, target, amount).await()
    }

    suspend fun getSupportedCodes(): List<SupportedCode> = supportedCodes ?: updateSupportedCodes()

    suspend fun updateSupportedCodes(): List<SupportedCode> {
        val result = exchangeRateAPI.supportedCodes().await().parse()
        supportedCodes = result
        return result
    }
}
