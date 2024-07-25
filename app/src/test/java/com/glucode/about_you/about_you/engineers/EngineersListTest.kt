package com.glucode.about_you.about_you.engineers

import com.glucode.about_you.mockdata.MockData
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class EngineersListTest {

    private val engineersList = listOf(MockData.engineers)
    private var engineerName: String = ""
    private var questions: Int = 0
    private var reenenBugs: Int = 0
    @Test
    fun `check if the list of engineers is not empty` (){
        return assertThat(engineersList.size).isGreaterThan(0)
    }

    @Test
    fun `check if engineers name value is not null, by randomly picking any name ` (){
        for (engineers in engineersList.listIterator()){
            engineers.forEach {  engineer ->
                engineerName = engineer.name
            }
        }
        assertThat(engineerName).isNotNull()
    }

    @Test
    fun `check if one of engineers name is equals to Reenen` (){
        for (engineers in engineersList){
            engineerName = engineers.first().name
        }
        assertThat(engineerName).isEqualTo("Reenen")
    }


    @Test
    fun `check if number of questions is equal to 5` (){
        for (engineers in engineersList){
            questions = engineers.first().questions.size
        }
        assertThat(questions).isEqualTo(5)
    }

    @Test
    fun `check if number of questions is not equal to 5 then fail` (){
        for (engineers in engineersList){
            questions = engineers.first().questions.size
        }
        assertThat(questions).isEqualTo(15)
    }

    @Test
    fun `confirm that Reenen have 1800 as  number of bugs through out his career` (){
        for (engineers in engineersList){
            engineerName = engineers.first().name
            reenenBugs = engineers.first().quickStats.bugs
        }
        assertThat(engineerName).isEqualTo("Reenen")
        assertThat(reenenBugs).isGreaterThan(0)
        assertThat(reenenBugs).isEqualTo(1800)
    }
}