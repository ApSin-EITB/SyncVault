package ru.eitb.syncvault

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Запуск MainActivity после задержки
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрытие текущей активности
        }, 3000) // 3000 мс задержка
    }
}
