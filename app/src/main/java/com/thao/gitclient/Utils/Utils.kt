package com.thao.gitclient.Utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun getFragment(fragment: String, bundle: Bundle?): Fragment {
            try {
                var tags = fragment.split("_")
                var fragment = Class.forName(tags[0]).newInstance() as Fragment
                fragment.arguments = bundle
                return fragment
            } catch (e: Exception) {
                e.printStackTrace()
            }

            throw Exception("Fragment Not exist")
        }

        fun formatDate(input: String): String {
            var timeConverted = ""
            if (!input.isEmpty()) {
                val des = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                val formatterTarget = SimpleDateFormat("MMM dd yyyy")
                var date: Date? = null
                try {
                    date = des.parse(input)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                date?.let {
                    timeConverted = formatterTarget.format(date)
                }?:kotlin.run {
                    timeConverted = input  }

            }
            return timeConverted
        }
    }

}