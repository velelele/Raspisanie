package ru.thever4.lessons.lesson4_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import ru.thever4.lessons.lesson4_2.databinding.ChatItemLayoutBinding
import java.text.SimpleDateFormat

class ChatsAdapter : RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    private val list: MutableList<ChatItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemLayoutBinding.inflate(inflater, parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<ChatItem>) {
        this.list.addAll(list)
    }

    class ChatViewHolder(
        private val binding: ChatItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem) = with(binding) {
            chatUsername.text = item.userName
            chatMessagePreview.text = item.message
            chatMessageCounter.text = item.messagesCount.toString()
            chatMessageTime.text = timestampToString(item.receivedTime)
            chatAvatar.load(item.userAvatar) {
                placeholder(R.drawable.no_avatar)
                memoryCachePolicy(CachePolicy.DISABLED)
            }
        }

        private fun timestampToString(timestamp: Long) =
            formatter.format(timestamp * SECOND_IN_MS)


        companion object {
            private const val TIME_FORMAT = "HH:mm"
            private const val SECOND_IN_MS = 1000L
            private val formatter = SimpleDateFormat(TIME_FORMAT)
        }
    }
}