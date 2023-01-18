package pl.polsl.lab6v2022

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView

class Kartka : AppCompatActivity() {

    private var presentList: ArrayList<String> = ArrayList()
    private var myList: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        if(savedInstanceState != null) {
            myList = savedInstanceState.getString("list","")
        } else {
            myList = intent.getStringExtra("list")!!
        }

        WebView.setWebContentsDebuggingEnabled(true)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

//        this.requestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity") //ODKOMENTOWAC DLA JS

        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Kartka.html")

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("list", myList)
    }

    @JavascriptInterface
    fun getList(): String? {
        return myList
    }



}