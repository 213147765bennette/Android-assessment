package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewQuestionCardBinding

class QuestionCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding: ViewQuestionCardBinding =
        ViewQuestionCardBinding.inflate(LayoutInflater.from(context), this)
    private lateinit var answerView: AnswerCardView

    @ColorInt
    private val selectedCardBackgroundColor: Int

    @ColorInt
    private val selectedTextColor: Int

    @ColorInt
    private val deselectedTextColor: Int

    var title: String? = null
        set(value) {
            field = value
            binding.title.text = value
        }

    var answers: List<String> = listOf()
        set(value) {
            field = value
            binding.answers.removeAllViews()
            value.forEach { answer ->
                addAnswer(answer)
            }
        }

    var selection: Int? = null
        set(value) {
            field = value
            value ?: return
            binding.answers.children.elementAt(value).isSelected = true
        }

    init {
        val whiteColour = ContextCompat.getColor(context, R.color.white)
        val blackColour = ContextCompat.getColor(context, R.color.black)
        selectedCardBackgroundColor = whiteColour
        selectedTextColor = blackColour
        deselectedTextColor = blackColour
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }

    private fun addAnswer(title: String) {
        answerView = AnswerCardView(context)
        answerView.title = title
        answerView.setOnClickListener {
            onAnswerClick(it)
        }
        binding.answers.addView(answerView)
    }

    private fun onAnswerClick(view: View) {
        if (!view.isSelected) {
            binding.answers.children.filter { it.isSelected }.forEach {
                it.isSelected = false
            }
        } else {
            setSelection()
        }
    }

    private fun setSelection() {

        if (selection?.let { binding.answers.children.elementAt(it).isSelected } == true) {
            answerView.setCardBackgroundColor(selectedCardBackgroundColor)
            binding.title.setTextColor(selectedTextColor)

        } else {
            answerView.setCardBackgroundColor(selectedCardBackgroundColor)
            binding.title.setTextColor(selectedTextColor)
        }

    }
}