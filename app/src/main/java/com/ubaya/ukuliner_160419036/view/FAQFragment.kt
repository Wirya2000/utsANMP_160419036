package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.ukuliner_160419036.R
import kotlinx.android.synthetic.main.fragment_f_a_q.*

/**
 * A simple [Fragment] subclass.
 * Use the [FAQFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FAQFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f_a_q, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textFAQTitle1.text = "Tentang Aplikasi"
        textFAQTitle2.text = "Bagaimana cara memberikan review makanan"
        textFAQTitle3.text = "Bagaimana cara mengganti isi data diri saya"
        textFAQTitle4.text = "Bagaimana cara menyimpan reataurant yang disuka agar tidak hilang"
        textFAQContent1.text = "Aplikasi ini adalah aplikasi untuk melihat restaurant di sekitar " +
                "UBAYA beserta reviewnya, selain dapat melihat restaurant, di applikasi ini juga dapat " +
                "melihat menu-menu yang ada di restaurant tersebut beserta harganya"
        textFAQContent2.text = "Untuk memberikan review makanan, Anda dapat memilih restaurant yang ingin " +
                "Anda review, kemudian klik tombol review, pada halaman Restaurant, kemudian " +
                "klik tombol + yang ada pada pojok kanan bawah pada halaman Review. Setelah selesai mengisi semua, " +
                "klik tombol POST untuk memberi review"
        textFAQContent3.text = "Untuk mengganti isi dari data diri, Anda dapat menuju menu Profile, " +
                "kemudian ubah data diri yan ingin dirubah, setelah selesai, klik tombol UPDATE " +
                "untuk merubah data diri Anda"
        textFAQContent4.text = "Untuk menyimpan restaurant yang Anda suka agar tidak hilang, " +
                "Anda dapat menggunakan fitur Favourite, untuk menggunakan fitur tersebut, " +
                "Anda hanya perlu ke halaman restaurant yang Anda suka, kemudian klik icon hati " +
                "yang ada di pojok kanan atas, maka restaurant tersebut akan tersimpan. Untuk dapat " +
                "melihat daftar restaurant yang telah Anda jadikan favourite, Anda dapat menuju " +
                "halaman Favourite pada tombol di paling bawah maupun pada icon di pojok kiri " +
                "di halaman Home"
    }
}