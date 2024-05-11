package ru.eitb.syncvault

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class FolderPickerActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FolderAdapter
    private lateinit var buttonSelect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folder_picker)

        recyclerView = findViewById(R.id.recyclerViewFolders)
        adapter = FolderAdapter(loadFolders()) // Предполагается, что у вас есть функция загрузки папок
        recyclerView.adapter = adapter

        buttonSelect = findViewById<Button>(R.id.buttonSelect)
        buttonSelect.setOnClickListener {
            val selectedFolders = adapter.getSelectedFolders() // Получение выбранных папок
            val intent = Intent(this, FileExplorerActivity::class.java)
            // Передача выбранных папок или других параметров в FileExplorerActivity
            intent.putExtra("selectedFolders", selectedFolders.map { it.name }.toTypedArray())
            startActivity(intent) // Запуск FileExplorerActivity
        }
    }

    private fun loadFolders(): List<Folder> {
        // Реализуйте загрузку списка папок
        return listOf()
    }
}

