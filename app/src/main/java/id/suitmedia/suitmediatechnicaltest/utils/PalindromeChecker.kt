package id.suitmedia.suitmediatechnicaltest.utils

object PalindromeChecker {

    fun isPalindrome(sentece: String): Boolean {
        val cleanString = sentece.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanString == cleanString.reversed()
    }
}