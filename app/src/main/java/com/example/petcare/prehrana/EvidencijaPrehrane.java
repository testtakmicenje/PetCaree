package com.example.petcare.prehrana

import android.Manifest
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.petcare.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EvidencijaPrehrane() : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 112

    private var imeLjubimcaEditText: EditText? = null

    private var datumVrijemeTextView: TextView? = null

    private var calendar: Calendar? = null

    var addpodsjetnik: Button? = null

    var workeremail: EditText? = null

    var mDatabase: SQLiteDatabase? = null

    private val channelId = "channelId"

    private lateinit var notificationManager: NotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evidencija_prehrane_activity)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT > 32) {
            if (!shouldShowRequestPermissionRationale("112")) {
                notificationPermission
            }
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            val backImageView = findViewById<ImageView>(R.id.logoImageView1)
            backImageView.setOnClickListener(View.OnClickListener { onBackPressed() })

            // Inicijalizacija elemenata
            imeLjubimcaEditText = findViewById(R.id.imeLjubimcaEditText)
            datumVrijemeTextView = findViewById(R.id.vrijemeHranjenjaText)
            workeremail = findViewById(R.id.imeLjubimca1EditText)
            addpodsjetnik = findViewById(R.id.podesiPodsjetnikButton)
            calendar = Calendar.getInstance()
            mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null)
            createEmployeeTable()

            // Postavljanje klika za odabir datuma i vremena
            datumVrijemeTextView!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    showDateTimePicker()
                }
            })
        }
        addpodsjetnik!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val email = workeremail!!.text.toString().trim { it <= ' ' }
                if (email.isEmpty()) {
                    showToast("Molimo vas da unesete sve podatke.")
                } else {
                    // Svi potrebni podaci su uneseni, možete izvršiti unos u bazu podataka
                    val databaseDateFormat =
                        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                    val formattedDateTime = databaseDateFormat.format(calendar!!.time)
                    val insertSQL = ("INSERT INTO Prehrana2 \n" +
                            "(Email, Vrijeme)\n" +
                            "VALUES \n" +
                            "(?, ?);")
                    mDatabase!!.execSQL(insertSQL, arrayOf(email, formattedDateTime))

                    scheduleNotification(calendar!!.timeInMillis)

                    // Postavljanje push notifikacije
                    val intent = Intent(this@EvidencijaPrehrane, Prehrana::class.java)
                    startActivity(intent)
                }
            }
        })
    }

    private fun createEmployeeTable() {
        mDatabase!!.execSQL(
            ("CREATE TABLE IF NOT EXISTS Prehrana2 " +
                    "(\n" +
                    "    id INTEGER NOT NULL CONSTRAINT Prehrana_pk2 PRIMARY KEY AUTOINCREMENT,\n" +
                    "    Email varchar(200) NOT NULL,\n" +
                    "    Vrijeme varchar(200) NOT NULL\n" +
                    ");")
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this@EvidencijaPrehrane, message, Toast.LENGTH_SHORT).show()
    }

    private fun scheduleNotification(timeInMillis: Long) {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }


    private fun showDateTimePicker() {
        // Postavljanje trenutnog datuma i vremena u dijalog
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.DatePickerTheme,
            object : OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    calendar!![Calendar.YEAR] = year
                    calendar!![Calendar.MONTH] = monthOfYear
                    calendar!![Calendar.DAY_OF_MONTH] = dayOfMonth

                    // Prikazivanje dijaloga za odabir vremena nakon odabira datuma
                    val timePickerDialog = TimePickerDialog(
                        this@EvidencijaPrehrane, R.style.DatePickerTheme,
                        object : OnTimeSetListener {
                            override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
                                calendar!![Calendar.HOUR_OF_DAY] = hourOfDay
                                calendar!![Calendar.MINUTE] = minute

                                // Formatiranje i postavljanje odabranog datuma i vremena u TextView
                                val dateFormat =
                                    SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                                datumVrijemeTextView!!.text = dateFormat.format(calendar!!.time)
                            }
                        }, calendar!![Calendar.HOUR_OF_DAY], calendar!![Calendar.MINUTE], true
                    )
                    timePickerDialog.show()

                    // Prilagodba boje dugmadi unutar DatePickerDialog-a
                    val positiveButton = timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                    val negativeButton = timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                    positiveButton.setTextColor(Color.parseColor("#000000")) // Boja teksta
                    positiveButton.setBackgroundColor(Color.parseColor("#FFFFFF")) // Boja pozadine
                    negativeButton.setTextColor(Color.parseColor("#000000")) // Boja teksta
                    negativeButton.setBackgroundColor(Color.parseColor("#FFFFFF")) // Boja pozadine
                }
            },
            calendar!![Calendar.YEAR],
            calendar!![Calendar.MONTH],
            calendar!![Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
        val positiveButton = datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        val negativeButton = datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        positiveButton.setTextColor(Color.parseColor("#000000")) // Boja teksta
        positiveButton.setBackgroundColor(Color.parseColor("#FFFFFF")) // Boja pozadine
        negativeButton.setTextColor(Color.parseColor("#000000")) // Boja teksta
        negativeButton.setBackgroundColor(Color.parseColor("#FFFFFF")) // Boja pozadine
    }

    private fun getNotification(content: String): Notification {
        val builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "1"
            val channelName: CharSequence = "Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(channelId, channelName, importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            builder = Notification.Builder(this, channelId)
        } else {
            builder = Notification.Builder(this)
        }
        builder.setContentTitle("Vaš podsjetnik")
        builder.setContentText(content)
        builder.setSmallIcon(R.drawable.notificationicon) // Dodajte ikonu notifikacije
        return builder.build()
    }

    val notificationPermission: Unit
        get() {
            try {
                if (Build.VERSION.SDK_INT > 32) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        PERMISSION_REQUEST_CODE
                    )
                }
            } catch (e: Exception) {
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // allow
                } else {
                    //deny
                }
                return
            }
        }
    }

    companion object {
        val DATABASE_NAME = "Prehrana.db"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "Dummy Channel",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This is dummy description"
        }

        notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(
            this, channelId
        )

        notificationBuilder.apply {
            setSmallIcon(R.drawable.notificationicon)
            setTitle("Android 13!!!")
            setContentText("Android 13 notification")
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this)
            .notify(1, notificationBuilder.build())
    }

}
