package fr.tse.jacademie.kotlinDemo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KotlinDemoApplicationConfig {
    /**
     * With this declaration you will able to inject logger in your Spring beans
     */
    @Bean
    fun logger(injectionPoint: InjectionPoint): Logger =
            LoggerFactory.getLogger(
                    injectionPoint.methodParameter?.containingClass
                            ?: injectionPoint.field?.declaringClass)
}
