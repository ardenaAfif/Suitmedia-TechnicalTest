package id.suitmedia.suitmediatechnicaltest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.suitmedia.suitmediatechnicaltest.R
import id.suitmedia.suitmediatechnicaltest.data.reoi.DataItem
import id.suitmedia.suitmediatechnicaltest.databinding.ActivityHomeBinding
import id.suitmedia.suitmediatechnicaltest.utils.FormatHelper.setImageFromUrl

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
            btnChooseUser.setOnClickListener {
                val intent = Intent(this@HomeActivity, UserListActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_USER_SELECTION)
            }

        }
    }

    private fun getName() {
        val name = intent.getStringExtra("name")
        binding.tvName.text = name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_USER_SELECTION && resultCode == RESULT_OK) {
            val selectedUser = data?.getParcelableExtra<DataItem>("SELECTED_USER")
            selectedUser?.let {
                showSelectedUser(it)
            }
        }
    }

    private fun showSelectedUser(user: DataItem) {
        binding.apply {
            cardSelected.visibility = View.VISIBLE
            tvSelectedUser.visibility = View.GONE

            ivProfileSelected.setImageFromUrl(this@HomeActivity, user.avatar.toString())
            tvUserNameSelected.text = "${user.firstName} ${user.lastName}"
            tvEmailSelected.text = user.email
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        private const val REQUEST_CODE_USER_SELECTION = 1
    }
}