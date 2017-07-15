package com.ysn.moncy.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by root on 15/07/17.
 */

/**
 *
 * TypeConverter berfungsi untuk mengubah atau meng-insert data ke dalam database
 * dengan memilih salah satu type data yang tersedia di database. Contohnya,
 * Respon dari API dapat JSON dan JSON tidak tersedia di type data di database dan yang
 * tersedia adalah String. Jadi, kita buat 2 buah method yakni,
 * toJson sebagai mengambil nilai String dari database dan dikonversikan ke JSON yang tersedia di Java
 * toString sebagai menyimpan nilai JSON dari Java dan dikonversikan ke String untuk di-insert ke database.
 * Oleh karena itu, class Converter harus minimal memiliki 2 buah method yakni,
 * method untuk konversi ke database dan method untuk konversi ke java.
  */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
