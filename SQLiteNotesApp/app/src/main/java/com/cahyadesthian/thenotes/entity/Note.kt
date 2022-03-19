package com.cahyadesthian.thenotes.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * STEP 1
 * Kelas model di atas merepresentasikan data yang tersimpan dan memberi
 * kemudahan menulis kode. Lebih simpel dibandingkan dengan ketika Anda
 * harus mengolah data dalam bentuk objek Cursor.
 * Selain itu dengan menjadikan objek ini sebagai objek
 * Parcelable (dalam bentuk paket) memudahkan pengiriman data dari satu
 * Activity ke Activity lain.
 *
 *
 * [INI STEP 2 BELOW]
 * Selesai model Note-nya, sekarang Anda fokus membangun database baik dari sisi DDL dan DML-nya.
 * */

@Parcelize
data class Note(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var date: String? = null
) : Parcelable
