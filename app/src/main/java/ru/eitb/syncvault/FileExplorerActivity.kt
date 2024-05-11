package ru.eitb.syncvault

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.hierynomus.smbj.share.File

class FileExplorerActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FileAdapter // Предположим, у вас есть адаптер для файлов

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_explorer)

        recyclerView = findViewById(R.id.recyclerViewFiles)
        adapter = FileAdapter(loadFiles()) // Загрузка файлов для отображения
        recyclerView.adapter = adapter
    }

    private fun loadFiles(): List<File> {
        // Тут должен быть код для загрузки файлов из выбранной папки или сервера
        return listOf() // Возвращаем пустой список как заглушку
    }
}
