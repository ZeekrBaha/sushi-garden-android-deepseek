package com.baha.sushigarden

import com.baha.sushigarden.data.services.delivery.FieldValidators
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FieldValidatorsTest {
    @Test
    fun `valid email returns true`() {
        assertTrue(FieldValidators.isValidEmail("test@example.com"))
    }

    @Test
    fun `invalid email returns false`() {
        assertFalse(FieldValidators.isValidEmail("notanemail"))
    }

    @Test
    fun `valid password returns true`() {
        assertTrue(FieldValidators.isValidPassword("abcdef"))
    }

    @Test
    fun `short password returns false`() {
        assertFalse(FieldValidators.isValidPassword("12345"))
    }

    @Test
    fun `nonEmpty returns true for non-empty`() {
        assertTrue(FieldValidators.isNonEmpty("hello"))
    }

    @Test
    fun `nonEmpty returns false for empty`() {
        assertFalse(FieldValidators.isNonEmpty(""))
    }
}
