package com.glucode.about_you.profile.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewProfileCardBinding


class ProfileCardView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr){

    private val binding: ViewProfileCardBinding = ViewProfileCardBinding.inflate(LayoutInflater.from(context), this)
    var engineerName: String? = null
        set(value) {
            field = value
            binding.engineerName.text = value
        }
    var engineerRole: String? = null
        set(value) {
            field = value
            binding.engineerRole.text = value
        }
    var engineerYears: String? = null
        set(value) {
            field = value
            binding.engineerNumOfYears.text = value
        }
    var engineerCoffees: String? = null
        set(value) {
            field = value
            binding.engineerNumOfCoffees.text = value
        }
    var engineerBugs: String? = null
        set(value) {
            field = value
            binding.engineerNumOfBugs.text = value
        }

    var profileImage: Uri? = null
        set(value) {
            field = value
            if (value != null) {
                binding.profileImage.setOnClickListener {
                    Log.d("----test","am clicked")
                }
            }
        }
    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_large)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }
}