package com.example.uas_if10k_10120917.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.uas_if10k_10120917.models.ItemSliderWalkthrough
import com.example.uas_if10k_10120917.R

class ViewPagerAdapter(private val items: List<ItemSliderWalkthrough>, private val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.slider_screen, container, false)
        val title = view.findViewById<TextView>(R.id.sliderTitle)
        val description = view.findViewById<TextView>(R.id.sliderBody)
        val image = view.findViewById<ImageView>(R.id.sliderImage)

        title.text = items[position].title
        description.text = items[position].description
        image.setImageResource(items[position].image)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

}