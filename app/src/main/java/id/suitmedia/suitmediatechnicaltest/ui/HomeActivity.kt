package id.suitmedia.suitmediatechnicaltest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.suitmedia.suitmediatechnicaltest.R
import id.suitmedia.suitmediatechnicaltest.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customToolbar()
        setupUI()
    }

    private fun customToolbar() {
        binding.toolbar.apply {
            navBack.setOnClickListener {
                onBackPressed()
            }
            tvToolbarName.text = getString(R.string.second_screen)
        }
    }

    private fun setupUI() {
        getName()
        binding.apply {
            btnCheck.setOnClickListener {
                val intent = Intent(this@HomeActivity, UserListActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun getName() {
        val name = intent.getStringExtra("name")
        binding.tvName.text = name
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}