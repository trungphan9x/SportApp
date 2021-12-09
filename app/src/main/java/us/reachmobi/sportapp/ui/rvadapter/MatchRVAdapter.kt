package us.reachmobi.sportapp.ui.rvadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import us.reachmobi.sportapp.databinding.ItemMatchBinding
import us.reachmobi.sportapp.data.model.Event


class MatchRVAdapter() : ListAdapter<Event, MatchRVAdapter.ViewHolder>(DiffCallback) {

    private var onItemClickListener: ((Int, Event) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Int, Event) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.event = currentList[position]
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(position, currentList[position])
        }
    }

    override fun getItemCount() = currentList.size

    inner class ViewHolder(val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.idEvent == newItem.idEvent
        override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
    }
}