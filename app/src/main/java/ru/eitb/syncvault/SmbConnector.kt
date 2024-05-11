package ru.eitb.syncvault

import com.hierynomus.mssmb2.SMB2Dialect
import com.hierynomus.smbj.SMBClient
import com.hierynomus.smbj.auth.AuthenticationContext
import com.hierynomus.smbj.connection.Connection
import com.hierynomus.smbj.session.Session
import com.hierynomus.smbj.share.DiskShare
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class SmbConnector {

    private var client: SMBClient? = null
    private var connection: Connection? = null
    private var session: Session? = null
    private var scheduledExecutor: ScheduledExecutorService? = null

    fun connectToServer(serverName: String, username: String, password: String, updateUI: (String) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            client = SMBClient()
            try {
                connection = client?.connect(serverName)
                val ac = AuthenticationContext(username, password.toCharArray(), null)
                session = connection?.authenticate(ac)
                updateUI("Соединение с сервером установлено")
                startConnectionMonitor(updateUI)
            } catch (e: Exception) {
                updateUI("Ошибка подключения: ${e.message}")
            }
        }
    }

    private fun startConnectionMonitor(updateUI: (String) -> Unit) {
        scheduledExecutor = Executors.newScheduledThreadPool(1)
        scheduledExecutor?.scheduleAtFixedRate({
            try {
                val share = session?.connectShare("storage") as DiskShare?
                if (share != null && share.list("").isNotEmpty()) {
                    updateUI("Соединение стабильно")
                } else {
                    updateUI("Нет файлов в шаре")
                }
            } catch (e: Exception) {
                updateUI("Соединение разорвано: ${e.message}")
            }
        }, 1, 1, TimeUnit.SECONDS)
    }

    fun disconnect() {
        try {
            session?.close()
            connection?.close()
            client?.close()
            scheduledExecutor?.shutdownNow()
            println("Соединение закрыто")
        } catch (e: Exception) {
            println("Ошибка при разрыве соединения: ${e.message}")
        }
    }
}
