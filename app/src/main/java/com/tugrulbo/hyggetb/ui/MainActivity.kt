package com.tugrulbo.hyggetb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.tugrulbo.hyggetb.R
import com.tugrulbo.hyggetb.ui.fragments.DetailFragment
import com.tugrulbo.hyggetb.ui.fragments.HomeFragment
import com.tugrulbo.hyggetb.ui.fragments.MapFragment
import com.tugrulbo.hyggetb.util.Communicator

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, HomeFragment()).commit()
    }

    override fun passDataCom(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)

        val transaction = this.supportFragmentManager.beginTransaction()
        val frag2 = DetailFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.frameLayout, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun passMapData(brandName:String?,latitude:String?,longitude:String?) {
        val bundle = Bundle()
        bundle.putString("brandName",brandName)
        bundle.putString("latitude",latitude)
        bundle.putString("longitude",longitude)

        val transaction = this.supportFragmentManager.beginTransaction()
        val frag2 = MapFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.frameLayout, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }




}