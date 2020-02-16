/*Pseudocode
Variables:
startFullMoon month/day/year, current month/day/year, formatted dates,
days to first/last/next full moon, dates of last full moon

Process:
Initialize the first full moon of the year as an integer, then format to a date
Initialize the current date as an integer, then format as a date
Calculate the days in between the first full moon and the current date.
Divide these days up by the average time between full moons
Subtract the current date by the time between moons to find the date of the last full moon.
Add a month to the last full moon date
Print the dates for the next and last full moons.
*/

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


fun main() {
    //Initialize first full moon as integers
    val startFullMoonMonth = 1
    val startFullMoonDay = 10
    val startFullMoonYear = 2020

    //Initialize current date as integers
    val currentDay = LocalDate.now().dayOfMonth
    val currentMonth = LocalDate.now().monthValue
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    //Set the format that will be used for all dates
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

    //Convert the current date to a date and format it
    val currentDate = LocalDate.of(currentYear, currentMonth, currentDay)
    val currentDateFormatted = currentDate.format(formatter)

    //Convert the first full moon to a date and format it
    val startFullMoonDate = LocalDate.of(startFullMoonYear, startFullMoonMonth, startFullMoonDay)
    val startFullMoonDateFormatted = startFullMoonDate.format(formatter)

    //Calculate the days/months to the first full moon, then calculate the days/months in between
    val daysToFirstMoon = currentDate.until(startFullMoonDate, ChronoUnit.DAYS)
    val monthsToLastMoon = ((daysToFirstMoon.absoluteValue) / 29.5).roundToInt()
    val daysToLastMoon = ((((daysToFirstMoon.absoluteValue) / 29.5) - monthsToLastMoon) * 29).roundToInt()

    //Convert the days/months since the last full moon to a date for the last full moon
    var LastMoonDate = currentDate
    LastMoonDate = LastMoonDate.minusMonths(monthsToLastMoon.toLong())
    LastMoonDate = LastMoonDate.minusDays(daysToLastMoon.toLong())

    //Calculate the date of the next full moon by adding a month to the last full moon
    val NextMoonDate = LastMoonDate.plusMonths(1)

    println("Today is $currentDateFormatted")
    println("Last Full Moon: $LastMoonDate Next Full Moon: $NextMoonDate")
}