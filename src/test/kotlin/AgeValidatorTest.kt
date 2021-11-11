import com.samplecode.AgeValidator
import com.samplecode.DateTimeFormat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AgeValidatorTest {

    @Test
    fun `born on jan 1 1980 is at least 21 on jan 1 2020`() {
        val validator = AgeValidator(21, DateTimeFormat.fromIsoString("2020-01-01T00:00:00.00Z"))
        val isOfAge = validator.isOfAge("1980-01-01")
        assertTrue(isOfAge)
        return
    }

    @Test
    fun `born on dec 31 1999 is at least 21 on jan 1 2021`() {
        val validator = AgeValidator(21, DateTimeFormat.fromIsoString("2021-01-01T00:00:00.00Z"))
        val isOfAge = validator.isOfAge("1999-12-31")
        assertTrue(isOfAge)
        return
    }

    @Test
    fun `born on jan 1 2000 is at least 21 on jan 1 2021`() {
        val validator = AgeValidator(21, DateTimeFormat.fromIsoString("2021-01-01T00:00:00.00Z"))
        val isOfAge = validator.isOfAge("2000-01-01")
        assertTrue(isOfAge)
        return
    }

    @Test
    fun `born on jan 2 2000 is not at least 21 on jan 1 2021`() {
        val validator = AgeValidator(21, DateTimeFormat.fromIsoString("2021-01-01T00:00:00.00Z"))
        val isOfAge = validator.isOfAge("2000-01-02")
        assertFalse(isOfAge)
        return
    }



    @Test
    fun `born in the future is not at least 21`() {
        val validator = AgeValidator(21)
        val isOfAge = validator.isOfAge("3000-01-02")
        assertFalse(isOfAge)
        return
    }
}