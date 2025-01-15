package id.suitmedia.suitmediatechnicaltest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.suitmedia.suitmediatechnicaltest.R
import id.suitmedia.suitmediatechnicaltest.adapter.UserAdapter
import id.suitmedia.suitmediatechnicaltest.databinding.ActivityUserListBinding
import id.suitmedia.suitmediatechnicaltest.viewmodel.UserViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var userAdapter: UserAdapter
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customToolbar()
        setupRv()
        observeData()
    }

    private fun observeData() {
        viewModel.userResponse.observe(this, Observer { userResponse ->
            userResponse?.data?.let { userList ->
                userAdapter.differ.submitList(userList)
            }
        })
    }

    private fun customToolbar() {
        binding.toolbar.apply {
            navBack.setOnClickListener { onBackPressed() }
            tvToolbarName.text = getString(R.string.third_screen)
        }
    }

    private fun setupRv() {
        userAdapter = UserAdapter(this)
        userAdapter.setOnItemClickListener { user ->
            val intent = Intent()
            intent.putExtra("SELECTED_USER", user)
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.rvUser.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@UserListActivity)
            setHasFixedSize(true)
        }
    }
}