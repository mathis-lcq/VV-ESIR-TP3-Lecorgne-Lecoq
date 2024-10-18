package fr.istic.vv;
import java.util.Objects;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Date Invalide.");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
if (month < 1 || month > 12 || day < 1 || year < 0) {
            return false;
        }

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (month == 2 && isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        return day <= daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        int newDay = this.day;
        int newMonth = this.month;
        int newYear = this.year;

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(this.year)) {
            daysInMonth[1] = 29;
        }

        newDay++;
        if (newDay > daysInMonth[this.month - 1]) {
            newDay = 1;
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }

        return new Date(newDay, newMonth, newYear);
    }

    public Date previousDate() {
        int newDay = this.day;
        int newMonth = this.month;
        int newYear = this.year;

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(this.year)) {
            daysInMonth[1] = 29;
        }

        newDay--;
        if (newDay < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newDay = daysInMonth[newMonth - 1];
        }

        return new Date(newDay, newMonth, newYear);
    }

    @Override
    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("Other ne peut pas être null.");
        }

        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date date = (Date) obj;
        return day == date.day && month == date.month && year == date.year;
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
