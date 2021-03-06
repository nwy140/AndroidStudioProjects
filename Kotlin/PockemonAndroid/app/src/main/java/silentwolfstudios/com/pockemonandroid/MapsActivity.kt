package silentwolfstudios.com.pockemonandroid

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
// ctrl+shift+a to convert java to kotlin file
    // go to build gradle and add in kotlin versions
    private lateinit var mMap: GoogleMap
    // google 's api has updated so hussein's tutorial is no longer working
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    checkPermissions();
    LoadPockemon();
    }


    //ASK ABOUT DANGEROUS PERMISSION FINE LOCATION

var ACCESSLOCATION = 123;
    fun checkPermissions(){
        if (Build.VERSION.SDK_INT>=23){
            if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            requestPermissions( arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) ,ACCESSLOCATION);
            return;
        }
        GetUserLocation()
    }

    fun GetUserLocation(){
        Toast.makeText(this,"User location access on",Toast.LENGTH_LONG).show();
        //TODO: Will implement later
        var myLocation = MyLocationListener(); //agetting location

        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager //access service
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation);
        var myThread=myThread();
        myThread.start();
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode){

            ACCESSLOCATION->{
                    if (grantResults[0]==PackageManager.PERMISSION_GRANTED    ){
                        GetUserLocation();
                    } else {
                        Toast.makeText(this,"we cannot access your location",Toast.LENGTH_LONG).show();
                    }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    //ASK ABOUT DANGEROUS PERMISSION FINE LOCATION
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

    }


    var location:Location?=null

    // inner class can access to other properties in the class // click on classname to implement methods
    inner class MyLocationListener:LocationListener {
        constructor(){
            location=Location("Start")
            location!!.longitude = 0.0;
            location!!.latitude=0.0;
        }
        override fun onLocationChanged(p0: Location?) {
            location=p0;
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    // Thread cannot communicate with UI // use runOnUiThread {} to communicate with UI in Thread

var oldLocation:Location?=null;
    inner  class myThread:Thread{
        constructor():super(){

            oldLocation=Location("Start")
            oldLocation!!.longitude = 0.0;
            oldLocation!!.latitude=0.0;
        }

        override  fun run(){
            while(true){

                try {
                    if(oldLocation!!.distanceTo(location)!=0f){
                    continue // go back to start of code block
                    }

                    oldLocation = location;

                    runOnUiThread{
                            mMap!!.clear();
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        mMap!!.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("Me")
                                .snippet(" here is my location") /*--Below titleb--*/
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario))   )
                        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,   14f ))

                        // show pockemons

                        for (i in 0..listPockemon.size-1  ){
                            var newPockemon = listPockemon[i]

                            if (newPockemon.IsCatch==false){
                                mMap!!.clear();
                                val pockemonLoc = LatLng(newPockemon.location!!.latitude, newPockemon.location!!.longitude)
                                mMap!!.addMarker(MarkerOptions()
                                        .position(pockemonLoc)
                                        .title(newPockemon.name!!)
                                        .snippet(newPockemon.des!! + "Power:"+newPockemon.power) /*--Below titleb--*/
                                        .icon(BitmapDescriptorFactory.fromResource(newPockemon.image!!))   )

                                if (location!!.distanceTo(newPockemon.location) < 2 ){
                                    newPockemon.IsCatch=true;
                                    listPockemon[i]=newPockemon
                                    playerpower+=newPockemon.power!!

                                    Toast.makeText(applicationContext,"Saiya People caught a new pokemon, your new powa is " + playerpower, Toast.LENGTH_LONG).show();
                                }


                            }

                        }
                    }

                    Thread.sleep(1000);


                } catch (ex:Exception){}

            }
        }

    }

    var playerpower= 0.0;
    var listPockemon = ArrayList <Pockemon>() //araylist from class Pockemon

    fun LoadPockemon(){

        listPockemon.add (Pockemon(R.drawable.charmander,
                "Charmander","Wild Charmender will flamethrower up your ass" ,55.0,37.777.toDouble(),-122.20312940823.toDouble()));
        listPockemon.add (Pockemon(R.drawable.bulbasaur,
                "bulbasaur","Wild Bulbasaur will whinewhip your butthole and tentacle rape you  ",90.5,37.8888.toDouble(),-122.424124.toDouble()));
        listPockemon.add (Pockemon(R.drawable.squirtle,
                "squirtle","Wild Squirtle will watergun piss at you and drink your piss",33.5,37.767.toDouble(),-122.124.toDouble()));
    }

}
