package com.glebkrep.itunesalbumns.Utils

object MyTextUtils {
    fun formatReleaseDate(releaseDate:String):String{
        val date = releaseDate.split("-")
        val year = date[0]
        val month = date[1]
        val day = date[2].slice(0..1)
        return "$year/$month"
    }

    fun formatDuration(timeInMillis:Long): String {
        var seconds = timeInMillis/1000L
        var minutes = (seconds/60).toInt()
        seconds = seconds-(minutes*60)
        if (seconds<10){
            return "$minutes:0$seconds"
        }
        else
        return "$minutes:$seconds"
    }
}