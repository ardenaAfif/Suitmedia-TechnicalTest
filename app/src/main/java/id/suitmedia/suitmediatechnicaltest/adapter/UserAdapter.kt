package id.suitmedia.suitmediatechnicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.suitmedia.suitmediatechnicaltest.data.reoi.DataItem
import id.suitmedia.suitmediatechnicaltest.databinding.ItemUserBinding
import id.suitmedia.suitmediatechnicaltest.utils.FormatHelper.setImageFromUrl

class UserAdapter(private val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    private var onItemClickListener: ((DataItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (DataItem) -> Unit) {
        onItemClickListener = listener
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.apply {
                tvName.text = user.firstName + " " + user.lastName
                tvEmail.text = user.email
                ivProfile.setImageFromUrl(context, user.avatar.toString())
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val userList = differ.currentList[position]
        holder.bind(userList)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(userList) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}