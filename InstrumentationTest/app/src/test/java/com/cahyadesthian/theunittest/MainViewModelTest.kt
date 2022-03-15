package com.cahyadesthian.theunittest

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var cuboidModel: CuboidModel


    private val dummyLength = 12.0
    private val dummyWidth = 7.0
    private val dummyHeight = 6.0

    //private val dummyVolume = 500    //gonna get it wrong to see the condition of false
    private val dummyVolume = 503.9999 //betul juga karena deltanya 0.0001
    //private val dummyVolume = 504 //test passed

    private val dummyCircumference = 100.0
    private val dummySurfaceArea = 396.0

    @Before
    fun before() {
        cuboidModel = mock(CuboidModel::class.java)
        mainViewModel = MainViewModel(cuboidModel)
    }

    @Test
    fun testVolume() {
      cuboidModel = CuboidModel()
      mainViewModel = MainViewModel(cuboidModel)
      mainViewModel.save(dummyWidth,dummyLength,dummyHeight)
      val volume = mainViewModel.getVolume()
      assertEquals(dummyVolume,volume,0.0001)
    }

    @Test
    fun testSurfaceArea() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth,dummyLength,dummyHeight)
        val surfaceArea = mainViewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea,surfaceArea,0.0)
    }

    @Test
    fun testCircumference() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(h = dummyHeight, l = dummyLength, w = dummyWidth)
        val circumference = mainViewModel.getCircumference()
        assertEquals(dummyCircumference,circumference,5.0)
    }


    /*
    * Dengan mock
    * padahal diatas ada mock gak se
    *
    * */
    @Test
    fun tesMockVolume() {
        `when`(mainViewModel.getVolume()).thenReturn(dummyVolume)
        val volume = mainViewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dummyVolume,volume,0.0001)
    }

    @Test
    fun tesMockCircumference() {
        `when`(mainViewModel.getCircumference()).thenReturn(dummyCircumference)
        val circumference = mainViewModel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(dummyCircumference,circumference,0.0001)
    }

    @Test
    fun tesMockArea() {
        `when`(mainViewModel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surfaceArea = mainViewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceArea()
        assertEquals(dummySurfaceArea,surfaceArea,0.0001)
    }




    @Test
    fun getCircumference() {
    }

    @Test
    fun getSurfaceArea() {
    }

    @Test
    fun getVolume() {
    }

    @Test
    fun save() {
    }
}