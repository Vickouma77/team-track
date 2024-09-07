package com.secure_auth.pact_cdc_demo.contractTest


import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider
import com.secure_auth.pact_cdc_demo.UserController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc


@WebMvcTest(UserController::class)
@Provider("PactTestProvider")
@PactBroker(url = "http://localhost:9292")
class PactProviderTest {

    @Autowired
    private lateinit var mockMvc: MockMvc


    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }


    @BeforeEach
    fun before(context: PactVerificationContext) {
        context.target = MockMvcTestTarget(mockMvc)
    }
}