package com.example.projettdm



import android.annotation.SuppressLint
import android.provider.Telephony.Sms
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import androidx.core.content.ContextCompat
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Dao.CountryDAO
import com.example.projettdm.DataManager.Entities.Country


class ReceiverStart : BroadcastReceiver() {

    private val channelId = "_unique"
    private val channelName = "SIL Channel2"

    private var country: Country? = null
    private var country_dao : CountryDAO? = null
    private var databse: AppDatabase? = null

    @RequiresApi(Build.VERSION_CODES.N)
    private val importance = NotificationManager.IMPORTANCE_HIGH

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("notif", "invoqued")
        print("__________________________")

        Log.d("intent______ URI", intent.toUri(0))


        newMessageSendNotificationWithCheck(
                  "string test",
                    context
                )





    }


    private fun newMessageSendNotificationWithCheck(str_number: String, active_con: Context) {

        val active = this ;
        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                //in background
                //active
                active.databse = AppDatabase.invoke(active_con)

                active.country_dao = active.databse?.CountryDao()

                var ls = listOf<Country>()
                 ls = active.country_dao?.getNewCountry()!!

                Log.d("intent______ listing", ls.toString())

                if(ls.isNotEmpty()){
                    active.country = ls[0]
                    Log.d("intent______ country ", active.country!!.name)
                }

                return null
            }

            override fun onPostExecute(result: Void?) {
                if(country!=null){

                    sendNotification(
                        "  Discover new country "+ country!!.name ,
                        "  ${country!!.description.subSequence(0,30)} ",
                        active_con , country!!.countryId
                    )
                }
            }


        }
        luncher.execute()
    }


    private fun sendNotification(titre: String, contenu: String, context: Context,_id:Int) {


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            mChannel.description = " this channel is for contact response app "

            val intent = Intent(context, CountryDetails::class.java)
            intent.putExtra("countryId",_id.toString())
            context.startActivity(intent)

            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            notificationManager.createNotificationChannel(mChannel)

            val noti = Notification.Builder(context, channelId)
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)
                .addAction(R.drawable.ic_launcher_background, " view ",
                    pendingIntent)

                .build()
            notificationManager.notify(_id, noti)
            Log.d("notif", "sent1")

        } else {

            val noti = Notification.Builder(context)
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)

                .build()
            notificationManager.notify((1..102).shuffled().first(), noti)
            Log.d("notif", "sent2")


        }

    }
}