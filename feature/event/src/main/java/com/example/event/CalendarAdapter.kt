package com.example.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.entities.Calendar
import com.example.event.databinding.CalendarItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarAdapter : ListAdapter<Calendar, CalendarAdapter.CalendarViewHolder>(NoteDiffCheck()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = CalendarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

    override fun submitList(list: List<Calendar>?) {
        val sortedList = list?.sortedByDescending { it.beginTime }
        super.submitList(sortedList)
    }

    inner class CalendarViewHolder(private val calendarItemBinding: CalendarItemBinding) : ViewHolder(calendarItemBinding.root) {
        fun bindData(calendar: Calendar) {
            calendarItemBinding.noteTitle.text = calendar.name
            val formattedTime = formatTime(calendar.beginTime, calendar.endTime)
            calendarItemBinding.noteTime.text = formattedTime
        }

        private fun formatTime(beginTime: Long?, endTime: Long?): String {
            val beginTimeString = beginTime?.let { formatTimeToString(it, endTime) } ?: "N/A"
            return beginTimeString
        }

        private fun formatTimeToString(beginTime: Long?, endTime: Long?): String {
            val beginDate = Date(beginTime ?: 0)
            val endDate = Date(endTime ?: 0)

            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

            val beginDateString = dateFormat.format(beginDate)
            val endDateString = dateFormat.format(endDate)
            val beginTimeString = timeFormat.format(beginDate)
            val endTimeString = timeFormat.format(endDate)

            val datePart = if (beginDateString == endDateString) {
                beginDateString
            } else {
                "$beginDateString - $endDateString"
            }

            return "$datePart / $beginTimeString - $endTimeString"
        }

    }

    private class NoteDiffCheck : DiffUtil.ItemCallback<Calendar>() {
        override fun areItemsTheSame(oldItem: Calendar, newItem: Calendar): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Calendar, newItem: Calendar): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
