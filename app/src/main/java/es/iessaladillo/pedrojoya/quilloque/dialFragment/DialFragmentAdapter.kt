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
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable

class DialFragmentAdapter : RecyclerView.Adapter<DialFragmentAdapter.ViewHolder>() {

    private var data: List<Contact> = emptyList()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.contacts_fragment_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): Contact = data[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: Contact = data[position]
        holder.bind(contact)
    }

    fun submitList(newList: List<Contact>) {
        data = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ((Int) -> Unit)?){
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View, onItemClickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val lblName: TextView = itemView.findViewById(R.id.lblName)
        private val lblPhoneNumber: TextView = itemView.findViewById(R.id.lblPhoneNumber)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(contact: Contact) {
            contact.run {
                imgAvatar.setImageDrawable(createAvatarDrawable(contact.name))
                lblName.text = contact.name
                lblPhoneNumber.text = contact.phoneNumber
            }
        }

    }

}