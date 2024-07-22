package com.example.incoming_call

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CallLogAdapter(
    private val callLogs: List<CallLogEntry>, private val clickListener: (CallLogEntry) -> Unit
) : RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.call_log_item, parent, false)
        return CallLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CallLogViewHolder, position: Int) {
        holder.bind(callLogs[position], clickListener)
    }

    override fun getItemCount(): Int {
        return callLogs.size
    }

    class CallLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val callTypeTextView: TextView = itemView.findViewById(R.id.callType)
        private val callDateTextView: TextView = itemView.findViewById(R.id.callDate)
        private val callDurationTextView: TextView = itemView.findViewById(R.id.callDuration)
        private val callNumberTextView: TextView = itemView.findViewById(R.id.callNumber)


        fun bind(callLogEntry: CallLogEntry, clickListener: (CallLogEntry) -> Unit) {
            callTypeTextView.text = callLogEntry.type
//            callDateTextView.text = callLogEntry.date.toString()
            callDateTextView.text = formatDate(callLogEntry.date)
//            callDurationTextView.text = callLogEntry.duration.toString()
            callDurationTextView.text = formatDuration(callLogEntry.duration)
            callNumberTextView.text = callLogEntry.number
            itemView.setOnClickListener { clickListener(callLogEntry) }
        }

        private fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
        private fun formatDuration(durationInSeconds: Int): String {
            val hours = durationInSeconds / 3600
            val minutes = (durationInSeconds % 3600) / 60
            val seconds = durationInSeconds % 60

            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }

}

