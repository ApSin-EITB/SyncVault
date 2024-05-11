package ru.eitb.syncvault

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hierynomus.smbj.share.File

class FileAdapter(private val files: List<File>) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox) // Если у вас есть CheckBox в вашем layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_list_item, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.textViewName.text = file.uncPath // Используем getUncPath() для получения UNC пути файла
        // Если checkBox используется для выбора файлов, его состояние можно устанавливать здесь
        holder.checkBox.visibility = View.GONE // Если CheckBox не нужен, его можно скрыть
    }

    override fun getItemCount() = files.size
}
