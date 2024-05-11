package ru.eitb.syncvault

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import android.app.AlertDialog
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var syncButton: Button
    private lateinit var logTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация компонентов интерфейса
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        syncButton = findViewById(R.id.buttonSync)
        logTextView = findViewById(R.id.textViewLogs)

        val buttonServerLogin = findViewById<Button>(R.id.buttonServerLogin)
        val buttonSelectFolders = findViewById<Button>(R.id.buttonSelectFolders)

        buttonServerLogin.setOnClickListener {
            showLoginDialog()
        }

        buttonSelectFolders.setOnClickListener {
            // Здесь логика для выбора папок
        }

        // Настройка бокового меню (Drawer)
        setupDrawer()

        buttonSelectFolders.setOnClickListener {
            val intent = Intent(this, FolderPickerActivity::class.java)
            startActivity(intent)
        }

        buttonSelectFolders.setOnClickListener {
            val intent = Intent(this, FolderPickerActivity::class.java)
            startActivity(intent)
        }

        // Настройка кнопки синхронизации
        syncButton.setOnClickListener {
            logTextView.append("\nНачата синхронизация...")
            startSyncProcess()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val smbConnector = SmbConnector()
        smbConnector.disconnect() // Обеспечивает закрытие соединения при завершении активности
    }

    private fun showLoginDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_login, null)
        val usernameEditText = view.findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = view.findViewById<EditText>(R.id.editTextPassword)

        AlertDialog.Builder(this)
            .setView(view)
            .setTitle("Connect to Server")
            .setPositiveButton("Connect") { dialog, which ->
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                connectToServer(username, password)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun connectToServer(username: String, password: String) {
        val smbConnector = SmbConnector()
        smbConnector.connectToServer("10.0.0.219", username, password) { message ->
            runOnUiThread {
                logTextView.append("\n$message")
            }
        }
    }

    private fun setupDrawer() {
        // Подключение toggle для открытия и закрытия Drawer
        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startSyncProcess() {
        // Метод для запуска процесса синхронизации
        logTextView.append("\nСинхронизация данных...")
        // Здесь должен быть вызван ваш код синхронизации
    }
}
