package us.reachmobi.sportapp.ui.rvadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import us.reachmobi.sportapp.databinding.ItemLeagueBinding
import us.reachmobi.sportapp.data.model.League


class LeagueRVAdapter() : ListAdapter<League, LeagueRVAdapter.ViewHolder>(DiffCallback) {

    private var onItemClickListener: ((Int, League) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Int, League) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.league = currentList[position]
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(position, currentList[position])
        }
    }

    override fun getItemCount() = currentList.size

    inner class ViewHolder(val binding: ItemLeagueBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<League>() {
        override fun areItemsTheSame(oldItem: League, newItem: League) = oldItem.idLeague == newItem.idLeague
        override fun areContentsTheSame(oldItem: League, newItem: League) = oldItem == newItem
    }
}