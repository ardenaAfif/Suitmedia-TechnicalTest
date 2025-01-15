package id.suitmedia.suitmediatechnicaltest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.suitmedia.suitmediatechnicaltest.databinding.ActivityLoginBinding
import id.suitmedia.suitmediatechnicaltest.utils.PalindromeChecker

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPalindromeChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {

        binding.apply {
            btnCheck.setOnClickListener {
                val name = etName.text.toString()
                val sentence = etPalindrome.text.toString()

                if (name.isEmpty() || sentence.isEmpty()) {
                    Toast.makeText(this@LoginActivity, "Fill the blank", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val isPalindrome = PalindromeChecker.isPalindrome(sentence)
                showPalindromeDialog(isPalindrome)
                this@LoginActivity.isPalindromeChecked = true
                btnNext.isEnabled = isPalindrome
            }

            btnNext.setOnClickListener {
                if (isPalindromeChecked) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.putExtra("name", etName.text.toString())
                    startActivity(intent)
                }
            }
        }
    }

    private fun showPalindromeDialog(isPalindrome: Boolean) {
        val message = if (isPalindrome) "isPalindrome" else "not palindrome"
        val builder = AlertDialog.Builder(this)
            .setTitle("Result")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        resetUI()
    }

    private fun resetUI() {
        binding.apply {
            etName.text.clear()
            etPalindrome.text.clear()
            btnNext.isEnabled = false
            isPalindromeChecked = false
        }
    }
}