package ru.eitb.syncvault

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FolderAdapter(private val folders: List<Folder>) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    class FolderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_list_item, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folder = folders[position]
        holder.textViewName.text = folder.name
        holder.checkBox.isChecked = folder.isSelected
        holder.itemView.setOnClickListener {
            folder.isSelected = !folder.isSelected
            holder.checkBox.isChecked = folder.isSelected
        }
    }

    override fun getItemCount() = folders.size

    fun getSelectedFolders(): List<Folder> {
        return folders.filter { it.isSelected }
    }
}
