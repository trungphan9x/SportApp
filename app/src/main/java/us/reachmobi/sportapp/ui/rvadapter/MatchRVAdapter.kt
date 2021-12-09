package us.reachmobi.sportapp.ui.rvadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import us.reachmobi.sportapp.databinding.ItemMatchBinding
import us.reachmobi.sportapp.data.model.Event


class MatchRVAdapter(private var events: List<Event>) : RecyclerView.Adapter<MatchRVAdapter.ViewHolder>() {

    private var onItemClickListener: ((Int, Event) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Int, Event) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.event = events[position]
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(position, events[position])
        }
    }

    override fun getItemCount() = events.size

    inner class ViewHolder(val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(list: List<Event>) {
        events = list
        notifyDataSetChanged()
    }
}