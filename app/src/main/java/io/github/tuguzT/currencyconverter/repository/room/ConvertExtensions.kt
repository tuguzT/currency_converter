package io.github.tuguzT.currencyconverter.repository.room

import io.github.tuguzT.currencyconverter.model.SupportedCode
import io.github.tuguzT.currencyconverter.repository.net.model.LatestDataResult
import io.github.tuguzT.currencyconverter.repository.room.dto.ConversionRateDto
import io.github.tuguzT.currencyconverter.repository.room.dto.SupportedCodeDto
import java.util.*

fun SupportedCodeDto.toModel(): SupportedCode = SupportedCode(code, name)

fun SupportedCode.toDto(): SupportedCodeDto = SupportedCodeDto(code, name)

fun LatestDataResult.toDto(targetCode: String): ConversionRateDto {
    val lastUpdate = Date(lastUpdateUnix)
    val nextUpdate = Date(nextUpdateUnix)
    val rate = checkNotNull(conversionRates[targetCode])
    return ConversionRateDto(baseCode, targetCode, lastUpdate, nextUpdate, rate)
}