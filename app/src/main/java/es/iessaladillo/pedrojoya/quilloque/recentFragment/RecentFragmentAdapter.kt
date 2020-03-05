package es.iessaladillo.pedrojoya.quilloque.recentFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.SharedViewModel
import es.iessaladillo.pedrojoya.quilloque.SharedViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository
import es.iessaladillo.pedrojoya.quilloque.room.RecentCalls
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable

class RecentFragmentAdapter : RecyclerView.Adapter<RecentFragmentAdapter.ViewHolder>() {

    private var data: List<RecentCalls> = emptyList()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recent_fragment_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): RecentCalls = data[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recentCalls: RecentCalls = data[position]
        holder.bind(recentCalls)
    }

    fun submitList(newList: List<RecentCalls>) {
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
        private val ImgCallType: ImageView = itemView.findViewById(R.id.imgCallType)
        private val lblDate: TextView = itemView.findViewById(R.id.lblDate)
        private val lblTime: TextView = itemView.findViewById(R.id.lblTime)
        private val lblCreateContact: TextView = itemView.findViewById(R.id.lblCreateContact)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(recentCalls: RecentCalls) {
            recentCalls.run {
                if (recentCalls.contactName == null) {
                    imgAvatar.setImageDrawable(createAvatarDrawable("?"))
                    lblName.text = recentCalls.phoneNumber
                    lblPhoneNumber.visibility = View.INVISIBLE
                    lblCreateContact.visibility = View.VISIBLE
                }
                else {
                    imgAvatar.setImageDrawable(createAvatarDrawable(recentCalls.contactName))
                    lblName.text = recentCalls.contactName
                    lblPhoneNumber.text = recentCalls.phoneNumber
                    lblPhoneNumber.visibility = View.VISIBLE
                    lblCreateContact.visibility = View.GONE
                }
                ImgCallType.setImageResource(recentCalls.type)
                lblDate.text = recentCalls.date
                lblTime.text = recentCalls.time
            }
        }

    }

}