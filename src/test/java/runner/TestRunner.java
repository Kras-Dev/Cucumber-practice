package runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;
// Аннотация @Suite указывает, что этот класс является набором тестов
@Suite
// Аннотация @IncludeEngines определяет, какие движки тестирования будут использоваться, в данном случае - Cucumber
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
// Аннотация @ConfigurationParameters позволяет задать параметры конфигурации для выполнения тестов
@ConfigurationParameters({
        // Установка фильтра тегов, чтобы выполнять только тесты с тегом @tag_name
        @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@all"),
        // Установка пути к каталогам с тестовыми сценариями (файлы .feature)
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features"),
        // Установка пути к классам с шагами (step definitions) для Cucumber
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "definitions"),
        // Установка плагина для интеграции с Allure и параметра "pretty" для вывода информации
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, pretty")
})

public class TestRunner {
}
