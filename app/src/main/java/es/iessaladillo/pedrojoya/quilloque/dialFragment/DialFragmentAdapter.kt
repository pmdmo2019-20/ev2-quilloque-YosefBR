package es.iessaladillo.pedrojoya.quilloque.dialFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.SharedViewModel
import es.iessaladillo.pedrojoya.quilloque.SharedViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository
import es.iessaladillo.pedrojoya.quilloque.room.SuggestedCall
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable

class DialFragmentAdapter : RecyclerView.Adapter<DialFragmentAdapter.ViewHolder>() {

    private var data: List<SuggestedCall> = emptyList()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.dial_fragment_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): SuggestedCall = data[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestedCall: SuggestedCall = data[position]
        holder.bind(suggestedCall)
    }

    fun submitList(newList: List<SuggestedCall>) {
        data = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ((Int) -> Unit)?){
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View, onItemClickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val lblContactName: TextView = itemView.findViewById(R.id.lblContactName)
        private val lblPhoneNumber: TextView = itemView.findViewById(R.id.lblPhoneNumber)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(suggestedCall: SuggestedCall) {
            suggestedCall.run {
                lblContactName.text = suggestedCall.contactName
                imgAvatar.setImageDrawable(createAvatarDrawable(lblContactName.text.toString()))
                lblPhoneNumber.text = suggestedCall.phoneNumber
            }
        }

    }

}