package us.reachmobi.sportapp.ui.rvadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import us.reachmobi.sportapp.databinding.ItemTeamBinding
import us.reachmobi.sportapp.data.model.Team


class TeamRVAdapter() : ListAdapter<Team, TeamRVAdapter.ViewHolder>(DiffCallback) {

    private var onItemClickListener: ((Int, Team) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Int, Team) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.team = currentList[position]
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(position, currentList[position])
        }
    }

    override fun getItemCount() = currentList.size

    inner class ViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.idTeam == newItem.idTeam
        override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
    }
}