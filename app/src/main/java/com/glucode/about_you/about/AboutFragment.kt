package com.glucode.about_you.about

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.profile.views.ProfileCardView

class AboutFragment: Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var profileView: ProfileCardView
    private  var imageUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createEngineerProfile()
        setUpQuestions()
    }

    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }
    private fun createEngineerProfile(){
        val engineerName = arguments?.getString("name")
        val engineerRole = arguments?.getString("role")
        val engineerNumOfYears = arguments?.getInt("years")
        val engineerNumOfCoffees = arguments?.getInt("coffees")
        val engineerNumOfBugs = arguments?.getInt("bugs")

        profileView = ProfileCardView(requireContext())
        profileView.engineerName = engineerName
        profileView.engineerRole = engineerRole
        profileView.engineerYears = engineerNumOfYears.toString()
        profileView.engineerCoffees = engineerNumOfCoffees.toString()
        profileView.engineerBugs = engineerNumOfBugs.toString()
        profileView.profileImage = imageUri
        uploadProfileImage()

        binding.container.addView(profileView)
    }

    private fun uploadProfileImage(){
        profileView.rootView.setOnClickListener {
            val chooseImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            launchImagePicker.launch(chooseImage)
        }
    }

    private val launchImagePicker =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode == Activity.RESULT_OK){
                imageUri = it.data?.data
                profileView.profileImage = imageUri
            }
        }
}