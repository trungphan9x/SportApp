package us.reachmobi.sportapp.ui.rvadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import us.reachmobi.sportapp.data.model.Player
import us.reachmobi.sportapp.databinding.ItemPlayerBinding


class PlayerRVAdapter() : ListAdapter<Player, PlayerRVAdapter.ViewHolder>(DiffCallback) {

    private var onItemClickListener: ((Int, Player) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Int, Player) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.player = currentList[position]
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(position, currentList[position])
        }
    }

    override fun getItemCount() = currentList.size

    inner class ViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player) = oldItem.idPlayer == newItem.idPlayer
        override fun areContentsTheSame(oldItem: Player, newItem: Player) = oldItem == newItem
    }
}