package com.example.projettdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Image
import com.example.projettdm.DataManager.Entities.Video
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var adapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = AppDatabase(this)
        populateDatabase(db)

        DataHolder.dbReference = db

        GlobalScope.launch {
            var myDataList = db.CountryDao().getAll()
            DataHolder.countriesList.addAll(myDataList)
        }

        adapter = CountryListAdapter(this, R.layout.row, DataHolder.countriesList)

        button.setOnClickListener {
            val intent = Intent(this, CountryDetails::class.java)
            startActivity(intent)
        }
        toolbar = findViewById(R.id.toolbar)


        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_all -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_favorite -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_visited -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_about -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

        /*
          GlobalScope.launch {
            db.CountryDao().insertAll(Country(name = "Algeria", code = "DZ" ,
                description = "flutter run " , history = "algeira", visited = false , favorite = false ,
                flagSrc = "" , hymeSrc = "" ))

            db.CountryDao().insertAll(Country(name = "ksa", code = "DZ" ,
                description = "flutter run " , history = "algeira", visited = false , favorite = false ,
                flagSrc = "" , hymeSrc = "" ))

            println("====> data added successfully")

            var data = db.CountryDao().getAll()
            println("====> data retrieved successfully")
            data?.forEach {
                println(it)
            }
        }
        */


      //  val iconName = (R.drawable.ic_launcher_background)
        //println("icon name ===>"+ iconName)


        /*button.setOnClickListener {

            val intent = Intent(this, CountryDetails::class.java)
            startActivity(intent)
        }*/





    fun populateDatabase(myDB: AppDatabase){
        GlobalScope.launch {

            myDB.clearAllTables()

            var algeria = Country(
                countryId = 1,
                name = "Algeria",
                code = "DZ" ,
                description = "L'appellation « Algérie » provient du nom de la ville d'Alger. Le nom « Alger » dériverait du catalan Aljer, " +
                        "lui-même tiré de Djezaïr, nom donné par Bologhine ibn Ziri11, fils de Ziri Ibn Menad fondateur de la" +
                        " dynastie berbère des Zirides, lorsqu'il bâtit la ville en 960 sur les ruines de l'ancienne ville au nom " +
                        "romain Icosium, Djaza'ir Beni MezghennaNote 6",


                history = "L’Algérie, en raison de sa tradition de terre d’accueil et les multiples civilisations qui l’ont traversée, a hérité " +
                        "d’une histoire très riche qui s’exprime par des vestiges d'époques variées. C’est ainsi que l'Afrique, la Méditerranée, l’Europe et" +
                        " l’Orient marquèrent de leurs influences spécifiques le cheminement historique de l’Algérie." +
                        "Les premiers vestiges archéologiques notables sont d'âge préhistorique et remontent à l'époque néolithique, comme ceux du parc national " +
                        "du Tassili que l'on considère comme le musée à ciel ouvert le plus étendu au monde. Plus tard, les Berbères construisirent plusieurs sites " +
                        "comme Medracen, Mausolée royal de Maurétanie,",
                visited = false ,
                favorite = false ,
                population = 20000000,
                surface = 2150 ,
                flagSrc = (R.drawable.dz_flag) ,
                hymeSrc = (R.raw.hyme_algeria))


            var tunisia = Country(
                countryId = 2,
                name = "Tunisia",
                code = "TN" ,
                description = "Tunisia,[a] officially the Republic of Tunisia,[b][18] is a country in the Maghreb region of North Africa, covering 163,610 square kilometres" +
                        " (63,170 square miles). Its northernmost point, Cape Angela, is also the northernmost point on the African continent. Tunisia is bordered by Algeria to " +
                        "the west and southwest, Libya to the southeast, and the Mediterranean Sea to the north and east. Tunisia's population was 11.7 million in 2019.[12] Tunisia's " +
                        "name is derived from its capital city, Tunis (Berber native name: Tunest), which is located on its northeast coast." +
                        "Geographically, Tunisia contains the eastern end of the Atlas Mountains, and the northern reaches of the Sahara desert. Much of the rest of the country's land is fertile soil." +
                        " Its 1,300 kilometres (810 miles) of coastline include the African conjunction of the western and eastern parts of the Mediterranean Basin.",

                history = "Farming methods reached the Nile Valley from the Fertile Crescent region about 5000 BC, and spread to the Maghreb by about 4000 BC. Agricultural communities in the " +
                        "humid coastal plains of central Tunisia then were ancestors of today's Berber tribes." +
                        "It was believed in ancient times that Africa was originally populated by Gaetulians and Libyans, both nomadic peoples. According to the Roman historian Sallust, " +
                        "the demigod Hercules died in Spain and his polyglot eastern army was left to settle the land, with some migrating to Africa. Persians went to the West" +
                        " and intermarried with the Gaetulians and became the Numidians. The Medes settled and were known as Mauri, later Moors.[33]\n" +
                        "Carthaginian-held territory before the first First Punic War\n" +
                        "The Numidians and Moors belonged to the race from which the Berbers are descended. The translated meaning of Numidian is Nomad and indeed the people were" +
                        " semi-nomadic until the reign of Masinissa of the Massyli tribe",
                visited = false ,
                favorite = false ,
                population = 20000000,
                surface = 2150 ,
                flagSrc = (R.drawable.tn_flag) ,
                hymeSrc = (R.raw.hyme_tunisia)
            )

            var saudia = Country(
                countryId = 3,
                name = "Saudia arabia",
                code = "TN" ,
                description = "is a country in Western Asia constituting the bulk of the Arabian Peninsula. With a land area of approximately 2,150,000 km2 (830,000 sq mi), " +
                        "Saudi Arabia is geographically the largest sovereign state in Western Asia, the second-largest in the Arab world (after Algeria), the fifth-largest in Asia," +
                        " and the 12th-largest in the world. Saudi Arabia is bordered by Jordan and Iraq to the north, Kuwait to the northeast, Qatar, Bahrain, and the United Arab Emirates to the east," +
                        " Oman to the southeast and Yemen to the south; it is separated from Egypt and Israel by the Gulf of Aqaba. It is the only country with both a Red Sea coast and a Persian Gulf coast, " +
                        "and most of its terrain consists of arid desert, lowland and mountains. As of October 2018, the Saudi economy was the largest in the Middle East and the 18th largest in the world." +
                        "[9] Saudi Arabia also has one of the world's youngest populations: 50 percent of its 33.4 million people are under 25 years old.",

                history = "There is evidence that human habitation in the Arabian Peninsula dates back to about 125,000 years ago.[49] A 2011 study found that the first modern humans to spread east across Asia " +
                        "left Africa about 75,000 years ago across the Bab-el-Mandeb connecting the Horn of Africa and Arabia.[50] The Arabian peninsula is regarded as a central figure in the understanding of" +
                        " hominin evolution and dispersals. Arabia underwent an extreme environmental fluctuation in the Quaternary that led to profound evolutionary and demographic changes. Arabia has a rich Lower" +
                        " Paleolithic record, and the quantity of Oldowan-like sites in the region indicate a significant role that Arabia had played in the early hominin colonization of Eurasia.[51]",
                visited = false ,
                favorite = false ,
                population = 20000000,
                surface = 2150 ,
                flagSrc = (R.drawable.sa_flag),
                hymeSrc = (R.raw.hyme_saudi)
            )


            // inserting countries
            myDB.CountryDao().insertAll(algeria , tunisia, saudia)

            // defining images for countries
            // algeria
            val dz_img1 = Image("Image1 " , "description one ", (R.drawable.dz_1),"yacine" , 1 )
            val dz_img2 = Image("Image2 " , "description one ", (R.drawable.dz_2),"yacine" , 1 )
            val dz_img3 = Image("Image3 " , "description one ", (R.drawable.dz_3),"yacine" , 1 )
            val dz_img4 = Image("Image4 " , "description one ", (R.drawable.dz_4),"yacine" , 1 )
            val dz_img5 = Image("Image5 " , "description one ", (R.drawable.dz_5),"yacine" , 1 )
            val dz_img6 = Image("Image6 " , "description one ", (R.drawable.dz_6),"yacine" , 1 )
            val dz_img7 = Image("Image7 " , "description one ", (R.drawable.dz_7),"yacine" , 1 )

            // inserting images
            myDB.ImageDao().insertAll(dz_img1,dz_img2,dz_img3, dz_img4, dz_img5,dz_img6,dz_img7)

            // tunisia
            val tn_img1= Image("Image1 " , "description one ", (R.drawable.tn_1),"yacine" , 2 )
            val tn_img2= Image("Image2 " , "description one ", (R.drawable.tn_2),"yacine" , 2 )
            val tn_img3= Image("Image3 " , "description one ", (R.drawable.tn_3),"yacine" , 2 )
            val tn_img4= Image("Image4 " , "description one ", (R.drawable.tn_4),"yacine" , 2 )
            val tn_img5= Image("Image5 " , "description one ", (R.drawable.tn_5),"yacine" , 2 )
            val tn_img6= Image("Image6 " , "description one ", (R.drawable.tn_6),"yacine" , 2 )
            val tn_img7= Image("Image7 " , "description one ", (R.drawable.tn_7),"yacine" , 2 )

            // inserting images
            myDB.ImageDao().insertAll(tn_img1,tn_img2,tn_img3,tn_img4, tn_img5, tn_img6, tn_img7)

            // saudi arabia
            val sa_img1= Image("Image1 " , "description one ", (R.drawable.sa_1),"yacine" , 3 )
            val sa_img2= Image("Image2 " , "description one ", (R.drawable.sa_2),"yacine" , 3 )
            val sa_img3= Image("Image3 " , "description one ", (R.drawable.sa_3),"yacine" , 3 )
            val sa_img4= Image("Image4 " , "description one ", (R.drawable.sa_4),"yacine" , 3 )
            val sa_img5= Image("Image5 " , "description one ", (R.drawable.sa_5),"yacine" , 3 )
            val sa_img6= Image("Image6 " , "description one ", (R.drawable.sa_6),"yacine" , 3 )
            val sa_img7= Image("Image7 " , "description one ", (R.drawable.sa_7),"yacine" , 3 )

            // inserting images
            myDB.ImageDao().insertAll(sa_img1,sa_img2,sa_img3,sa_img4, sa_img5 ,sa_img6,sa_img7)


            println("====> data added successfully")

            var data = myDB.CountryDao().getAll()
            println("====> data retrieved successfully")
            data?.forEach {
                println(it)
            }


            val vid1 = Video(" video 1","decription",R.raw.algerie1,"me",1)
            val vid2 = Video(" video ","decription",R.raw.algerie2,"me",1)

            myDB.VideoDao().insertAll(vid1,vid2)
        }
    }
}
