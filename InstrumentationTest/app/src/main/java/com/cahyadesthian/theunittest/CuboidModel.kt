package com.cahyadesthian.theunittest

class CuboidModel {

    private var width = 0.0
    private var length = 0.0
    private var height = 0.0

    fun getVolume(): Double = width*length*height

    fun getSurfaceArea(): Double {
        val wl = width*length
        val wh = width*height
        val hl = length*height

        return 2*(wl+wh+hl)
    }

    fun getCircumference(): Double = 4*(width+length+height)

    fun save(width: Double, length: Double, height:Double) {
        this.width = width
        this.length = length
        this.height = height
    }

}