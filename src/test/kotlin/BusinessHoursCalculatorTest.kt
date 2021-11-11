import com.samplecode.Business
import com.samplecode.BusinessHoursCalculator
import com.samplecode.DateTimeFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class BusinessHoursCalculatorTest {

    var business = Business()
    var calculator = BusinessHoursCalculator(business)

    @Test
    fun `saturday 1201 to sunday 1159 yields zero hours`() {
        val sat = DateTimeFormat.fromIsoString("2021-11-06T00:01:00.00Z")
        val sun = DateTimeFormat.fromIsoString("2021-11-07T23:59:00.00Z")

        val numberOfHours = calculator.numberOfHoursBetweenDateTimes(sat, sun)

        assertEquals(0, numberOfHours)

        return
    }

    @Test
    fun `monday 1000 to tuesday 1000 yields eight hours`() {
        val mon = DateTimeFormat.fromIsoString("2021-11-08T10:00:00.00Z")
        val tue = DateTimeFormat.fromIsoString("2021-11-09T10:00:00.00Z")

        val numberOfHours = calculator.numberOfHoursBetweenDateTimes(mon, tue)

        assertEquals(8, numberOfHours)

        return
    }

    @Test
    fun `monday 0600 to friday 2300 yields forty hours`() {
        val mon = DateTimeFormat.fromIsoString("2021-11-08T06:00:00.00Z")
        val fri = DateTimeFormat.fromIsoString("2021-11-12T23:00:00.00Z")

        val numberOfHours = calculator.numberOfHoursBetweenDateTimes(mon, fri)

        assertEquals(40, numberOfHours)

        return
    }


    @Test
    fun `sunday 0600 to saturday 2300 yields forty hours`() {
        val sun = DateTimeFormat.fromIsoString("2021-11-07T06:00:00.00Z")
        val sat = DateTimeFormat.fromIsoString("2021-11-13T23:00:00.00Z")

        val numberOfHours = calculator.numberOfHoursBetweenDateTimes(sun, sat)

        assertEquals(40, numberOfHours)

        return
    }

    @Test
    fun `start time after end time yields zero hours`() {
        val sun = DateTimeFormat.fromIsoString("2021-11-07T06:00:00.00Z")
        val sat = DateTimeFormat.fromIsoString("2021-11-13T23:00:00.00Z")

        val numberOfHours = calculator.numberOfHoursBetweenDateTimes(sat, sun)

        assertEquals(0, numberOfHours)

        return

    }
}