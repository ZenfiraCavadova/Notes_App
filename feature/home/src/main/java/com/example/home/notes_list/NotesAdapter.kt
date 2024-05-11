import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.entities.Note
import com.example.home.databinding.NotesItemBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NotesAdapter : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NoteDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

    inner class NotesViewHolder(private val notesItemBinding: NotesItemBinding) : ViewHolder(notesItemBinding.root) {
        fun bindData(note: Note) {
            notesItemBinding.noteTitle.text = note.title
            notesItemBinding.noteDescription.text = note.description
            notesItemBinding.date.text = formatDate(note.creationDate)
        }

        @SuppressLint("SimpleDateFormat")
        private fun formatDate(timestamp: Long): String {
            val calendar = Calendar.getInstance()
            val now = calendar.timeInMillis
            calendar.timeInMillis = timestamp

            val sdf = when {
                isToday(calendar, now) -> SimpleDateFormat("'Today at' HH:mm", Locale.getDefault())
                isYesterday(calendar, now) -> SimpleDateFormat("'Yesterday at' HH:mm", Locale.getDefault())
                isTomorrow(calendar, now) -> SimpleDateFormat("'Tomorrow at' HH:mm", Locale.getDefault())
                else -> SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault())
            }

            return sdf.format(Date(timestamp))
        }

        private fun isToday(calendar: Calendar, now: Long): Boolean {
            val todayCalendar = Calendar.getInstance()
            todayCalendar.timeInMillis = now
            return calendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR) &&
                    calendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH) &&
                    calendar.get(Calendar.DAY_OF_MONTH) == todayCalendar.get(Calendar.DAY_OF_MONTH)
        }

        private fun isYesterday(calendar: Calendar, now: Long): Boolean {
            val yesterdayCalendar = Calendar.getInstance()
            yesterdayCalendar.timeInMillis = now - (24 * 60 * 60 * 1000)
            return calendar.get(Calendar.YEAR) == yesterdayCalendar.get(Calendar.YEAR) &&
                    calendar.get(Calendar.MONTH) == yesterdayCalendar.get(Calendar.MONTH) &&
                    calendar.get(Calendar.DAY_OF_MONTH) == yesterdayCalendar.get(Calendar.DAY_OF_MONTH)
        }

        private fun isTomorrow(calendar: Calendar, now: Long): Boolean {
            val tomorrowCalendar = Calendar.getInstance()
            tomorrowCalendar.timeInMillis = now + (24 * 60 * 60 * 1000)
            return calendar.get(Calendar.YEAR) == tomorrowCalendar.get(Calendar.YEAR) &&
                    calendar.get(Calendar.MONTH) == tomorrowCalendar.get(Calendar.MONTH) &&
                    calendar.get(Calendar.DAY_OF_MONTH) == tomorrowCalendar.get(Calendar.DAY_OF_MONTH)
        }
    }

    private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }
}
