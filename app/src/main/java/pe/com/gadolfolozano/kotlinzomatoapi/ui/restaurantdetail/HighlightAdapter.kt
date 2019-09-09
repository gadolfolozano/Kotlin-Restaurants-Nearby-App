package pe.com.gadolfolozano.kotlinzomatoapi.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.gadolfolozano.kotlinzomatoapi.databinding.ItemHighlightBinding

class HighlightAdapter(private val dataSet: List<String>?) : RecyclerView.Adapter<HighlightAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHighlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val highlightName = dataSet!![holder.adapterPosition]
        holder.bind(highlightName)
    }

    class MyViewHolder constructor(private val binding: ItemHighlightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(highlightName: String) {
            binding.tvHighlightsName.text = highlightName
        }
    }
}
