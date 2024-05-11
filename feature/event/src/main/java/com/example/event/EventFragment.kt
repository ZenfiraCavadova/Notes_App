package com.example.event

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.core.BaseFragment
import com.example.domain.entities.Calendar
import com.example.event.databinding.FragmentEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : BaseFragment<FragmentEventBinding,EventViewModel,EventState,EventEffect,EventEvent>() {
//    lateinit var binding: FragmentEventBinding
//    val viewModel by viewModels<EventViewModel>()

    private val calendarAdapter = CalendarAdapter()

    private val requestPermissionLauncher= registerForActivityResult(ActivityResultContracts.RequestPermission()){

        isGranted: Boolean ->
        if (isGranted){
            readCalendar()
        }
    }

    override fun getViewModelClass() = EventViewModel::class.java

    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventBinding = { inflater, viewGroup, value ->
        FragmentEventBinding.inflate(inflater, viewGroup, value)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter=calendarAdapter
        askNotificationPermission()
    }

    private fun askNotificationPermission(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_CALENDAR) ==
            PackageManager.PERMISSION_GRANTED){
            readCalendar()
        }else{
            requestPermissionLauncher.launch(android.Manifest.permission.READ_CALENDAR)
        }
    }
    private fun readCalendar() {
        val eventsList = arrayListOf<Calendar>()
        val cursor = requireActivity().contentResolver.query(
            CalendarContract.Events.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.use {
            val idIndex = cursor.getColumnIndex(CalendarContract.Events._ID)
            val nameIndex = cursor.getColumnIndex(CalendarContract.Events.TITLE)
            val descriptionIndex = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION)
            val beginTimeIndex = cursor.getColumnIndex(CalendarContract.Events.DTSTART)
            val endTimeIndex = cursor.getColumnIndex(CalendarContract.Events.DTEND)

            while (cursor.moveToNext()) {
                val eventId = if (idIndex != -1) cursor.getLong(idIndex) else -1L
                val name = cursor.getStringOrNull(nameIndex)
                val description = cursor.getStringOrNull(descriptionIndex)
                val beginTime = if (beginTimeIndex != -1) cursor.getLong(beginTimeIndex) else -1L
                val endTime = if (endTimeIndex != -1) cursor.getLong(endTimeIndex) else -1L

                if (eventId != -1L && name != null && description != null && beginTime != -1L && endTime != -1L) {
                    eventsList.add(Calendar(eventId, name, description, beginTime, endTime))
                }
            }
        }
        cursor?.close()
        calendarAdapter.submitList(eventsList)
    }

    private fun Cursor.getStringOrNull(index: Int): String? {
        return if (index != -1) getString(index) else null
    }


}