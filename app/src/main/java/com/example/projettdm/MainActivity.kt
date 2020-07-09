package com.example.projettdm

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Image
import com.example.projettdm.DataManager.Entities.Video
import com.example.projettdm.DataManager.Entities.VideoYoutube
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var adapter: CountryListAdapter

    private var list_countries : MutableList<Country> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = AppDatabase(this)
        DataHolder.dbReference = db
        //populateDatabase(db)

        adapter = CountryListAdapter(this, R.layout.row, this.list_countries)
        listView.adapter = adapter

        nav_view.menu.getItem(0).isChecked = true;

        try {
            GlobalScope.launch {
                var myDataList = db.CountryDao().getAll()
                DataHolder.countriesList.addAll(myDataList)
                list_countries.clear()
                list_countries.addAll(myDataList)
                println("we got our data ===>"+ DataHolder.countriesList.size)
            }.invokeOnCompletion {
                adapter.notifyDataSetChanged()

              //  this.runOnUiThread(Runnable { adapter.notifyDataSetChanged() })
                    // adapter.notifyDataSetChanged()
            }

        }catch (err:Error){
            println("Error loading the data")
            Toast.makeText(this, "Error loading the data", Toast.LENGTH_SHORT).show()
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

                try {
                    GlobalScope.launch {
                        var myDataList = DataHolder.dbReference.CountryDao().getAll()
                        DataHolder.countriesList.clear()
                        DataHolder.countriesList.addAll(myDataList)
                        list_countries.clear()
                        list_countries.addAll(myDataList)
                        println("_________________we got our data ===>")
                    }.invokeOnCompletion {

                        this.runOnUiThread(Runnable {
                            adapter.notifyDataSetChanged()
                            Toast.makeText(this, "All Countries", Toast.LENGTH_SHORT).show()

                        })


                    }

                }catch (err:Error){
                    println("Error loading the data")
                    Toast.makeText(this, "Error loading the data", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.nav_favorite -> {
                try {
                    GlobalScope.launch {
                        var myDataList = DataHolder.dbReference.CountryDao().getAllFavorites()
                        DataHolder.countriesList.clear()
                        DataHolder.countriesList.addAll(myDataList)
                        list_countries.clear()
                        list_countries.addAll(myDataList)
                        println("_________________we got our data ===>")
                    }.invokeOnCompletion {

                        this.runOnUiThread(Runnable {
                            adapter.notifyDataSetChanged()
                            Toast.makeText(this, "Favorite Countries", Toast.LENGTH_SHORT).show()


                        })


                    }

                }catch (err:Error){
                    println("Error loading the data")
                    Toast.makeText(this, "Error loading the data", Toast.LENGTH_SHORT).show()

                }

            }
            R.id.nav_visited -> {
                try {
                    GlobalScope.launch {
                        var myDataList = DataHolder.dbReference.CountryDao().getAllVisited()
                        DataHolder.countriesList.clear()
                        DataHolder.countriesList.addAll(myDataList)
                        print(myDataList)
                        list_countries.clear()
                        list_countries.addAll(myDataList)
                        println("_________________history ===>")
                    }.invokeOnCompletion {

                        this.runOnUiThread(Runnable {
                            adapter.notifyDataSetChanged()
                            Toast.makeText(this, "Visisted Countries", Toast.LENGTH_SHORT).show()

                        })


                    }

                }catch (err:Error){
                    println("Error loading the data")
                    Toast.makeText(this, "Error loading the data", Toast.LENGTH_SHORT).show()


                }
            }
            R.id.nav_share -> {
                Toast.makeText(this, "Comming soon!", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_about -> {
                Toast.makeText(this, "Comming soon!", Toast.LENGTH_SHORT).show()
            }
            else -> { // Note the block
                try {
                    GlobalScope.launch {
                        var myDataList = DataHolder.dbReference.CountryDao().getAll()
                        DataHolder.countriesList.clear()
                        DataHolder.countriesList.addAll(myDataList)
                        list_countries.clear()
                        list_countries.addAll(myDataList)
                        println("_________________we got our data ===>")
                    }.invokeOnCompletion {

                        this.runOnUiThread(Runnable {
                            adapter.notifyDataSetChanged()
                            Toast.makeText(this, "All Countries", Toast.LENGTH_SHORT).show()

                        })


                    }

                }catch (err:Error){
                    println("Error loading the data")
                    Toast.makeText(this, "Error loading the data", Toast.LENGTH_SHORT).show()
                }

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


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
                code = "SA" ,
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

            var fr = Country(
                countryId = 4,
                name = "France",
                code = "FR" ,
                description = " la République française (Écouter), est un État souverain transcontinental dont le territoire métropolitain est situé en Europe de l'Ouest. Ce dernier a des frontières terrestres avec la Belgique, le Luxembourg, l'Allemagne, la Suisse, l'Italie, l'Espagne et les deux principautés d'Andorre et de Monaco. La France dispose aussi d'importantes façades maritimes sur l'Atlantique et la Méditerranée. Son territoire ultramarin s'étend dans les océans Indien, Atlantique et Pacifique ainsi qu'en Amérique du Sud, et a des frontières terrestres avec le Brésil, le Suriname et les Pays-Bas.\n" +
                        "\n" +
                        "Fruit d'une histoire politique longue et mouvementée, la France est une république constitutionnelle unitaire ayant un régime semi-présidentiel. La devise de la République est depuis 1875 « Liberté, Égalité, Fraternité » et son drapeau est constitué des trois couleurs nationales : bleu, blanc, rouge. Son hymne national est La Marseillaise,",

                history = "La France métropolitaine actuelle occupe la plus grande partie de l'ancienne Gaule celtique, conquise par Jules César au ier siècle av. J.-C., mais elle tire son nom des Francs, un peuple germanique qui s'y installa à partir du ve siècle. La France est un État dont l'unification est ancienne, " +
                        "et fut l'un des premiers pays de l'époque moderne à tenter une expérience démocratique.",
                visited = false ,
                favorite = false ,
                population = 20000000,
                surface = 2150 ,
                flagSrc = (R.drawable.frag_fr),
                hymeSrc = (R.raw.france)
            )

            var russia = Country(
                countryId = 5,
                name = "RUSSIA",
                code = "RU" ,
                description = " est un État fédéral transcontinental fondé en 1991, à la suite de la dislocation de l'Union des républiques socialistes soviétiques et reconnu comme successeur légal de celle-ci. Sa capitale est Moscou, sa langue officielle le russe et sa monnaie le rouble.\n" +
                        "\n" +
                        "Plus vaste État de la planète, la Russie est à cheval sur l'Asie du Nord (74,7 % de sa superficie) et sur l'Europe (25,3 %). Le territoire s'étend ainsi d'ouest en est, de l'enclave de Kaliningrad au district autonome de Tchoukotka, sur plus de 9 000 kilomètres et pour une superficie de 17 125 191 km2. Bien qu'entourée de nombreuses mers et de deux océans, la Russie est caractérisée par un climat continental avec des milieux particulièrement froids et hostiles sur la majeure partie du territoire.\n" +
                        "\n" +
                        "La population russe est estimée à près de 147 millions d'habitants en 20191, ce qui en fait le neuvième pays le plus peuplé de la planète. 78 % de ses habitants vivent en Europe5.",

                history = "Après la Seconde Guerre mondiale, qui avait entraîné la mort d'environ 27 millions de personnes30 (civils et militaires), la population avait retrouvé son niveau d'avant-guerre en 1955 (111 millions), puis s'était accrue de près de 35 % en atteignant son maximum en 1992 (148,7 millions). Cependant plusieurs phénomènes sont venus modifier cette dynamique démographique dont la plus importante est sans doute la « normalisation » de la fécondité russe qui a effectué à compter de 1988 sa transition démographique et présente désormais un taux de natalité proche de celui des autres pays d'Europe de l'Est, c'est-à-dire très bas.",
                visited = false ,
                favorite = false ,
                population = 20000000,
                surface = 2150 ,
                flagSrc = (R.drawable.russian_frag),
                hymeSrc = (R.raw.russia)
            )


            // inserting countries
            myDB.CountryDao().insertAll(algeria , tunisia, saudia , fr , russia)

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

            myDB.ImageDao().insertAll(
                Image("img1","desc",R.drawable.fr_1,"yacine",4),
                Image("img1","desc",R.drawable.fr_2,"yacine",4),
                Image("img1","desc",R.drawable.fr_3,"yacine",4)
            )

            myDB.ImageDao().insertAll(
                Image("img1","desc",R.drawable.russia_3,"yacine",5),
                Image("img1","desc",R.drawable.russia_1,"yacine",5),
                Image("img1","desc",R.drawable.russia_2,"yacine",5)
            )


            println("====> data added successfully")

            var data = myDB.CountryDao().getAll()
            println("====> data retrieved successfully")
            data?.forEach {
                println(it)
            }


            val vid1 = Video(" video 1","decription",R.raw.algerie2,"me",1)
            val vid2 = Video(" video ","decription",R.raw.algerie2,"me",1)

            val yt = VideoYoutube("6gjz41kdDwI",1)
            val yt2 = VideoYoutube("N6t7_RAuR4g",1)
            val yt3 = VideoYoutube("-sgy5hxHzSw",1)

            // russia : "YrNxPr4PKQo" "S_dfq9rFWAE"
            // fr "I60A8-iORm8"   "c2BEGup4eBE"
            // saudi "ollH45NDXu0" "bUzcCBHUDbM"

            myDB.VideoDao().insertAllYoutube(
                VideoYoutube("S_dfq9rFWAE",5),
                VideoYoutube("YrNxPr4PKQo",5),
                VideoYoutube("I60A8-iORm8",4),
                VideoYoutube("c2BEGup4eBE",4),
                VideoYoutube("ollH45NDXu0",3),
                VideoYoutube("bUzcCBHUDbM",3),
                VideoYoutube("G_WoDMAMji8",2)



            )


            myDB.VideoDao().insertAllYoutube(yt,yt2,yt3)

            myDB.VideoDao().insertAll(vid1,vid2,
            Video(" st petersburg "," hello ",R.raw.russia,"yacine",5),
                Video(" Djarba "," hello ",R.raw.tunnis,"yacine",2)
            )

        }

    }
}
